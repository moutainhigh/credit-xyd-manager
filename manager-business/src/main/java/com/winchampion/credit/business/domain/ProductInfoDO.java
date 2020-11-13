package com.winchampion.credit.business.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 金融产品详情表
 * 
 * @author zhangcong
 * @email 1992lcg@163.com
 * @date 2020-02-28 14:32:12
 */
public class ProductInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//产品编号
	private String productId;
	//产品详情介绍标题
	private String productTitle;
	//产品详情介绍内容
	private String info;
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
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品编号
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置：产品详情介绍标题
	 */
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	/**
	 * 获取：产品详情介绍标题
	 */
	public String getProductTitle() {
		return productTitle;
	}
	/**
	 * 设置：产品详情介绍内容
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * 获取：产品详情介绍内容
	 */
	public String getInfo() {
		return info;
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
