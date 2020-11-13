package com.winchampion.credit.business.domain;

import java.io.Serializable;

import com.winchampion.credit.business.domain.base.UserBaseDO;



/**
 * 企业融资需求可发布金融机构
 * 
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-29 10:58:35
 */
public class CompanyFinEntDO extends UserBaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//机构名称
	private String finName;
	//排序优先级
	private Integer sort;

	/**
	 * 设置：机构名称
	 */
	public void setFinName(String finName) {
		this.finName = finName;
	}
	/**
	 * 获取：机构名称
	 */
	public String getFinName() {
		return finName;
	}
	/**
	 * 设置：排序优先级
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序优先级
	 */
	public Integer getSort() {
		return sort;
	}
}
