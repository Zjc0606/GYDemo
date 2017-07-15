package com.zjc.gydemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {
    @BindView(R.id.id_tab_task)
    LinearLayout mTabTask;
    @BindView(R.id.id_tab_history)
    LinearLayout mTabHistory;
    @BindView(R.id.id_tab_my)
    LinearLayout mTabMy;
    @BindView(R.id.top_text)
    TextView mTopText;

    @BindView(R.id.id_tab_task_img)
    ImageButton mImgTask;
    @BindView(R.id.id_tab_history_img)
    ImageButton mImgHistory;
    @BindView(R.id.id_tab_my_img)
    ImageButton mImgMy;

    private Fragment mFragTask;
    private Fragment mFragHistory;
    private Fragment mFragMy;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Logger.init(TAG);
        setSelect(1);
    }

    @OnClick({R.id.id_tab_task, R.id.id_tab_history, R.id.id_tab_my})
    public void mainClick(LinearLayout view) {
        resetImgs();
        switch (view.getId()) {
            case R.id.id_tab_task:
                setSelect(1);
                break;
            case R.id.id_tab_history:
                setSelect(2);

                break;
            case R.id.id_tab_my:
                setSelect(3);
                break;
        }
    }

    /**
     * 切换图片至暗色
     */
    private void resetImgs() {
        mImgTask.setImageResource(R.drawable.tab_task_normal);
        mImgHistory.setImageResource(R.drawable.tab_history_normal);
        mImgMy.setImageResource(R.drawable.tab_my_normal);

    }

    /**
     * 1、把图片设置为亮的
     * 2、设置内容区域
     * 3、切换top信息
     */
    private void setSelect(int i) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 1:
                if (mFragTask==null){
                    mFragTask=new TaskFragment();
                    transaction.add(R.id.id_content,mFragTask);
                }else{
                    transaction.show(mFragTask);
                }
                mTopText.setText("当前任务信息");
                mImgTask.setImageResource(R.drawable.tab_task_pressed);
                break;
            case 2:
                if (mFragHistory==null){
                    mFragHistory=new HistoryFragment();
                    transaction.add(R.id.id_content,mFragHistory);
                }else{
                    transaction.show(mFragHistory);
                }
                mTopText.setText("历史记录查询");
                mImgHistory.setImageResource(R.drawable.tab_history_pressed);

                break;
            case 3:
                if (mFragMy==null){
                    mFragMy=new MyFragment();
                    transaction.add(R.id.id_content,mFragMy);
                }else{
                    transaction.show(mFragMy);
                }
                mTopText.setText("我的账户信息");
                mImgMy.setImageResource(R.drawable.tab_my_pressed);

                break;
            default:

                break;
        }
        transaction.commit();
    }
    private void hideFragment(FragmentTransaction transaction){
        if (mFragTask!=null){
            transaction.hide(mFragTask);
        }
        if (mFragHistory!=null){
            transaction.hide(mFragHistory);
        }
        if (mFragMy!=null){
            transaction.hide(mFragMy);
        }
    }
}
