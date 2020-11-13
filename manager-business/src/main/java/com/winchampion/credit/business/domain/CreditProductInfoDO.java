package com.winchampion.credit.business.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 信用产品
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-01 18:18:37
 */
public class CreditProductInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//产品编号
	private String creditProductId;
	//产品标题
	private String creditProductTitle;
	//产品内容
	private String creditProductInfo;
	//排序
	private Integer sort;

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
	 * 设置：产品编号
	 */
	public void setCreditProductId(String creditProductId) {
		this.creditProductId = creditProductId;
	}
	/**
	 * 获取：产品编号
	 */
	public String getCreditProductId() {
		return creditProductId;
	}
	/**
	 * 设置：产品标题
	 */
	public void setCreditProductTitle(String creditProductTitle) {
		this.creditProductTitle = creditProductTitle;
	}
	/**
	 * 获取：产品标题
	 */
	public String getCreditProductTitle() {
		return creditProductTitle;
	}
	/**
	 * 设置：产品内容
	 */
	public void setCreditProductInfo(String creditProductInfo) {
		this.creditProductInfo = creditProductInfo;
	}
	/**
	 * 获取：产品内容
	 */
	public String getCreditProductInfo() {
		return creditProductInfo;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
}
