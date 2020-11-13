package com.winchampion.credit.business.domain;

import java.io.Serializable;


/**
 * 问题反馈记录表
 * 
 * @author zhangcong
 * @email zhangcong@163.com
 * @date 2020-04-07 09:30:07
 */
public class FaqFeedbackRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private String id;
	//问题编号
	private String faqNo;
	//用户id
	private String customerId;
	//用户账户
	private String customerPhone;
	//用户姓名
	private String customerName;
	//姓名
	private String contactName;
	//联系人电话
	private String contactPhone;
	//问题描述
	private String problemDescription;
	//发布时间
	private String releaseTime;
	//验证码
	private String graphicCode;
	//是否已处理 0：未处理；1：已处理
	private String processed;

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
	 * 设置：问题编号
	 */
	public void setFaqNo(String faqNo) {
		this.faqNo = faqNo;
	}
	/**
	 * 获取：问题编号
	 */
	public String getFaqNo() {
		return faqNo;
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
	 * 设置：姓名
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * 获取：姓名
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * 设置：联系人电话
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * 获取：联系人电话
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * 设置：问题描述
	 */
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	/**
	 * 获取：问题描述
	 */
	public String getProblemDescription() {
		return problemDescription;
	}
	/**
	 * 设置：发布时间
	 */
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	/**
	 * 获取：发布时间
	 */
	public String getReleaseTime() {
		return releaseTime;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getGraphicCode() {
		return graphicCode;
	}
	public void setGraphicCode(String graphicCode) {
		this.graphicCode = graphicCode;
	}
	public String getProcessed() {
		return processed;
	}
	public void setProcessed(String processed) {
		this.processed = processed;
	}
	
}
