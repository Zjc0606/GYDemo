package com.zjc.gydemo.dbmanager;

import android.content.Context;

import com.zjc.greendao.entity.Tasktab;

/**
 * 完成对TaskTab表的操作，ORM操作的是对象
 * Created by zjc on 2016/11/15.
 */

public class TasktabUtils {
    private DaoManager manager;

    public TasktabUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }
    /**
     *删除设备标准表
     * @return 删除成功标志
     */
    public boolean deleteTasktab(){
        boolean flag=false;

        try {
            manager.getDaoSession().deleteAll(Tasktab.class);//删除所有记录
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
