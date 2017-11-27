package com.zjc.gydemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment {
    private static final String TAG = "HistoryFragment";
    private final static int DATE_DIALOG = 0;

    @BindView(R.id.time)
    Button time;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab_history,container,false);
        ButterKnife.bind(this,view);
        Logger.init(TAG);
        Logger.d(TAG,"执行");

        return view;
    }

}
