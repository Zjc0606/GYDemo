package com.zjc.gydemo.dbmanager;

import android.content.Context;

import com.zjc.greendao.entity.Plan;

import java.util.List;

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

    /**
     * 插入多条记录，需要开启新的线程
     * @param planList
     * @return 判断成功标志
     */
    public boolean insertMultPlan(final List<Plan> planList){
        boolean flag=false;
        try {
            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for(Plan plan:planList){
                        manager.getDaoSession().insertOrReplace(plan);
                    }
                }
            });
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
    /**
     * 返回多行记录
     * @return
     */
    public List<Plan> getPlanAll(){
        return manager.getDaoSession().loadAll(Plan.class);
    }
}
