package com.zjc.gydemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TaskFragment extends Fragment {
    /**
     *当fragment被创建的时候调用的方法，返回当前fragment显示的内容
     */
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_task,container,false);
    }
}
