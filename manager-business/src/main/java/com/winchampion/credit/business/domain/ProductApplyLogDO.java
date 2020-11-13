package com.winchampion.credit.business.domain;

import java.io.Serializable;

import com.winchampion.credit.user.utils.DictUtils;



/**
 * 产品申请记录表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-04 16:20:45
 */
public class ProductApplyLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private String id;
	//产品id
	private String productId;
	//用户id
	private String customerId;
	//申请单号
	private String applyOdd;
	//申请类型
	private String applicationType;
	//申请类型描述
	private String applicationDes;
	//申请时间
	private String applyTime;
	//最后一步申请结果
	private String applyResult;
	//申请结果描述
	private String applyResultDes;
	//企业信息
	private String companyMessage;
	
	//用户姓名
	private String customerName;
	//用户手机号
	private String customerPhone;
	//产品名称
	private String productName;
	//所属金融机构
	private String insName;
	
	//联系人姓名
	private String name;
	//联系人手机号
	private String mobilePhone;

	/**
	 * 设置：主键ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：产品id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品id
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置：用户id
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：用户id
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：申请单号
	 */
	public void setApplyOdd(String applyOdd) {
		this.applyOdd = applyOdd;
	}
	/**
	 * 获取：申请单号
	 */
	public String getApplyOdd() {
		return applyOdd;
	}
	/**
	 * 设置：申请类型
	 */
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	/**
	 * 获取：申请类型
	 */
	public String getApplicationType() {
		return applicationType;
	}
	/**
	 * 设置：申请时间
	 */
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * 获取：申请时间
	 */
	public String getApplyTime() {
		return applyTime;
	}
	/**
	 * 设置：最后一步申请结果
	 */
	public void setApplyResult(String applyResult) {
		this.applyResult = applyResult;
	}
	/**
	 * 获取：最后一步申请结果
	 */
	public String getApplyResult() {
		return applyResult;
	}
	public String getCompanyMessage() {
		return companyMessage;
	}
	public void setCompanyMessage(String companyMessage) {
		this.companyMessage = companyMessage;
	}
	public String getApplicationDes() {
		applicationDes = DictUtils.getDictLabel(applicationType, "product_application_type", "-");
		return applicationDes;
	}
	public void setApplicationDes(String applicationDes) {
		this.applicationDes = applicationDes;
	}
	public String getApplyResultDes() {
		applyResultDes = DictUtils.getDictLabel(applyResult, "product_apply_result", "-");
		return applyResultDes;
	}
	public void setApplyResultDes(String applyResultDes) {
		this.applyResultDes = applyResultDes;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
}
