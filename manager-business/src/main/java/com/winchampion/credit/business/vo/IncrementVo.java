package com.winchampion.credit.business.vo;


import com.winchampion.credit.common.util.annotation.ExcelField;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/27
 * @description: 增量和全量  实体类Vo
 */
public class IncrementVo {
    @ExcelField(title = "序号")
    private String id;
    @ExcelField(title = "字段")
    private String field;
    @ExcelField(title = "上次报送数据")
    private String lastPushDate;
    @ExcelField(title = "本次报送数据")
    private String thisTimePushDate;
    @ExcelField(title = "字段说明")
    private String fieldDesc;
    @ExcelField(title = "Y为必填项")
    private String isRequired;
    @ExcelField(title = "上报国家平台逻辑")
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
