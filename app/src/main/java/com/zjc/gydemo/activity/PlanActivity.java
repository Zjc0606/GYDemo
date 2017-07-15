package com.zjc.gydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zjc.greendao.entity.Tasks;
import com.zjc.gydemo.bean.PlanBean;
import com.zjc.gydemo.dbmanager.NetaddressUtils;
import com.zjc.gydemo.dbmanager.TasksUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class PlanActivity extends AppCompatActivity {
    @BindView(R.id.sp_qin)
    Spinner mSpinner1;
    @BindView(R.id.sp_type)
    Spinner mSpinner2;

    private TasksUtils tasksUtils;
    private PlanBean mPlanBean;
    private NetaddressUtils netaddressUtils;

    Intent mIntent;

    List<String> mList1;
    List<String> mList2;
    List<Tasks> mTasksList;
    List<PlanBean> mPlanBeanList;
    ArrayAdapter<String> mAdapter1;
    ArrayAdapter<String> mAdapter2;
    String mQin, mType, mUsername;
    String params,mNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        ButterKnife.bind(this);
        netaddressUtils=new NetaddressUtils(this);
        mIntent = this.getIntent();
        tasksUtils = new TasksUtils(this);
        mList1 = new ArrayList<>();
        mList1.add("一勤");
        mList1.add("二勤");
        mList1.add("三勤");
        mList2 = new ArrayList<>();
        mList1.add("转炉");
        mList1.add("阳极炉");

        //为下拉列表定义一个适配器，这里就用到里前面定义的list。
        mAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList1);
        mAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList1);
        //为适配器设置下拉列表下拉时的菜单样式。
        mAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将适配器添加到下拉列表上
        mSpinner1.setAdapter(mAdapter1);
        mSpinner2.setAdapter(mAdapter2);

    }

    @OnClick(R.id.bt_planSubmit)
    void planSubmit() throws JSONException{
        mQin = mSpinner1.getSelectedItem().toString().trim();
        mType = mSpinner2.getSelectedItem().toString().trim().equals("转炉") ? "CF" : "RF";
        mUsername = mIntent.getStringExtra("username");
        mTasksList = tasksUtils.queryTasks(mType);
        for (Tasks tasks : mTasksList) {
            mPlanBean = new PlanBean();
            mPlanBean.setAssetnum(tasks.getAssetnum());
            mPlanBean.setRegular(mQin);
            mPlanBean.setRegular(mUsername);
            mPlanBean.setType(mType);
            mPlanBeanList.add(mPlanBean);
        }
        JSONObject jsonParam = new JSONObject();
        Gson gson = new Gson();
        jsonParam.put("GYrecord", "");
        jsonParam.put("DQrecord", "");
        jsonParam.put("PlanSet", gson.toJson(mPlanBeanList));
        jsonParam.put("Task", "");
        jsonParam.put("DQnoplan", "");
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");//解决中文乱码问题
        AsyncHttpClient client = new AsyncHttpClient();
        mNet = netaddressUtils.queryNet();
        client.post(PlanActivity.this, mNet, entity, "application/json;charset=utf-8", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
