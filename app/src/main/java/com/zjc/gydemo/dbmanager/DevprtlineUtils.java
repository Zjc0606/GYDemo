package com.zjc.gydemo.dbmanager;

import android.content.Context;

/**
 * 完成对devprtline表的操作，ORM操作的是对象
 * Created by zjc on 2016/11/15.
 */

public class DevprtlineUtils {
    private DaoManager manager;

    public DevprtlineUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }
    /**
     *删除表
     * @return 删除成功标志
     */
    public boolean deleteDevprtline(){
        boolean flag=false;

        try {
            manager.getDaoSession().deleteAll(DevprtlineUtils.class);//删除所有记录
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
