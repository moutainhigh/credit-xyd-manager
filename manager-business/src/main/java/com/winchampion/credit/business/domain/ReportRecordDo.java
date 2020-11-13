package com.winchampion.credit.business.domain;

import java.util.Date;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/28
 * @description: 上报记录VO
 */

public class ReportRecordDo {
    private String id;
    // 上报类型（1：手动上报  2：定时任务）
    private String type;
    // 上报版本
    private String version;
    // 全量接口上报结果
    private String quantityStatus;
    // 增量接口上报结果
    private String incrementStatus;
    // 上报时间
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getQuantityStatus() {
        return quantityStatus;
    }

    public void setQuantityStatus(String quantityStatus) {
        this.quantityStatus = quantityStatus;
    }

    public String getIncrementStatus() {
        return incrementStatus;
    }

    public void setIncrementStatus(String incrementStatus) {
        this.incrementStatus = incrementStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
