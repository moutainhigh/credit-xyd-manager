package com.winchampion.credit.business.domain;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/30
 * @description: 上传增量、全量、首页展示数据 excel 记录
 */
public class UploadDo {

    private String id;
    private String version;
    private String date;
    private String userId;
    private String filePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
