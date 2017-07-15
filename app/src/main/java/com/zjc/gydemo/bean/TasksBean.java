package com.zjc.gydemo.bean;

import com.zjc.greendao.entity.Tasks;

import java.util.ArrayList;

/**
 * 登陆时解析设备json数据
 * Created by zjc on 2016/11/18.
 */

public class TasksBean {
    private ArrayList<Tasks> tasksList;

    public ArrayList<Tasks> getTasksList() {
        return tasksList;
    }

    public void setTasksList(ArrayList<Tasks> tasksList) {
        this.tasksList = tasksList;
    }
}
