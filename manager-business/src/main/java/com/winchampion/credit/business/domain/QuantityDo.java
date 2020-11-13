package com.winchampion.credit.business.domain;

/**
 * @ClassName：IncrementDo
 * @Description：全量实体类
 * @Author：wanglichao
 * @Date：2020-11-07 21:20
 * @Versiion：1.0
 */
public class QuantityDo {
    private String id;
    private String field;
    private String lastPushDate;
    private String thisTimePushDate;
    private String fieldDesc;
    private String isRequired;
    private String howToPush;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getLastPushDate() {
        return lastPushDate;
    }

    public void setLastPushDate(String lastPushDate) {
        this.lastPushDate = lastPushDate;
    }

    public String getThisTimePushDate() {
        return thisTimePushDate;
    }

    public void setThisTimePushDate(String thisTimePushDate) {
        this.thisTimePushDate = thisTimePushDate;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getHowToPush() {
        return howToPush;
    }

    public void setHowToPush(String howToPush) {
        this.howToPush = howToPush;
    }
}
