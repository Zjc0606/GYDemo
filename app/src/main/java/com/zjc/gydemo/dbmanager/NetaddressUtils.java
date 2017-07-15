package com.zjc.gydemo.dbmanager;


import android.content.Context;

import com.orhanobut.logger.Logger;
import com.zjc.greendao.dao.NetaddressDao;
import com.zjc.greendao.entity.Netaddress;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 完成对netaddress表的操作，ORM操作的是对象
 * Created by zjc on 2016/11/15.
 */

public class NetaddressUtils {
    private DaoManager manager;

    public NetaddressUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }

    /**
     * 完成对数据库中Netaddress表的插入工作
     * @return 返回成功标志
     */
    public boolean insertNetAddress(Netaddress netaddress){
        boolean flag =false;
        flag=manager.getDaoSession().insert(netaddress)!=-1?true:false;
        return flag;
    }

    /**
     * select * from where name like ? or name=? or
     * <  >ge  <= >= != between and
     * select * from student where age>23 and name like "张三"
     */
    public String queryNet(){
        //查询构建器
        QueryBuilder<Netaddress> builder=manager.getDaoSession().queryBuilder(Netaddress.class);
        List<Netaddress> list=builder.where(NetaddressDao.Properties.Name.eq("金隆")).list();
        return list.size()==1?list.get(0).getNetaddress():null;
    }


    public void insertNet(){
        if (queryNet()==null) {
            Logger.d("插入网址");
            Netaddress netaddress=new Netaddress();
            netaddress.setName("金隆");
            netaddress.setNetaddress("http://192.168.10.15:8080/dianjian/servlet/Httpserver");
            insertNetAddress(netaddress);
        }
    }
}
