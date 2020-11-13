package com.winchampion.credit.common.interfaces.country.req;

import java.math.BigDecimal;
import java.util.List;

public class StatisticsDayRequest {

    //数据唯一标识 ID 长度（50）
    private String sjlyId;
    //省/直辖市/自治区：企业所在省，中文名称
    private String province;
    //地级市：企业所在市，中文名称
    private String city;
    //区/县：企业所在区，中文名称
    private String area;
    //注册企业数：累计注册的企业数，单位家，取整数
    private Integer registeredEnterpriseNum;
    //授信企业数：累计授信的企业数，单位家，取整数
    private Integer creditEnterpriseNum;
    //放款企业数：累计放款的企业数，单位家，取整数
    private Integer loanEnterpriseNum;
    //信用放款企业数：累计信用放款的企业数，单位家，取整数
    private Integer creditLoanEnterpriseNum;
    //融资需求企业数：累计融资需求的企业数，单位家，取整数
    private Integer financingNeedsEnterpriseNum;
    //授信金额：单位为万元，保留两位小数
    private BigDecimal creditAmount;
    //授信笔数：单位笔，取整数
    private Integer creditNum;
    //放款金额：单位为万元，保留两位小数
    private BigDecimal loanAmount;
    //放款笔数：单位笔，取整数
    private Integer loanNum;
    //信用放款金额:单位为万元，保留两位小数
    private BigDecimal creditLoanAmount;
    //信用放款笔数:单位笔，取整数
    private Integer creditLoanNum;
    //融资需求金额:单位为万，保留两位小数
    private BigDecimal financingNeedsAmount;
    //融资需求笔数:单位笔，取整数
    private Integer financingNeedsNum;
    //企业行业统计信息
    private List<IndustryStatistics> enterpriseIndustryStatistics;

    //企业规模统计信息
    private List<ScaleStatistics> enterpriseScaleStatistics;
    //企业营收区间统计信息
    private List<RevenueIntervalStatistics> enterpriseRevenueIntervalStatistics;
    //企业税收区间统计信息
    private List<TaxIntervalStatistics> enterpriseTaxIntervalStatistics;
    //成功放款金额区间统计信息
    private List<LoanAmountRangeStatistics> successLoanAmountRangeStatistics;
    //放款类型统计信息
    private List<LoanAmountTypeStatistics> loanAmountTypeStatistics;
    //金融机构统计信息
    private List<FinancialInstitutionStatistics> financialInstitutionStatistics;
    //需求金额区间统计信息
    private List<NeedsAmountRangeStatistics> needsAmountRangeStatistics;
    //入驻金融机构数：单位家，取整数
    private Integer settledInFinancialInstitutionNum;
    //入驻金融机构统计信息
    private List<SettledInFinancialInstitutionStatistics> settledInFinancialInstitutionStatistics;
    //金融产品数：单位为个，取整数
    private Integer financialProductsNum;
    //金融产品统计信息
    private List<FinancialProductsStatistics> financialProductsStatistics;
    //统计开始时间：yyyy-MM-dd HH:mm:ss
    private String statisticsBeginTime;
    //统计截止时间：yyyy-MM-dd HH:mm:ss
    private String statisticsEndTime;

    //固定值
    public StatisticsDayRequest(){
        this.province = "上海市";
        this.city = "上海市";
        this.area = "-";
    }

    public String getSjlyId() {
        return sjlyId;
    }

    public void setSjlyId(String sjlyId) {
        this.sjlyId = sjlyId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getRegisteredEnterpriseNum() {
        return registeredEnterpriseNum;
    }

    public void setRegisteredEnterpriseNum(Integer registeredEnterpriseNum) {
        this.registeredEnterpriseNum = registeredEnterpriseNum;
    }

    public Integer getCreditEnterpriseNum() {
        return creditEnterpriseNum;
    }

    public void setCreditEnterpriseNum(Integer creditEnterpriseNum) {
        this.creditEnterpriseNum = creditEnterpriseNum;
    }

    public Integer getLoanEnterpriseNum() {
        return loanEnterpriseNum;
    }

    public void setLoanEnterpriseNum(Integer loanEnterpriseNum) {
        this.loanEnterpriseNum = loanEnterpriseNum;
    }

    public Integer getCreditLoanEnterpriseNum() {
        return creditLoanEnterpriseNum;
    }

    public void setCreditLoanEnterpriseNum(Integer creditLoanEnterpriseNum) {
        this.creditLoanEnterpriseNum = creditLoanEnterpriseNum;
    }

    public Integer getFinancingNeedsEnterpriseNum() {
        return financingNeedsEnterpriseNum;
    }

    public void setFinancingNeedsEnterpriseNum(Integer financingNeedsEnterpriseNum) {
        this.financingNeedsEnterpriseNum = financingNeedsEnterpriseNum;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Integer getCreditNum() {
        return creditNum;
    }

    public void setCreditNum(Integer creditNum) {
        this.creditNum = creditNum;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(Integer loanNum) {
        this.loanNum = loanNum;
    }

    public BigDecimal getCreditLoanAmount() {
        return creditLoanAmount;
    }

    public void setCreditLoanAmount(BigDecimal creditLoanAmount) {
        this.creditLoanAmount = creditLoanAmount;
    }

    public Integer getCreditLoanNum() {
        return creditLoanNum;
    }

    public void setCreditLoanNum(Integer creditLoanNum) {
        this.creditLoanNum = creditLoanNum;
    }

    public BigDecimal getFinancingNeedsAmount() {
        return financingNeedsAmount;
    }

    public void setFinancingNeedsAmount(BigDecimal financingNeedsAmount) {
        this.financingNeedsAmount = financingNeedsAmount;
    }

    public Integer getFinancingNeedsNum() {
        return financingNeedsNum;
    }

    public void setFinancingNeedsNum(Integer financingNeedsNum) {
        this.financingNeedsNum = financingNeedsNum;
    }

    public List<IndustryStatistics> getEnterpriseIndustryStatistics() {
        return enterpriseIndustryStatistics;
    }

    public void setEnterpriseIndustryStatistics(List<IndustryStatistics> enterpriseIndustryStatistics) {
        this.enterpriseIndustryStatistics = enterpriseIndustryStatistics;
    }

    public List<ScaleStatistics> getEnterpriseScaleStatistics() {
        return enterpriseScaleStatistics;
    }

    public void setEnterpriseScaleStatistics(List<ScaleStatistics> enterpriseScaleStatistics) {
        this.enterpriseScaleStatistics = enterpriseScaleStatistics;
    }

    public List<RevenueIntervalStatistics> getEnterpriseRevenueIntervalStatistics() {
        return enterpriseRevenueIntervalStatistics;
    }

    public void setEnterpriseRevenueIntervalStatistics(List<RevenueIntervalStatistics> enterpriseRevenueIntervalStatistics) {
        this.enterpriseRevenueIntervalStatistics = enterpriseRevenueIntervalStatistics;
    }

    public List<TaxIntervalStatistics> getEnterpriseTaxIntervalStatistics() {
        return enterpriseTaxIntervalStatistics;
    }

    public void setEnterpriseTaxIntervalStatistics(List<TaxIntervalStatistics> enterpriseTaxIntervalStatistics) {
        this.enterpriseTaxIntervalStatistics = enterpriseTaxIntervalStatistics;
    }

    public List<LoanAmountRangeStatistics> getSuccessLoanAmountRangeStatistics() {
        return successLoanAmountRangeStatistics;
    }

    public void setSuccessLoanAmountRangeStatistics(List<LoanAmountRangeStatistics> successLoanAmountRangeStatistics) {
        this.successLoanAmountRangeStatistics = successLoanAmountRangeStatistics;
    }

    public List<LoanAmountTypeStatistics> getLoanAmountTypeStatistics() {
        return loanAmountTypeStatistics;
    }

    public void setLoanAmountTypeStatistics(List<LoanAmountTypeStatistics> loanAmountTypeStatistics) {
        this.loanAmountTypeStatistics = loanAmountTypeStatistics;
    }

    public List<FinancialInstitutionStatistics> getFinancialInstitutionStatistics() {
        return financialInstitutionStatistics;
    }

    public void setFinancialInstitutionStatistics(List<FinancialInstitutionStatistics> financialInstitutionStatistics) {
        this.financialInstitutionStatistics = financialInstitutionStatistics;
    }

    public List<NeedsAmountRangeStatistics> getNeedsAmountRangeStatistics() {
        return needsAmountRangeStatistics;
    }

    public void setNeedsAmountRangeStatistics(List<NeedsAmountRangeStatistics> needsAmountRangeStatistics) {
        this.needsAmountRangeStatistics = needsAmountRangeStatistics;
    }

    public Integer getSettledInFinancialInstitutionNum() {
        return settledInFinancialInstitutionNum;
    }

    public void setSettledInFinancialInstitutionNum(Integer settledInFinancialInstitutionNum) {
        this.settledInFinancialInstitutionNum = settledInFinancialInstitutionNum;
    }

    public List<SettledInFinancialInstitutionStatistics> getSettledInFinancialInstitutionStatistics() {
        return settledInFinancialInstitutionStatistics;
    }

    public void setSettledInFinancialInstitutionStatistics(List<SettledInFinancialInstitutionStatistics> settledInFinancialInstitutionStatistics) {
        this.settledInFinancialInstitutionStatistics = settledInFinancialInstitutionStatistics;
    }

    public Integer getFinancialProductsNum() {
        return financialProductsNum;
    }

    public void setFinancialProductsNum(Integer financialProductsNum) {
        this.financialProductsNum = financialProductsNum;
    }

    public List<FinancialProductsStatistics> getFinancialProductsStatistics() {
        return financialProductsStatistics;
    }

    public void setFinancialProductsStatistics(List<FinancialProductsStatistics> financialProductsStatistics) {
        this.financialProductsStatistics = financialProductsStatistics;
    }

    public String getStatisticsBeginTime() {
        return statisticsBeginTime;
    }

    public void setStatisticsBeginTime(String statisticsBeginTime) {
        this.statisticsBeginTime = statisticsBeginTime;
    }

    public String getStatisticsEndTime() {
        return statisticsEndTime;
    }

    public void setStatisticsEndTime(String statisticsEndTime) {
        this.statisticsEndTime = statisticsEndTime;
    }
}
