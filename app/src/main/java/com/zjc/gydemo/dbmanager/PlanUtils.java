package com.zjc.gydemo.dbmanager;

import android.content.Context;

import com.zjc.greendao.entity.Plan;

/**
 * 完成对plan表的操作，ORM操作的是对象
 * Created by zjc on 2016/11/15.
 */

public class PlanUtils {
    private DaoManager manager;

    public PlanUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }
    /**
     *删除计划表
     * @return 删除成功标志
     */
    public boolean deletePlan(){
        boolean flag=false;

        try {
            manager.getDaoSession().deleteAll(Plan.class);//删除所有记录
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
