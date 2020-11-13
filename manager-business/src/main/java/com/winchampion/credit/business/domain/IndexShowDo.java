package com.winchampion.credit.business.domain;

import com.winchampion.credit.common.util.annotation.ExcelField;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/30
 * @description: Excel 首页展示数据 Sheet 列 VO
 */
public class IndexShowDo {

    //
    @ExcelField(title = "UUID")
    private String uuid;
    @ExcelField(title = "机构标识")
    private String mechanismFlag;
    @ExcelField(title = "月份")
    private String month;
    @ExcelField(title = "开始时间")
    private String startDate;
    @ExcelField(title = "结束时间")
    private String endDate;
    @ExcelField(title = "统计类型（1：累计申请次数；2：累计授信金额；3：注册用户数；4：累计授信笔数）")
    private String statisticsType;
    @ExcelField(title = "数量")
    private String count;
    @ExcelField(title = "删除标识")
    private String isDelFlag;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMechanismFlag() {
        return mechanismFlag;
    }

    public void setMechanismFlag(String mechanismFlag) {
        this.mechanismFlag = mechanismFlag;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(String statisticsType) {
        this.statisticsType = statisticsType;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getIsDelFlag() {
        return isDelFlag;
    }

    public void setIsDelFlag(String isDelFlag) {
        this.isDelFlag = isDelFlag;
    }
}
