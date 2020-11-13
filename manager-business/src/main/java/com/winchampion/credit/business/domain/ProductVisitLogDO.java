package com.winchampion.credit.business.domain;

import java.io.Serializable;

/**
 * 产品访问日志
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-10 11:40:34
 */
public class ProductVisitLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//访问来源
	private String ip;
	//访问时间
	private String visitDate;
	//动作
	private String operate;
	//金融产品id
	private String productId;
	//客户id
	private String customerId;

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
	 * 设置：访问来源
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：访问来源
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：访问时间
	 */
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	/**
	 * 获取：访问时间
	 */
	public String getVisitDate() {
		return visitDate;
	}
	/**
	 * 设置：动作
	 */
	public void setOperate(String operate) {
		this.operate = operate;
	}
	/**
	 * 获取：动作
	 */
	public String getOperate() {
		return operate;
	}
	/**
	 * 设置：金融产品id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：金融产品id
	 */
	public String getProductId() {
		return productId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
