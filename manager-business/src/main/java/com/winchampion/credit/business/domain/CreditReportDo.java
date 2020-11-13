package com.winchampion.credit.business.domain;

import java.io.Serializable;

public class CreditReportDo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    //客户/企业编号
    private String searchId;
    //报告类型 1.个人信用报告，2法人信用报告
    private String reportType;
    //企业名称
    private String cname;
    //统一社会信用代码
    private String creditCode;
    //查询时间
    private String searchDate;
    //查询结果 1.查询成功，2未查到
    private String searchResult;
    //查询结果名称
    private String searchResultName;
    //客户编号
    private String customerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public String getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(String searchResult) {
        this.searchResult = searchResult;
    }

    public String getSearchResultName() {
        return searchResultName;
    }

    public void setSearchResultName(String searchResultName) {
        this.searchResultName = searchResultName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
