package com.zjc.gydemo.dbmanager;

import android.content.Context;
import android.database.Cursor;

import com.zjc.greendao.dao.TasksDao;
import com.zjc.greendao.entity.Plan;
import com.zjc.greendao.entity.Tasks;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 完成对Tasks表的操作，ORM操作的是对象
 * Created by zjc on 2016/11/15.
 */

public class TasksUtils {
    private DaoManager manager;

    public TasksUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }

    /**
     * 完成对数据库中Student表的插入工作
     * @param student
     * @return
     */
//    public boolean insertStudent(Entity student){
//        boolean flag =false;
//        flag=manager.getDaoSession().insert(student)!=-1?true:false;
//        return flag;
//    }

    /**
     * 插入多条记录，需要开启新的线程
     * @param tasksList 设备数据RFID PHYNUM ASSETNUM DESCRIPTION  TYPE
     * @return 判断成功标志
     */
    public boolean insertMultTasks(final List<Tasks> tasksList){
        boolean flag=false;
        try {
            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for(Tasks asset:tasksList){
                        manager.getDaoSession().insertOrReplace(asset);
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
     * 完成对student的某一条记录的修改
     * @param student
     * @return
     */
//    public boolean updateStudent(Entity student){
//        boolean flag=false;
//        try {
//            manager.getDaoSession().update(student);
//            flag=true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }

    /**
     *删除设备表
     * @return 删除成功标志
     */
    public boolean deleteTasks(){
        boolean flag=false;

        try {
            manager.getDaoSession().deleteAll(Tasks.class);//删除所有记录
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据设备类型查询对应的设备
     */
    public List<Tasks> queryTasks(String type){
        //查询构建器
        QueryBuilder<Tasks> builder=manager.getDaoSession().queryBuilder(Tasks.class);
        List<Tasks> mTasksList=builder.where(TasksDao.Properties.Type.eq(type)).list();
//        Logger.d(mTasksList.size()+"");
        return mTasksList;
    }

    /**
     * 根据设备编码查询设备并去重
     * @param planList s设备编码及已点检次数
     * @return 返回任务列表显示的设备
     */
    public  List<String> getDescriptions(List<Plan> planList) {
        List<String> result = new ArrayList<>();
        Cursor cur;
        for (Plan plan:planList) {
            cur = manager.getDaoSession().getDatabase().rawQuery("SELECT DISTINCT DESCRIPTION FROM Tasks WHERE " +
                    "ASSETNUM='"+plan.getAssetnum()+"'", null);
            while(cur.moveToNext())
            {
                if("停运".equals(plan.getResult())){
                    result.add(cur.getString(0)+" 已停运");
                }else{
                    result.add(cur.getString(0)+" 当勤已点"+plan.getResult()+"次");
                }
            }
            cur.close();
        }
        return result;
    }

    //根据RFID编码查询设备
    public String getDevice(String m_strresult){
        String device=null;
        Cursor cur = manager.getDaoSession().getDatabase().rawQuery("SELECT DISTINCT DESCRIPTION FROM Tasks WHERE " +
                "PHYNUM='"+m_strresult+"'", null);
        while(cur.moveToNext())
        {
            device=cur.getString(0);
        }
        cur.close();
//        Logger.d(list.size()+"");
//        Logger.d(list.toString());
        return device;
    }

    /**
     * 返回多行记录
     * @return
     */
//    public List<Entity> listAll(){
//        return manager.getDaoSession().loadAll(Entity.class);
//    }

    /**
     * 按照主键返回单行记录
     * @param key
     * @return
     */
//    public Entity listOneStudent(long key){
//        return manager.getDaoSession().load(Entity.class,key);
//    }

//    public void query1(){
//        //使用native sql进行查询操作
//        List<Entity> list=manager.getDaoSession().queryRaw(Entity.class,"where name like ? and _id>?",new String[]{"%张%","5"});
//        Logger.d("查询数据:"+list);
//    }

    /**
     * select * from where name like ? or name=? or
     * <  >ge  <= >= != between and
     * select * from student where age>23 and name like "张三"
     */
//    public void query2(){
//        //查询构建器
//        QueryBuilder<Entity> builder=manager.getDaoSession().queryBuilder(Entity.class);
//        List<Entity> list=builder.where(EntityDao.Properties.Age.ge(22)).list();
//        Logger.d(list.size()+"");
//        Logger.d(list.toString());
//    }
//
//    public void query3(){
//        QueryBuilder<Entity> builder=manager.getDaoSession().queryBuilder(Entity.class);
//        //select * from student where name='张三‘ or score>95
//        builder.whereOr(EntityDao.Properties.Name.eq("张三"),EntityDao.Properties.Score.ge(95));
////        builder.whereOr(EntityDao.Properties.Name.eq("张三"),EntityDao.Properties.Score.ge(95)).limit(5);//取前5条数据
//        List<Entity> list=builder.list();
//        Logger.d(builder.buildCount()+"");
//        Logger.d(list.toString());
//    }


}
