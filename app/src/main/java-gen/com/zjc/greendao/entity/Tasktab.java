package com.zjc.greendao.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import com.google.gson.annotations.SerializedName;

/**
 * Entity mapped to table "TASKTAB".
 */
public class Tasktab {
    @SerializedName("CPLANNUM")
    private String cplannum;
    @SerializedName("ASSETNUM")
    private String assetNum;
    @SerializedName("CLINENUM")
    private String clinenum;
    @SerializedName("PARTLOC")
    private String partloc;
    @SerializedName("CONTENT")
    private String content;
    @SerializedName("STANDARD")
    private String standard;
    @SerializedName("CONTENTFLAG")
    private String contentflag;
    @SerializedName("RESULT")
    private String result;
    @SerializedName("RECORD")
    private String record;

    public Tasktab() {
    }

    public Tasktab(String cplannum, String assetNum, String clinenum, String partloc, String content, String standard, String contentflag, String result, String record) {
        this.cplannum = cplannum;
        this.assetNum = assetNum;
        this.clinenum = clinenum;
        this.partloc = partloc;
        this.content = content;
        this.standard = standard;
        this.contentflag = contentflag;
        this.result = result;
        this.record = record;
    }

    public String getCplannum() {
        return cplannum;
    }

    public void setCplannum(String cplannum) {
        this.cplannum = cplannum;
    }

    public String getAssetNum() {
        return assetNum;
    }

    public void setAssetNum(String assetNum) {
        this.assetNum = assetNum;
    }

    public String getClinenum() {
        return clinenum;
    }

    public void setClinenum(String clinenum) {
        this.clinenum = clinenum;
    }

    public String getPartloc() {
        return partloc;
    }

    public void setPartloc(String partloc) {
        this.partloc = partloc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getContentflag() {
        return contentflag;
    }

    public void setContentflag(String contentflag) {
        this.contentflag = contentflag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "Tasktab{" +
                "cplannum='" + cplannum + '\'' +
                ", assetNum='" + assetNum + '\'' +
                ", clinenum='" + clinenum + '\'' +
                ", partloc='" + partloc + '\'' +
                ", content='" + content + '\'' +
                ", standard='" + standard + '\'' +
                ", contentflag='" + contentflag + '\'' +
                ", result='" + result + '\'' +
                ", record='" + record + '\'' +
                '}';
    }
}
