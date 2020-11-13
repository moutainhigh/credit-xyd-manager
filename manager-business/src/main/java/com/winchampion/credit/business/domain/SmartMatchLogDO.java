package com.winchampion.credit.business.domain;

import java.io.Serializable;


/**
 * 智能匹配日志
 * 
 * @author zhangcong
 * @email 15121007361@163.com
 * @date 2020-04-20 10:42:35
 */
public class SmartMatchLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//客户编号
	private String customerId;
	//
	private String id;
	//匹配类型 1.个人。2法人企业
	private String matchType;
	//企业名称
	private String cname;
	//需求
	private String needs;
	//匹配产品
	private String matchResult;
	//匹配轮次 1. 完全匹配，2 2次匹配，3未匹配
	private String matchRounds;
	//匹配时间
	private String matchDate;
	//账户姓名
	private String customerName;
	//账户手机号
	private String customerPhone;

	/**
	 * 设置：客户编号
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：客户编号
	 */
	public String getCustomerId() {
		return customerId;
	}
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
	/**
	 * 设置：匹配类型 1.个人。2法人企业
	 */
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	/**
	 * 获取：匹配类型 1.个人。2法人企业
	 */
	public String getMatchType() {
		return matchType;
	}
	/**
	 * 设置：企业名称
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 获取：企业名称
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * 设置：需求
	 */
	public void setNeeds(String needs) {
		this.needs = needs;
	}
	/**
	 * 获取：需求
	 */
	public String getNeeds() {
		return needs;
	}
	/**
	 * 设置：匹配产品
	 */
	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}
	/**
	 * 获取：匹配产品
	 */
	public String getMatchResult() {
		return matchResult;
	}
	/**
	 * 设置：匹配轮次 1. 完全匹配，2 2次匹配，3未匹配
	 */
	public void setMatchRounds(String matchRounds) {
		this.matchRounds = matchRounds;
	}
	/**
	 * 获取：匹配轮次 1. 完全匹配，2 2次匹配，3未匹配
	 */
	public String getMatchRounds() {
		return matchRounds;
	}
	/**
	 * 设置：匹配时间
	 */
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	/**
	 * 获取：匹配时间
	 */
	public String getMatchDate() {
		return matchDate;
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
	
}
