package com.zjc.gydemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

public class MyFragment extends Fragment {
    private static final String TAG = "MyFragment";
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab_my,container,false);
        ButterKnife.bind(this,view);
        Logger.init(TAG);
        Logger.d(TAG,"执行");

        return view;
    }
}
