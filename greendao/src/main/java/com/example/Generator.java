package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Generator {
    public static void main(String[] args) throws Exception {

        int version=1;
        String entityPackageName="com.zjc.greendao.entity";
//        String entityClassName="Note";
        String daoPackageName="com.zjc.greendao.dao";
        Schema schema=new Schema(version,entityPackageName);
        schema.setDefaultJavaPackageDao(daoPackageName);

        //添加实体
        addEntity(schema);

        String outDir="E:\\Android\\AndroidStudioProjects\\GYDemo\\app\\src\\main\\java-gen";
        new DaoGenerator().generateAll(schema,outDir);

    }

    private static void addEntity(Schema schema) {
        //创建设备表
        Entity entity1 = schema.addEntity("Devprtline");
        entity1.setTableName("DEVPRTLINE");
//        entity.addIdProperty().autoincrement();
        entity1.addStringProperty("phynum");
        entity1.addStringProperty("rfid");
        entity1.addStringProperty("assetnum");


        //创建计划任务设备表
        Entity entity2 = schema.addEntity("Desc");
        entity2.setTableName("DESC");
        entity2.addStringProperty("assetnum");
        entity2.addStringProperty("regular");
        entity2.addStringProperty("executeby");
        entity2.addStringProperty("type");

        //创建历史记录表
        Entity entity3 = schema.addEntity("History");
        entity3.setTableName("HISTORYRECORD");
        entity3.addStringProperty("date");
        entity3.addStringProperty("content");
        entity3.addStringProperty("result1");
        entity3.addStringProperty("result2");
        entity3.addStringProperty("result3");
        entity3.addStringProperty("result4");

        //创建网址表
        Entity entity4 = schema.addEntity("Netaddress");
        entity4.setTableName("NETADDRESS");
        entity4.addStringProperty("name");
        entity4.addStringProperty("netaddress");

        //创建计划表
        Entity entity5= schema.addEntity("Plan");
        entity5.setTableName("PLANGY");
        entity5.addStringProperty("cplannum");
        entity5.addStringProperty("assetnum");
        entity5.addStringProperty("cstdnum");
        entity5.addStringProperty("executeby");
        entity5.addStringProperty("drawupdate");
        entity5.addStringProperty("regular");
        entity5.addStringProperty("result");

        //创建任务表
        Entity entity6= schema.addEntity("Tasks");
        entity6.setTableName("TASKS");
        entity6.addStringProperty("phynum");
        entity6.addStringProperty("rfid");
        entity6.addStringProperty("assetnum");
        entity6.addStringProperty("description");
        entity6.addStringProperty("type");

        //创建标准表
        Entity entity7= schema.addEntity("Tasktab");
        entity7.setTableName("TASKTAB");
        entity7.addStringProperty("cplannum");
        entity7.addStringProperty("assetNum");
        entity7.addStringProperty("clinenum");
        entity7.addStringProperty("partloc");
        entity7.addStringProperty("content");
        entity7.addStringProperty("standard");
        entity7.addStringProperty("contentflag");
        entity7.addStringProperty("result");
        entity7.addStringProperty("record");
    }
}
