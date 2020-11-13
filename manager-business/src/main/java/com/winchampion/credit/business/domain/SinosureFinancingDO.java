package com.winchampion.credit.business.domain;

import java.io.Serializable;


/**
 * 中信保融资产品申请详情
 * 
 * @author zhangcong
 * @email 15121007361@163.com
 * @date 2020-06-22 13:23:33
 */
public class SinosureFinancingDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//申请记录id
	private String applyOdd;
	//所属行业
	private String industry;
	//主营业务
	private String primaryBusiness;
	//上年总资产(元)
	private String lastYearTotalAssets;
	//上年资产负债率
	private String lastYearDebtRatio;
	//上年主营业务收入(元)
	private String lastYearPrimaryIncome;
	//上年净利润(元)
	private String lastYearRetainedProfits;
	//员工人数
	private String numberOfEmployees;
	//当前授信银行个数
	private String creditBankNumber;
	//当前授信总金额(元)
	private String creditAmount;
	//姓名
	private String name;
	//手机
	private String mobilePhone;
	//职务
	private String duty;
	//邮箱
	private String mailbox;
	//产品名称
	private String productName;
	//金融机构名称
	private String organizationName;
	//申请时间
	private String applyTime;
	
	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	
	public String getApplyOdd() {
		return applyOdd;
	}
	public void setApplyOdd(String applyOdd) {
		this.applyOdd = applyOdd;
	}
	/**
	 * 设置：所属行业
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	/**
	 * 获取：所属行业
	 */
	public String getIndustry() {
		return industry;
	}
	/**
	 * 设置：主营业务
	 */
	public void setPrimaryBusiness(String primaryBusiness) {
		this.primaryBusiness = primaryBusiness;
	}
	/**
	 * 获取：主营业务
	 */
	public String getPrimaryBusiness() {
		return primaryBusiness;
	}
	/**
	 * 设置：上年总资产(元)
	 */
	public void setLastYearTotalAssets(String lastYearTotalAssets) {
		this.lastYearTotalAssets = lastYearTotalAssets;
	}
	/**
	 * 获取：上年总资产(元)
	 */
	public String getLastYearTotalAssets() {
		return lastYearTotalAssets;
	}
	/**
	 * 设置：上年资产负债率
	 */
	public void setLastYearDebtRatio(String lastYearDebtRatio) {
		this.lastYearDebtRatio = lastYearDebtRatio;
	}
	/**
	 * 获取：上年资产负债率
	 */
	public String getLastYearDebtRatio() {
		return lastYearDebtRatio;
	}
	/**
	 * 设置：上年主营业务收入(元)
	 */
	public void setLastYearPrimaryIncome(String lastYearPrimaryIncome) {
		this.lastYearPrimaryIncome = lastYearPrimaryIncome;
	}
	/**
	 * 获取：上年主营业务收入(元)
	 */
	public String getLastYearPrimaryIncome() {
		return lastYearPrimaryIncome;
	}
	/**
	 * 设置：上年净利润(元)
	 */
	public void setLastYearRetainedProfits(String lastYearRetainedProfits) {
		this.lastYearRetainedProfits = lastYearRetainedProfits;
	}
	/**
	 * 获取：上年净利润(元)
	 */
	public String getLastYearRetainedProfits() {
		return lastYearRetainedProfits;
	}
	/**
	 * 设置：员工人数
	 */
	public void setNumberOfEmployees(String numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}
	/**
	 * 获取：员工人数
	 */
	public String getNumberOfEmployees() {
		return numberOfEmployees;
	}
	/**
	 * 设置：当前授信银行个数
	 */
	public void setCreditBankNumber(String creditBankNumber) {
		this.creditBankNumber = creditBankNumber;
	}
	/**
	 * 获取：当前授信银行个数
	 */
	public String getCreditBankNumber() {
		return creditBankNumber;
	}
	/**
	 * 设置：当前授信总金额(元)
	 */
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	/**
	 * 获取：当前授信总金额(元)
	 */
	public String getCreditAmount() {
		return creditAmount;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：手机
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * 获取：手机
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * 设置：职务
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * 获取：职务
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * 设置：邮箱
	 */
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	/**
	 * 获取：邮箱
	 */
	public String getMailbox() {
		return mailbox;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
}
