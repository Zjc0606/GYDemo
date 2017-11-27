package com.zjc.gydemo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.Logger;
import com.zjc.greendao.entity.Plan;
import com.zjc.greendao.entity.Tasks;
import com.zjc.greendao.entity.Tasktab;
import com.zjc.gydemo.bean.PlanBean;
import com.zjc.gydemo.dbmanager.DescUtils;
import com.zjc.gydemo.dbmanager.NetaddressUtils;
import com.zjc.gydemo.dbmanager.PlanUtils;
import com.zjc.gydemo.dbmanager.TasksUtils;
import com.zjc.gydemo.dbmanager.TasktabUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class PlanActivity extends AppCompatActivity {
    private static final String TAG = "PlanActivity";

    @BindView(R.id.sp_qin)
    Spinner mSpinner1;
    @BindView(R.id.sp_type)
    Spinner mSpinner2;

    private TasksUtils tasksUtils;
    private NetaddressUtils netaddressUtils;
    private DescUtils descUtils;
    private TasktabUtils tasktabUtils;
    private PlanUtils planUtils;

    Intent mIntent;

    List<String> mList1;
    List<String> mList2;
    List<Tasks> mTasksList;
    List<PlanBean> mPlanBeanList;
    ArrayAdapter<String> mAdapter1;
    ArrayAdapter<String> mAdapter2;
    String mQin, mType, mUsername;
    String mNet;
    private ProgressDialog proDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        ButterKnife.bind(this);
        Logger.init(TAG);
        netaddressUtils = new NetaddressUtils(this);
        descUtils = new DescUtils(this);
        mIntent = this.getIntent();
        tasksUtils = new TasksUtils(this);
        tasktabUtils = new TasktabUtils(this);
        planUtils = new PlanUtils(this);
        mList1 = new ArrayList<>();
        mList1.add("一勤");
        mList1.add("二勤");
        mList1.add("三勤");
        mList2 = new ArrayList<>();
        mList2.add("转炉");
        mList2.add("阳极炉");

        //为下拉列表定义一个适配器，这里就用到里前面定义的list。
        mAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList1);
        mAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList2);
        //为适配器设置下拉列表下拉时的菜单样式。
        mAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将适配器添加到下拉列表上
        mSpinner1.setAdapter(mAdapter1);
        mSpinner2.setAdapter(mAdapter2);

    }

    @OnClick(R.id.bt_submit)
    public void planSubmit(){
        showDialog();//显示等待弹框
        mQin = mSpinner1.getSelectedItem().toString().trim();
        mType = mSpinner2.getSelectedItem().toString().trim().equals("转炉") ? "CF" : "RF";
        mUsername = mIntent.getStringExtra("username");
        mTasksList = tasksUtils.queryTasks(mType);
        Logger.d(mTasksList.toString());
        PlanBean mPlanBean;
        mPlanBeanList=new ArrayList<>();
        for (Tasks tasks : mTasksList) {
            mPlanBean = new PlanBean();
            mPlanBean.setAssetnum(tasks.getAssetnum());
            mPlanBean.setRegular(mQin);
            mPlanBean.setExecuteby(mUsername);
            mPlanBean.setType(mType);
            mPlanBeanList.add(mPlanBean);
        }
        JSONObject jsonParam = new JSONObject();
        try {
            Gson gson = new Gson();
            jsonParam.put("PlanSet", gson.toJson(mPlanBeanList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Logger.json(jsonParam.toString());
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");//解决中文乱码问题
        AsyncHttpClient client = new AsyncHttpClient();
        mNet = netaddressUtils.queryNet();
        String adress="http://192.168.56.1:8080/wsn/task.action";
        client.post(PlanActivity.this, adress, entity, "application/json;charset=utf-8", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    Toast.makeText(PlanActivity.this, "已确认", Toast.LENGTH_LONG).show();
                    proDia.dismiss();
                }
                if (responseBody != null) {
                    String result = new String(responseBody);
                    descUtils.deleteDesc();
                    Gson gson = new Gson();
                    Type typeTaskTab = new TypeToken<ArrayList<Tasktab>>() {
                    }.getType();
                    Type typePlan = new TypeToken<ArrayList<Plan>>() {
                    }.getType();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
//                        Logger.json(jsonObject.toString());
                        ArrayList<Tasktab> taskTabArrayList = gson.fromJson(jsonObject.getString("StdGY"), typeTaskTab);
                        ArrayList<Plan> planArrayList = gson.fromJson(jsonObject.getString("PlanGY"), typePlan);
//                        Logger.d(taskTabArrayList.toString());
//                        Logger.d(planArrayList.toString());
                        tasktabUtils.insertMultTaskTab(taskTabArrayList);
                        planUtils.insertMultPlan(planArrayList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    /**
                     * android.view.WindowLeaked: Activity has leaked window com.android.internal.p
                     *这个错误原因是一些对话框在activity转向之前，并没有销毁，导致其引用的context为空，因此引发内存泄漏。
                     *这个问题属于当前Activity已经onDestroy但是依靠其的Dialog没有dismiss
                     */
                    proDia.dismiss();
                    Intent intentMain = new Intent(PlanActivity.this, MainActivity.class);
                    startActivity(intentMain);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                descUtils.deleteDesc();
                proDia.dismiss();
                Toast.makeText(PlanActivity.this, "确认失败！超时或者网络不稳定", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void showDialog() {
        proDia = ProgressDialog.show(PlanActivity.this, "确认中", "请耐心等待...");
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(8000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    proDia.dismiss();
                }
            }
        }.start();
        proDia.show();
    }

}
