package com.winchampion.credit.business.domain;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/29
 * @description:
 */
public class UploadReportDo {
    private String id;
    // 操作时间
    private String date;
    // 账户名（日常上报、账户人）
    private String userName;
    // 增量接口上报状态
    private String dayStatus;
    // 全量接口上报状态
    private String allStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDayStatus() {
        return dayStatus;
    }

    public void setDayStatus(String dayStatus) {
        this.dayStatus = dayStatus;
    }

    public String getAllStatus() {
        return allStatus;
    }

    public void setAllStatus(String allStatus) {
        this.allStatus = allStatus;
    }
}
