package com.zjc.gydemo.activity;

import android.content.Intent;
import android.hardware.uhf.magic.reader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;
import com.zjc.greendao.entity.Tasks;
import com.zjc.gydemo.dbmanager.DescUtils;
import com.zjc.gydemo.dbmanager.DevprtlineUtils;
import com.zjc.gydemo.dbmanager.NetaddressUtils;
import com.zjc.gydemo.dbmanager.PlanUtils;
import com.zjc.gydemo.dbmanager.TasksUtils;
import com.zjc.gydemo.dbmanager.TasktabUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.edit_user)
    EditText username;
    @BindView(R.id.edit_password)
    EditText password;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private TasksUtils tasksUtils;
    private TasktabUtils tasktabUtils;
    private DevprtlineUtils devprtlineUtils;
    private PlanUtils planUtils;
    private DescUtils descUtils;
    private NetaddressUtils netaddressUtils;

    String mUser, mPassword, mNet;
    String mPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Logger.init(TAG);
        tasksUtils = new TasksUtils(this);
        tasktabUtils = new TasktabUtils(this);
        devprtlineUtils = new DevprtlineUtils(this);
        planUtils = new PlanUtils(this);
        descUtils = new DescUtils(this);
        netaddressUtils = new NetaddressUtils(this);
//        Init();//录卡接口在模拟器上有问题
        dbInit();

    }

    @OnClick(R.id.btn_login)
    public void login() {
        mUser = username.getText().toString().trim();
        mPassword = password.getText().toString().trim();
        try {
            mPro = URLEncoder.encode("工艺", "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams();
        params.put("type", 2);
        params.put("username", mUser);
        params.put("password", mPassword);
        params.put("pro", mPro);
        mNet = netaddressUtils.queryNet();
        String adress="http://192.168.56.1:8080/wsn/login.action";
//        String adress="http://192.168.56.1:8080/GongYi/login/userLogin.action";
        Logger.d(params.toString() + ":" + mNet);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(adress, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (responseBody != null) {
                    String result = new String(responseBody);
                    Logger.json(result);
                    if (result.contains("用户名不存在")) {
                        Toast.makeText(LoginActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();
                    } else if (result.contains("密码错误")) {
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    } else {
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<Tasks>>() {}.getType();
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            System.out.println("----"+jsonObject.getString("Assets"));
                            ArrayList<Tasks> tasksList = gson.fromJson(jsonObject.getString("Assets"), type);
                            System.out.println("----"+tasksList);
                            tasksUtils.insertMultTasks(tasksList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent=new Intent(LoginActivity.this,PlanActivity.class);
                        intent.putExtra("username",mUser);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(LoginActivity.this, "网络有问题", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void Init() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                android.hardware.uhf.magic.reader.init("/dev/ttyMT1");
                android.hardware.uhf.magic.reader.Open("/dev/ttyMT1");
                Logger.d("扫卡代码初始化");
                if (reader.SetTransmissionPower(2100) != 0x11) {
                    if (reader.SetTransmissionPower(2100) != 0x11) {
                        reader.SetTransmissionPower(2100);
                    }
                }
            }
        });
        thread.start();
    }

    void dbInit() {
        tasksUtils.deleteTasks();
        tasktabUtils.deleteTasktab();
        devprtlineUtils.deleteDevprtline();
        planUtils.deletePlan();
        descUtils.deleteDesc();
        netaddressUtils.insertNet();
    }
}
