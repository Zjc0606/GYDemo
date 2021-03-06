package com.zjc.greendao.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import com.google.gson.annotations.SerializedName;

/**
 * Entity mapped to table "PLANGY".
 */
public class Plan {
    @SerializedName("CPLANNUM")
    private String cplannum;
    @SerializedName("ASSETNUM")
    private String assetnum;
    @SerializedName("CSTDNUM")
    private String cstdnum;
    @SerializedName("EXECUTEBY")
    private String executeby;
    @SerializedName("DRAWUPDATE")
    private String drawupdate;
    @SerializedName("REGULAR")
    private String regular;
    @SerializedName("RESULT")
    private String result;

    public Plan() {
    }

    public Plan(String cplannum, String assetnum, String cstdnum, String executeby, String drawupdate, String regular, String result) {
        this.cplannum = cplannum;
        this.assetnum = assetnum;
        this.cstdnum = cstdnum;
        this.executeby = executeby;
        this.drawupdate = drawupdate;
        this.regular = regular;
        this.result = result;
    }

    public String getCplannum() {
        return cplannum;
    }

    public void setCplannum(String cplannum) {
        this.cplannum = cplannum;
    }

    public String getAssetnum() {
        return assetnum;
    }

    public void setAssetnum(String assetnum) {
        this.assetnum = assetnum;
    }

    public String getCstdnum() {
        return cstdnum;
    }

    public void setCstdnum(String cstdnum) {
        this.cstdnum = cstdnum;
    }

    public String getExecuteby() {
        return executeby;
    }

    public void setExecuteby(String executeby) {
        this.executeby = executeby;
    }

    public String getDrawupdate() {
        return drawupdate;
    }

    public void setDrawupdate(String drawupdate) {
        this.drawupdate = drawupdate;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "cplannum='" + cplannum + '\'' +
                ", assetnum='" + assetnum + '\'' +
                ", cstdnum='" + cstdnum + '\'' +
                ", executeby='" + executeby + '\'' +
                ", drawupdate='" + drawupdate + '\'' +
                ", regular='" + regular + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
