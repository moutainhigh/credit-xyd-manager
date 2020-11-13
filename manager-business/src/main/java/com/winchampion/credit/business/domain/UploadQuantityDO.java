package com.winchampion.credit.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 上传模板全量表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-10-28 18:46:48
 */
public class UploadQuantityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private String id;
	//注册企业数 gai
	private Integer registeredEnterpriseNum;
	//授信企业数
	private Integer creditEnterpriseNum;
	//放款企业数
	private Integer loanEnterpriseNum;
	//信用放款企业数
	private Integer creditLoanEnterpriseNum;
	//融资需求企业数
	private Integer financingNeedsEnterpriseNum;
	//授信金额
	private BigDecimal creditAmount;
	//授信笔数
	private Integer creditNum;
	//放款金额
	private BigDecimal loanAmount;
	//放款笔数
	private Integer loanNum;
	//信用放款金额
	private BigDecimal creditLoanAmount;
	//信用放款笔数
	private Integer creditLoanNum;
	//融资需求金额
	private BigDecimal financingNeedsAmount;
	//融资需求笔数
	private Integer financingNeedsNum;
	//正在对接笔数
	private Integer dockingNum;
	//逾期笔数
	private Integer overdueNum;
	//放款平均利率
	private BigDecimal averageLendingRate;
	//全业务流程平均周期
	private Integer fullBusinessProcessAveragePeriod;
	//授信平均周期
	private Integer creditAveragePeriod;
	//放款平均周期
	private Integer loanAveragePeriod;
	//信用放款平均周期
	private Integer creditLoanAveragePeriod;
	//近一年放款金额
	private BigDecimal nearlyYearLoanAmount;
	//近一年放款笔数
	private Integer nearlyYearLoanNum;
	//近一年放款企业数
	private Integer nearlyYearLoanEnterpriseNum;
	//近一年信用放款金额
	private BigDecimal nearlyYearCreditLoanAmount;
	//近一年信用放款笔数
	private Integer nearlyYearCreditLoanNum;
	//近一年信用放款企业数
	private Integer nearlyYearCreditLoanEnterpriseNum;
	//近一年融资需求金额
	private BigDecimal nearlyYearFinancingNeedsAmount;
	//近一年融资需求笔数
	private Integer nearlyYearFinancingNeedsNum;
	//入驻金融机构数
	private Integer settledInFinancialInstitutionNum;
	//发布金融产品数
	private Integer financialProductsNum;
	//上传记录id
	private String recordId;
    //版本号
    private String version;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
