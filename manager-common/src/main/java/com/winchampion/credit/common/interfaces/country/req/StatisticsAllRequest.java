package com.winchampion.credit.common.interfaces.country.req;

import java.math.BigDecimal;

public class StatisticsAllRequest {

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
    //正在对接笔数:单位笔，取整数
    private Integer dockingNum;
    //逾期笔数:单位笔，取整数
    private Integer overdueNum;
    //放款平均利率:银行放款实际执行利率的平均值，单位为%，保留两位小数
    private BigDecimal averageLendingRate;
    //全业务流程平均周期:银行成功放款时间减去企业融资需求申请时间的平均值，单位天，取整数
    private Integer fullBusinessProcessAveragePeriod;
    //授信平均周期:银行授信时间减去企业融资需求申请时间的平均值，单位天，取整数
    private Integer creditAveragePeriod;
    //放款平均周期:银行成功放款时间减去银行授信时间的平均值，单位天，取整数
    private Integer loanAveragePeriod;
    //信用放款平均周期:主担保模式为信用贷（不包含担保、抵押质押）银行成功放款时间减去银行授信时间的平均值，单位天，取整数
    private Integer creditLoanAveragePeriod;
    //近一年放款金额:单位为万，保留两位小数
    private BigDecimal nearlyYearLoanAmount;
    //近一年放款笔数:单位笔，取整数
    private Integer nearlyYearLoanNum;
    //近一年放款企业数:单位家，取整数
    private Integer nearlyYearLoanEnterpriseNum;
    //近一年信用放款金额:单位为万，保留两位小数
    private BigDecimal nearlyYearCreditLoanAmount;
    //近一年信用放款笔数:单位笔，取整数
    private Integer nearlyYearCreditLoanNum;
    //近一年信用放款企业数:单位家，取整数
    private Integer nearlyYearCreditLoanEnterpriseNum;
    //近一年融资需求金额:单位为万，保留两位小数
    private BigDecimal nearlyYearFinancingNeedsAmount;
    //近一年融资需求笔数:单位笔，取整数
    private Integer nearlyYearFinancingNeedsNum;
    //入驻金融机构数:单位家，取整数
    private Integer settledInFinancialInstitutionNum;
    //发布金融产品数：单位为个，取整数
    private Integer financialProductsNum;
    //统计开始时间:yyyy-MM-dd HH:mm:ss 默认:2019-09-24
    private String statisticsBeginTime;
    //统计截止时间:yyyy-MM-dd HH:mm:ss
    private String statisticsEndTime;

    //固定值
    public StatisticsAllRequest(){
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

    public Integer getDockingNum() {
        return dockingNum;
    }

    public void setDockingNum(Integer dockingNum) {
        this.dockingNum = dockingNum;
    }

    public Integer getOverdueNum() {
        return overdueNum;
    }

    public void setOverdueNum(Integer overdueNum) {
        this.overdueNum = overdueNum;
    }

    public BigDecimal getAverageLendingRate() {
        return averageLendingRate;
    }

    public void setAverageLendingRate(BigDecimal averageLendingRate) {
        this.averageLendingRate = averageLendingRate;
    }

    public Integer getFullBusinessProcessAveragePeriod() {
        return fullBusinessProcessAveragePeriod;
    }

    public void setFullBusinessProcessAveragePeriod(Integer fullBusinessProcessAveragePeriod) {
        this.fullBusinessProcessAveragePeriod = fullBusinessProcessAveragePeriod;
    }

    public Integer getCreditAveragePeriod() {
        return creditAveragePeriod;
    }

    public void setCreditAveragePeriod(Integer creditAveragePeriod) {
        this.creditAveragePeriod = creditAveragePeriod;
    }

    public Integer getLoanAveragePeriod() {
        return loanAveragePeriod;
    }

    public void setLoanAveragePeriod(Integer loanAveragePeriod) {
        this.loanAveragePeriod = loanAveragePeriod;
    }

    public Integer getCreditLoanAveragePeriod() {
        return creditLoanAveragePeriod;
    }

    public void setCreditLoanAveragePeriod(Integer creditLoanAveragePeriod) {
        this.creditLoanAveragePeriod = creditLoanAveragePeriod;
    }

    public BigDecimal getNearlyYearLoanAmount() {
        return nearlyYearLoanAmount;
    }

    public void setNearlyYearLoanAmount(BigDecimal nearlyYearLoanAmount) {
        this.nearlyYearLoanAmount = nearlyYearLoanAmount;
    }

    public Integer getNearlyYearLoanNum() {
        return nearlyYearLoanNum;
    }

    public void setNearlyYearLoanNum(Integer nearlyYearLoanNum) {
        this.nearlyYearLoanNum = nearlyYearLoanNum;
    }

    public Integer getNearlyYearLoanEnterpriseNum() {
        return nearlyYearLoanEnterpriseNum;
    }

    public void setNearlyYearLoanEnterpriseNum(Integer nearlyYearLoanEnterpriseNum) {
        this.nearlyYearLoanEnterpriseNum = nearlyYearLoanEnterpriseNum;
    }

    public BigDecimal getNearlyYearCreditLoanAmount() {
        return nearlyYearCreditLoanAmount;
    }

    public void setNearlyYearCreditLoanAmount(BigDecimal nearlyYearCreditLoanAmount) {
        this.nearlyYearCreditLoanAmount = nearlyYearCreditLoanAmount;
    }

    public Integer getNearlyYearCreditLoanNum() {
        return nearlyYearCreditLoanNum;
    }

    public void setNearlyYearCreditLoanNum(Integer nearlyYearCreditLoanNum) {
        this.nearlyYearCreditLoanNum = nearlyYearCreditLoanNum;
    }

    public Integer getNearlyYearCreditLoanEnterpriseNum() {
        return nearlyYearCreditLoanEnterpriseNum;
    }

    public void setNearlyYearCreditLoanEnterpriseNum(Integer nearlyYearCreditLoanEnterpriseNum) {
        this.nearlyYearCreditLoanEnterpriseNum = nearlyYearCreditLoanEnterpriseNum;
    }

    public BigDecimal getNearlyYearFinancingNeedsAmount() {
        return nearlyYearFinancingNeedsAmount;
    }

    public void setNearlyYearFinancingNeedsAmount(BigDecimal nearlyYearFinancingNeedsAmount) {
        this.nearlyYearFinancingNeedsAmount = nearlyYearFinancingNeedsAmount;
    }

    public Integer getNearlyYearFinancingNeedsNum() {
        return nearlyYearFinancingNeedsNum;
    }

    public void setNearlyYearFinancingNeedsNum(Integer nearlyYearFinancingNeedsNum) {
        this.nearlyYearFinancingNeedsNum = nearlyYearFinancingNeedsNum;
    }

    public Integer getSettledInFinancialInstitutionNum() {
        return settledInFinancialInstitutionNum;
    }

    public void setSettledInFinancialInstitutionNum(Integer settledInFinancialInstitutionNum) {
        this.settledInFinancialInstitutionNum = settledInFinancialInstitutionNum;
    }

    public Integer getFinancialProductsNum() {
        return financialProductsNum;
    }

    public void setFinancialProductsNum(Integer financialProductsNum) {
        this.financialProductsNum = financialProductsNum;
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
