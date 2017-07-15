package com.zjc.gydemo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 存放任务分组、勤数、设备编码及用户名
 * Created by zjc on 2016/11/23.
 */

public class PlanBean {
    @SerializedName("ASSETNUM")
    private String assetnum;
    @SerializedName("REGULAR")
    private String regular;
    @SerializedName("EXECUTEBY")
    private String executeby;
    @SerializedName("TYPE")
    private String type;

    public String getAssetnum() {
        return assetnum;
    }

    public void setAssetnum(String assetnum) {
        this.assetnum = assetnum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExecuteby() {
        return executeby;
    }

    public void setExecuteby(String executeby) {
        this.executeby = executeby;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
