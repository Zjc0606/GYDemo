package com.zjc.gydemo.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.uhf.magic.reader;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.zjc.greendao.entity.Plan;
import com.zjc.gydemo.dbmanager.PlanUtils;
import com.zjc.gydemo.dbmanager.TasksUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;


public class TaskFragment extends Fragment {
    private static final String TAG = "TaskFragment";

    @BindView(R.id.bt_scan)
    Button scan;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.checkedlist)
    ListView checkedList;

    private ArrayAdapter arrayAdapter=null;

    private PlanUtils planUtils;
    private TasksUtils tasksUtils;

    private Handler mHandler= new MainHandler();

    private boolean scanFlag,toastFlag;
    List<String> taskList=null;
    Map<String, String> mapStop=new HashMap<>();
    String stopFlag;
    /**
     *当fragment被创建的时候调用的方法，返回当前fragment显示的内容
     */
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab_task,container,false);
        ButterKnife.bind(this,view);
        Logger.init(TAG);
        planUtils = new PlanUtils(getContext());
        tasksUtils = new TasksUtils(getContext());
        setDevice();//显示设备


        return view;
    }

    private void setDevice(){
        List<Plan> planParam= planUtils.getPlanAll();
        taskList=tasksUtils.getDescriptions(planParam);
        arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_expandable_list_item_1,taskList);
        checkedList.setAdapter(arrayAdapter);
    }

    @OnItemClick(R.id.checkedlist)
    public void setCheckedList(){
        String item=checkedList.getSelectedItem().toString();
        final String s[]=item.split(" ");
        String realDev=s[0];
        reader.m_handler=mHandler;
    }

    @OnClick(R.id.submit)
    public void setSubmit() {

    }

    class  MainHandler extends Handler{
        public void handleMessage(Message msg) {
            //扫描到了rfid卡，更改标志位
//			scanToastFlag=false;
            if(msg!=null&&scanFlag)
            {
                scanFlag=false;
                toastFlag=true;
                System.out.println("接收卡号---------"+msg);
                String m_strresult =(String)msg.obj;
                final String finalm_strresult=m_strresult;
                String devName = tasksUtils.getDevice(m_strresult);
                String realDev;
                for (String list : taskList) {
                    String s[]=list.split(" ");
                    realDev=s[0];
                    if(realDev.equals(devName)){
                        toastFlag=false;
                        final String finalDev=realDev;
                        if("已停运".equals(s[1])){
                            Dialog dialog = new AlertDialog.Builder(getContext())
                                    .setTitle("解除停运？")
                                    .setMessage("当前设备已停运无法点检，点击确认解除停运，点击取消返回任务列表！")
                                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            String num="0";

                                            if(mapStop!=null&&mapStop.containsKey(finalDev)){
                                                int count=Integer.parseInt(mapStop.get(finalDev), 10);
                                                if(count%2==1){
                                                    stopFlag="0";
                                                }else{
                                                    stopFlag="1";
                                                }
                                                count++;
                                                mapStop.put(finalDev, count+"");
                                            }else{
                                                stopFlag="1";
                                                mapStop.put(finalDev, stopFlag);
                                            }
//                                            DBUtil.updateUndo(finalDev,num,stopFlag);
//                                            DBUtil.updateResult(finalDev,"");
                                            setDevice();
                                            Toast.makeText(getContext(), "已解除，请重新扫卡进行点检！", Toast.LENGTH_SHORT).show();

                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener(){

                                        public void onClick(DialogInterface dialog, int which) {
                                            // TODO Auto-generated method stub
                                        }
                                    }).create();
                            dialog.show();
                            break;//匹配到设备之后停止for循环  下同
                        }else{
                            Dialog dialog = new AlertDialog.Builder(getContext())
                                    .setTitle(realDev)
                                    .setMessage("确定点检当前设备？")
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent();
                                            Bundle bundle = new Bundle();
                                            bundle.putString("rfidnum", finalm_strresult);
                                            bundle.putString("dev", finalDev);
                                            System.out.println("realDev"+finalDev);
//                                            intent.setClass(getActivity(),CheckListActivity.class);
//                                            intent.putExtras(bundle);
                                            //在此加判断，编辑框不需要默认正常
//                                            DBUtil.updateResult(finalDev,"正常");
                                            startActivity(intent);
                                        }
                                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).create();
                            dialog.show();
                            break;
                        }

                    }else{
                        continue;
                    }
                }
                if(toastFlag){
                    Toast.makeText(getContext(),"没有匹配的设备！",Toast.LENGTH_SHORT).show();
                }

            }else{
                //标签信息获取失败
                Toast.makeText(getContext(),"RFID标签信息获取不成功，请重试", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
}
