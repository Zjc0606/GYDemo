package com.zjc.greendao.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PLANGY".
 */
public class Plan {

    private String cplannum;
    private String assetnum;
    private String cstdnum;
    private String executeby;
    private String drawupdate;
    private String regular;
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

}
