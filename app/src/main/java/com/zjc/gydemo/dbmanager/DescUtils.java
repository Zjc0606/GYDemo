package com.zjc.gydemo.dbmanager;

import android.content.Context;

import com.zjc.greendao.entity.Desc;

/**
 * 完成对desc表的操作，ORM操作的是对象
 * Created by zjc on 2016/11/15.
 */

public class DescUtils {
    private DaoManager manager;

    public DescUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }
    /**
     *删除desc表
     * @return 删除成功标志
     */
    public boolean deleteDesc(){
        boolean flag=false;

        try {
            manager.getDaoSession().deleteAll(Desc.class);//删除所有记录
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
