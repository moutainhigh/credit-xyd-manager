package com.winchampion.credit.business.domain;

import java.io.Serializable;

import com.winchampion.credit.business.domain.base.UserBaseDO;



/**
 * 小程序产品
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-08-07 16:19:20
 */
public class WechatProductDO extends UserBaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String creditProductId;
	//
	private String wechatAppid;
	//
	private String wechatUrl;
	
	/**
	 * 设置：
	 */
	public void setCreditProductId(String creditProductId) {
		this.creditProductId = creditProductId;
	}
	/**
	 * 获取：
	 */
	public String getCreditProductId() {
		return creditProductId;
	}
	/**
	 * 设置：
	 */
	public void setWechatAppid(String wechatAppid) {
		this.wechatAppid = wechatAppid;
	}
	/**
	 * 获取：
	 */
	public String getWechatAppid() {
		return wechatAppid;
	}
	/**
	 * 设置：
	 */
	public void setWechatUrl(String wechatUrl) {
		this.wechatUrl = wechatUrl;
	}
	/**
	 * 获取：
	 */
	public String getWechatUrl() {
		return wechatUrl;
	}
}
