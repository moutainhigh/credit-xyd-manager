package com.winchampion.credit.business.domain;

import com.winchampion.credit.common.interfaces.country.req.*;
import com.winchampion.credit.user.vo.UserVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 上传模板增量表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-10-28 18:46:47
 */
public class UploadIncrementDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private String id;
	//注册企业数 gai
	private Integer registeredEnterpriseNum;
	//授信企业数 gai
	private Integer creditEnterpriseNum;
	//放款企业数gai
	private Integer loanEnterpriseNum;
	//信用放款企业数gai
	private Integer creditLoanEnterpriseNum;
	//融资需求企业数gai
	private Integer financingNeedsEnterpriseNum;
	//授信金额gai
	private BigDecimal creditAmount;
	//授信笔数gai
	private Integer creditNum;
	//放款金额gai
	private BigDecimal loanAmount;
	//放款笔数gai
	private Integer loanNum;
	//信用放款金额gai
	private BigDecimal creditLoanAmount;
	//信用放款笔数
	private Integer creditLoanNum;
	//融资需求金额 gai
	private BigDecimal financingNeedsAmount;
	//融资需求笔数 gai
	private Integer financingNeedsNum;
	//企业行业统计信息
	private Integer comTradeCensusInfo;
	//行业类型编码
	private Integer tradeTypeCode;
	//各行业类型注册企业数
	private Integer alltradeTypeRegComNum;
	//各行业类型授信企业数
	private Integer alltradeTypeGrantNum;
	//企业规模统计信息
	private Integer comScaleTotalInfo;
	//规模编码
	private Integer scaleCode;
	//各规模注册企业数
	private Integer scaleRegisterComNum;
	//各规模授信企业数
	private Integer scaleGrantComNum;
	//企业营收区间统计信息
	private Integer comRevenueRegionInfo;
	//营收区间编码
	private Integer revenueRegionCode;
	//各营收区间注册企业数
	private Integer allrevenueRegisterComNum;
	//企业税收区间统计信息
	private Integer comTaxRegionTotalInfo;
	//税收区间编码
	private Integer taxRegionCode;
	//各税收区间注册企业数
	private Integer alltaxRegionRegiComNum;
	//成功放款金额区间统计信息
	private Integer succLoanMonregiTotInfo;
	//金额区间编码
	private Integer moneyRegionCode;
	//各区间放款金额
	private BigDecimal allRegionLoanMoney;
	//放款类型统计信息
	private Integer loanTypeTotalInfo;
	//放款类型编码
	private Integer loanTypeCode;
	//各类型放款金额
	private BigDecimal allTypeLoanMoney;
	//金融机构统计信息
	private Integer finMechaTotalInfo;
	//金融机构名称
	private String finMechaName;
	//各金融机构放款金额
	private BigDecimal allfinMechaLoanMoney;
	//各金融机构放款笔数
	private Integer allfinMechaLoanTotal;
	//各金融机构放款企业数
	private Integer allfinMechaLoanComNum;
	//各金融机构受理融资需求笔数
	private Integer allfinMechaAccNeedNum;
	//各金融机构受理需求金额
	private BigDecimal allfinMechaAccNeedMony;
	//需求金额区间统计信息
	private Integer needMoneyRegionTotInfo;
	//金额区间编码(需求金额区间)
	private Integer moneyRegionCode2;
	//各区间的需求金额
	private BigDecimal allRegionNeedMoney;
	//入驻金融机构数
	private Integer settledInFinancialInstitutionNum;
	//入驻金融机构统计信息
	private Integer settlementFinTotalInfo;
	//机构类型编码
	private Integer finTypeCode;
	//各类型金融机构数
	private Integer allTypeFinNum;
	//金融产品数 gai
	private Integer financialProductsNum;
	//金融产品统计信息
	private Integer finProductTotalInfo;
	//产品类型编码
	private Integer productTypeCode;
	//各类型金融产品数
	private Integer allTypeFinproductNum;
	//上传记录id
	private String recordId;
	//版本号
	private String version;

	// 企业行业统计信息
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
	//入驻金融机构统计信息
	private List<SettledInFinancialInstitutionStatistics> settledInFinancialInstitutionStatistics;
	//金融产品统计信息
	private List<FinancialProductsStatistics> financialProductsStatistics;


	public List<IndustryStatistics> getEnterpriseIndustryStatistics() {
		List<IndustryStatistics> indList = new ArrayList<>();
		IndustryStatistics industryStatistics = new IndustryStatistics();
		industryStatistics.setCode(this.tradeTypeCode);
		industryStatistics.setLoanNum(this.alltradeTypeGrantNum);
		industryStatistics.setRegisteredNum(this.alltradeTypeRegComNum);
		indList.add(industryStatistics);
		return indList;
	}

	public void setEnterpriseIndustryStatistics(List<IndustryStatistics> enterpriseIndustryStatistics) {
		this.enterpriseIndustryStatistics = enterpriseIndustryStatistics;
	}

	public List<FinancialProductsStatistics> getFinancialProductsStatistics() {

		List<FinancialProductsStatistics> finList = new ArrayList<>();
		FinancialProductsStatistics financialProductsStatistics = new FinancialProductsStatistics();
		financialProductsStatistics.setCode(this.productTypeCode);
		financialProductsStatistics.setProductsNum(this.allTypeFinproductNum);
		finList.add(financialProductsStatistics);
		return finList;
	}

	public void setFinancialProductsStatistics(List<FinancialProductsStatistics> financialProductsStatistics) {
		this.financialProductsStatistics = financialProductsStatistics;
	}

	public List<ScaleStatistics> getEnterpriseScaleStatistics() {
		List<ScaleStatistics> scaleStatistics = new ArrayList<>();
		ScaleStatistics statistics = new ScaleStatistics();
		statistics.setCode(this.scaleCode);
		statistics.setLoanNum(this.scaleGrantComNum);
		statistics.setRegisteredNum(this.scaleRegisterComNum);
		scaleStatistics.add(statistics);
		return scaleStatistics;
	}

	public void setEnterpriseScaleStatistics(List<ScaleStatistics> enterpriseScaleStatistics) {
		this.enterpriseScaleStatistics = enterpriseScaleStatistics;
	}

	public List<RevenueIntervalStatistics> getEnterpriseRevenueIntervalStatistics() {

		List<RevenueIntervalStatistics> revenueIntervalStatistics = new ArrayList<>();
		RevenueIntervalStatistics revenue = new RevenueIntervalStatistics();
		revenue.setCode(this.revenueRegionCode);
		revenue.setRegisteredNum(this.allrevenueRegisterComNum);
		revenueIntervalStatistics.add(revenue);

		return revenueIntervalStatistics;
	}

	public void setEnterpriseRevenueIntervalStatistics(List<RevenueIntervalStatistics> enterpriseRevenueIntervalStatistics) {
		this.enterpriseRevenueIntervalStatistics = enterpriseRevenueIntervalStatistics;
	}

	public List<TaxIntervalStatistics> getEnterpriseTaxIntervalStatistics() {
		List<TaxIntervalStatistics> taxIntervalStatistics = new ArrayList<>();
		TaxIntervalStatistics taxInt = new TaxIntervalStatistics();
		taxInt.setCode(this.taxRegionCode);
		taxInt.setRegisteredNum(this.alltaxRegionRegiComNum);
		taxIntervalStatistics.add(taxInt);
		return taxIntervalStatistics;
	}

	public void setEnterpriseTaxIntervalStatistics(List<TaxIntervalStatistics> enterpriseTaxIntervalStatistics) {
		this.enterpriseTaxIntervalStatistics = enterpriseTaxIntervalStatistics;
	}

	public List<LoanAmountRangeStatistics> getSuccessLoanAmountRangeStatistics() {
		List<LoanAmountRangeStatistics> loanList = new ArrayList<>();
		LoanAmountRangeStatistics loanAmountRangeStatistics = new LoanAmountRangeStatistics();
		loanAmountRangeStatistics.setCode(this.moneyRegionCode);
		loanAmountRangeStatistics.setRangeAmount(this.allRegionLoanMoney);
		loanList.add(loanAmountRangeStatistics);

		return loanList;
	}

	public void setSuccessLoanAmountRangeStatistics(List<LoanAmountRangeStatistics> successLoanAmountRangeStatistics) {
		this.successLoanAmountRangeStatistics = successLoanAmountRangeStatistics;
	}

	public List<LoanAmountTypeStatistics> getLoanAmountTypeStatistics() {
		List<LoanAmountTypeStatistics> loanList = new ArrayList<>();
		LoanAmountTypeStatistics loanAmountRangeStatistics = new LoanAmountTypeStatistics();
		loanAmountRangeStatistics.setCode(this.loanTypeCode);
		loanAmountRangeStatistics.setTypeAmount(this.allTypeLoanMoney);
		loanList.add(loanAmountRangeStatistics);

		return loanList;
	}

	public void setLoanAmountTypeStatistics(List<LoanAmountTypeStatistics> loanAmountTypeStatistics) {
		this.loanAmountTypeStatistics = loanAmountTypeStatistics;
	}

	public List<FinancialInstitutionStatistics> getFinancialInstitutionStatistics() {
		List<FinancialInstitutionStatistics> loanList = new ArrayList<>();
		FinancialInstitutionStatistics loanAmountRangeStatistics = new FinancialInstitutionStatistics();
		loanAmountRangeStatistics.setName(this.finMechaName);
		loanAmountRangeStatistics.setLoanAmount(this.allfinMechaLoanMoney);
		loanAmountRangeStatistics.setLoanNum(this.allfinMechaLoanTotal);
		loanAmountRangeStatistics.setEnterpriseNu(this.allfinMechaLoanComNum);
		loanAmountRangeStatistics.setNeedsNum(this.allfinMechaAccNeedNum);
		loanAmountRangeStatistics.setNeedsAmount(this.allfinMechaAccNeedMony);

		loanList.add(loanAmountRangeStatistics);

		return loanList;
	}

	public void setFinancialInstitutionStatistics(List<FinancialInstitutionStatistics> financialInstitutionStatistics) {
		this.financialInstitutionStatistics = financialInstitutionStatistics;
	}

	public List<NeedsAmountRangeStatistics> getNeedsAmountRangeStatistics() {

		List<NeedsAmountRangeStatistics> needsList = new ArrayList<>();
		NeedsAmountRangeStatistics needs = new NeedsAmountRangeStatistics();
		needs.setCode(this.moneyRegionCode2);
        needs.setRangNeedsAmount(this.allRegionNeedMoney);
        needsList.add(needs);
		return needsList;
	}

	public void setNeedsAmountRangeStatistics(List<NeedsAmountRangeStatistics> needsAmountRangeStatistics) {
		this.needsAmountRangeStatistics = needsAmountRangeStatistics;
	}



	public List<SettledInFinancialInstitutionStatistics> getSettledInFinancialInstitutionStatistics() {
		List<SettledInFinancialInstitutionStatistics> setList  = new ArrayList<>();
		SettledInFinancialInstitutionStatistics settled = new SettledInFinancialInstitutionStatistics();
		settled.setCode(this.finTypeCode);
		settled.setInstitutionsNum(this.allTypeFinNum);
		setList.add(settled);
		return setList;
	}

	public void setSettledInFinancialInstitutionStatistics(List<SettledInFinancialInstitutionStatistics> settledInFinancialInstitutionStatistics) {
		this.settledInFinancialInstitutionStatistics = settledInFinancialInstitutionStatistics;
	}

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

	public Integer getComTradeCensusInfo() {
		return comTradeCensusInfo;
	}

	public void setComTradeCensusInfo(Integer comTradeCensusInfo) {
		this.comTradeCensusInfo = comTradeCensusInfo;
	}

	public Integer getTradeTypeCode() {
		return tradeTypeCode;
	}

	public void setTradeTypeCode(Integer tradeTypeCode) {
		this.tradeTypeCode = tradeTypeCode;
	}

	public Integer getAlltradeTypeRegComNum() {
		return alltradeTypeRegComNum;
	}

	public void setAlltradeTypeRegComNum(Integer alltradeTypeRegComNum) {
		this.alltradeTypeRegComNum = alltradeTypeRegComNum;
	}

	public Integer getAlltradeTypeGrantNum() {
		return alltradeTypeGrantNum;
	}

	public void setAlltradeTypeGrantNum(Integer alltradeTypeGrantNum) {
		this.alltradeTypeGrantNum = alltradeTypeGrantNum;
	}

	public Integer getComScaleTotalInfo() {
		return comScaleTotalInfo;
	}

	public void setComScaleTotalInfo(Integer comScaleTotalInfo) {
		this.comScaleTotalInfo = comScaleTotalInfo;
	}

	public Integer getScaleCode() {
		return scaleCode;
	}

	public void setScaleCode(Integer scaleCode) {
		this.scaleCode = scaleCode;
	}

	public Integer getScaleRegisterComNum() {
		return scaleRegisterComNum;
	}

	public void setScaleRegisterComNum(Integer scaleRegisterComNum) {
		this.scaleRegisterComNum = scaleRegisterComNum;
	}

	public Integer getScaleGrantComNum() {
		return scaleGrantComNum;
	}

	public void setScaleGrantComNum(Integer scaleGrantComNum) {
		this.scaleGrantComNum = scaleGrantComNum;
	}

	public Integer getComRevenueRegionInfo() {
		return comRevenueRegionInfo;
	}

	public void setComRevenueRegionInfo(Integer comRevenueRegionInfo) {
		this.comRevenueRegionInfo = comRevenueRegionInfo;
	}

	public Integer getRevenueRegionCode() {
		return revenueRegionCode;
	}

	public void setRevenueRegionCode(Integer revenueRegionCode) {
		this.revenueRegionCode = revenueRegionCode;
	}

	public Integer getAllrevenueRegisterComNum() {
		return allrevenueRegisterComNum;
	}

	public void setAllrevenueRegisterComNum(Integer allrevenueRegisterComNum) {
		this.allrevenueRegisterComNum = allrevenueRegisterComNum;
	}

	public Integer getComTaxRegionTotalInfo() {
		return comTaxRegionTotalInfo;
	}

	public void setComTaxRegionTotalInfo(Integer comTaxRegionTotalInfo) {
		this.comTaxRegionTotalInfo = comTaxRegionTotalInfo;
	}

	public Integer getTaxRegionCode() {
		return taxRegionCode;
	}

	public void setTaxRegionCode(Integer taxRegionCode) {
		this.taxRegionCode = taxRegionCode;
	}

	public Integer getAlltaxRegionRegiComNum() {
		return alltaxRegionRegiComNum;
	}

	public void setAlltaxRegionRegiComNum(Integer alltaxRegionRegiComNum) {
		this.alltaxRegionRegiComNum = alltaxRegionRegiComNum;
	}

	public Integer getSuccLoanMonregiTotInfo() {
		return succLoanMonregiTotInfo;
	}

	public void setSuccLoanMonregiTotInfo(Integer succLoanMonregiTotInfo) {
		this.succLoanMonregiTotInfo = succLoanMonregiTotInfo;
	}

	public Integer getMoneyRegionCode() {
		return moneyRegionCode;
	}

	public void setMoneyRegionCode(Integer moneyRegionCode) {
		this.moneyRegionCode = moneyRegionCode;
	}

	public BigDecimal getAllRegionLoanMoney() {
		return allRegionLoanMoney;
	}

	public void setAllRegionLoanMoney(BigDecimal allRegionLoanMoney) {
		this.allRegionLoanMoney = allRegionLoanMoney;
	}

	public Integer getLoanTypeTotalInfo() {
		return loanTypeTotalInfo;
	}

	public void setLoanTypeTotalInfo(Integer loanTypeTotalInfo) {
		this.loanTypeTotalInfo = loanTypeTotalInfo;
	}

	public Integer getLoanTypeCode() {
		return loanTypeCode;
	}

	public void setLoanTypeCode(Integer loanTypeCode) {
		this.loanTypeCode = loanTypeCode;
	}

	public BigDecimal getAllTypeLoanMoney() {
		return allTypeLoanMoney;
	}

	public void setAllTypeLoanMoney(BigDecimal allTypeLoanMoney) {
		this.allTypeLoanMoney = allTypeLoanMoney;
	}

	public Integer getFinMechaTotalInfo() {
		return finMechaTotalInfo;
	}

	public void setFinMechaTotalInfo(Integer finMechaTotalInfo) {
		this.finMechaTotalInfo = finMechaTotalInfo;
	}

	public String getFinMechaName() {
		return finMechaName;
	}

	public void setFinMechaName(String finMechaName) {
		this.finMechaName = finMechaName;
	}

	public BigDecimal getAllfinMechaLoanMoney() {
		return allfinMechaLoanMoney;
	}

	public void setAllfinMechaLoanMoney(BigDecimal allfinMechaLoanMoney) {
		this.allfinMechaLoanMoney = allfinMechaLoanMoney;
	}

	public Integer getAllfinMechaLoanTotal() {
		return allfinMechaLoanTotal;
	}

	public void setAllfinMechaLoanTotal(Integer allfinMechaLoanTotal) {
		this.allfinMechaLoanTotal = allfinMechaLoanTotal;
	}

	public Integer getAllfinMechaLoanComNum() {
		return allfinMechaLoanComNum;
	}

	public void setAllfinMechaLoanComNum(Integer allfinMechaLoanComNum) {
		this.allfinMechaLoanComNum = allfinMechaLoanComNum;
	}

	public Integer getAllfinMechaAccNeedNum() {
		return allfinMechaAccNeedNum;
	}

	public void setAllfinMechaAccNeedNum(Integer allfinMechaAccNeedNum) {
		this.allfinMechaAccNeedNum = allfinMechaAccNeedNum;
	}

	public BigDecimal getAllfinMechaAccNeedMony() {
		return allfinMechaAccNeedMony;
	}

	public void setAllfinMechaAccNeedMony(BigDecimal allfinMechaAccNeedMony) {
		this.allfinMechaAccNeedMony = allfinMechaAccNeedMony;
	}

	public Integer getNeedMoneyRegionTotInfo() {
		return needMoneyRegionTotInfo;
	}

	public void setNeedMoneyRegionTotInfo(Integer needMoneyRegionTotInfo) {
		this.needMoneyRegionTotInfo = needMoneyRegionTotInfo;
	}

	public Integer getMoneyRegionCode2() {
		return moneyRegionCode2;
	}

	public void setMoneyRegionCode2(Integer moneyRegionCode2) {
		this.moneyRegionCode2 = moneyRegionCode2;
	}

	public BigDecimal getAllRegionNeedMoney() {
		return allRegionNeedMoney;
	}

	public void setAllRegionNeedMoney(BigDecimal allRegionNeedMoney) {
		this.allRegionNeedMoney = allRegionNeedMoney;
	}



	public Integer getSettlementFinTotalInfo() {
		return settlementFinTotalInfo;
	}

	public void setSettlementFinTotalInfo(Integer settlementFinTotalInfo) {
		this.settlementFinTotalInfo = settlementFinTotalInfo;
	}

	public Integer getFinTypeCode() {
		return finTypeCode;
	}

	public void setFinTypeCode(Integer finTypeCode) {
		this.finTypeCode = finTypeCode;
	}

	public Integer getAllTypeFinNum() {
		return allTypeFinNum;
	}

	public void setAllTypeFinNum(Integer allTypeFinNum) {
		this.allTypeFinNum = allTypeFinNum;
	}



	public Integer getFinProductTotalInfo() {
		return finProductTotalInfo;
	}

	public void setFinProductTotalInfo(Integer finProductTotalInfo) {
		this.finProductTotalInfo = finProductTotalInfo;
	}

	public Integer getProductTypeCode() {
		return productTypeCode;
	}

	public void setProductTypeCode(Integer productTypeCode) {
		this.productTypeCode = productTypeCode;
	}

	public Integer getAllTypeFinproductNum() {
		return allTypeFinproductNum;
	}

	public void setAllTypeFinproductNum(Integer allTypeFinproductNum) {
		this.allTypeFinproductNum = allTypeFinproductNum;
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

	@Override
	public String toString() {
		return "UploadIncrementDO{" +
				"id='" + id + '\'' +
				", registeredEnterpriseNum=" + registeredEnterpriseNum +
				", creditEnterpriseNum=" + creditEnterpriseNum +
				", loanEnterpriseNum=" + loanEnterpriseNum +
				", creditLoanEnterpriseNum=" + creditLoanEnterpriseNum +
				", financingNeedsEnterpriseNum=" + financingNeedsEnterpriseNum +
				", creditAmount=" + creditAmount +
				", creditNum=" + creditNum +
				", loanAmount=" + loanAmount +
				", loanNum=" + loanNum +
				", creditLoanAmount=" + creditLoanAmount +
				", creditLoanNum=" + creditLoanNum +
				", financingNeedsAmount=" + financingNeedsAmount +
				", financingNeedsNum=" + financingNeedsNum +
				", comTradeCensusInfo=" + comTradeCensusInfo +
				", tradeTypeCode=" + tradeTypeCode +
				", alltradeTypeRegComNum=" + alltradeTypeRegComNum +
				", alltradeTypeGrantNum=" + alltradeTypeGrantNum +
				", comScaleTotalInfo=" + comScaleTotalInfo +
				", scaleCode=" + scaleCode +
				", scaleRegisterComNum=" + scaleRegisterComNum +
				", scaleGrantComNum=" + scaleGrantComNum +
				", comRevenueRegionInfo=" + comRevenueRegionInfo +
				", revenueRegionCode=" + revenueRegionCode +
				", allrevenueRegisterComNum=" + allrevenueRegisterComNum +
				", comTaxRegionTotalInfo=" + comTaxRegionTotalInfo +
				", taxRegionCode=" + taxRegionCode +
				", alltaxRegionRegiComNum=" + alltaxRegionRegiComNum +
				", succLoanMonregiTotInfo=" + succLoanMonregiTotInfo +
				", moneyRegionCode=" + moneyRegionCode +
				", allRegionLoanMoney=" + allRegionLoanMoney +
				", loanTypeTotalInfo=" + loanTypeTotalInfo +
				", loanTypeCode=" + loanTypeCode +
				", allTypeLoanMoney=" + allTypeLoanMoney +
				", finMechaTotalInfo=" + finMechaTotalInfo +
				", finMechaName='" + finMechaName + '\'' +
				", allfinMechaLoanMoney=" + allfinMechaLoanMoney +
				", allfinMechaLoanTotal=" + allfinMechaLoanTotal +
				", allfinMechaLoanComNum=" + allfinMechaLoanComNum +
				", allfinMechaAccNeedNum=" + allfinMechaAccNeedNum +
				", allfinMechaAccNeedMony=" + allfinMechaAccNeedMony +
				", needMoneyRegionTotInfo=" + needMoneyRegionTotInfo +
				", moneyRegionCode2=" + moneyRegionCode2 +
				", allRegionNeedMoney=" + allRegionNeedMoney +
				", settledInFinancialInstitutionNum=" + settledInFinancialInstitutionNum +
				", settlementFinTotalInfo=" + settlementFinTotalInfo +
				", finTypeCode=" + finTypeCode +
				", allTypeFinNum=" + allTypeFinNum +
				", financialProductsNum=" + financialProductsNum +
				", finProductTotalInfo=" + finProductTotalInfo +
				", productTypeCode=" + productTypeCode +
				", allTypeFinproductNum=" + allTypeFinproductNum +
				", recordId='" + recordId + '\'' +
				", version='" + version + '\'' +
				", enterpriseIndustryStatistics=" + enterpriseIndustryStatistics +
				", enterpriseScaleStatistics=" + enterpriseScaleStatistics +
				", enterpriseRevenueIntervalStatistics=" + enterpriseRevenueIntervalStatistics +
				", enterpriseTaxIntervalStatistics=" + enterpriseTaxIntervalStatistics +
				", successLoanAmountRangeStatistics=" + successLoanAmountRangeStatistics +
				", loanAmountTypeStatistics=" + loanAmountTypeStatistics +
				", financialInstitutionStatistics=" + financialInstitutionStatistics +
				", needsAmountRangeStatistics=" + needsAmountRangeStatistics +
				", settledInFinancialInstitutionStatistics=" + settledInFinancialInstitutionStatistics +
				", financialProductsStatistics=" + financialProductsStatistics +
				'}';
	}

	public StatisticsDayRequest convert(StatisticsDayRequest statisticsDayRequest){
		statisticsDayRequest.setSjlyId(this.version);
		return statisticsDayRequest;
	}
}
