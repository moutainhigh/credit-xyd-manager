package com.winchampion.credit.business.domain;

import java.io.Serializable;

import com.winchampion.credit.business.domain.base.UserBaseDO;
import com.winchampion.credit.user.utils.DictUtils;



/**
 * 跑马灯表
 * 
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-27 15:05:36
 */
public class HorseDO extends UserBaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	//跑马灯内容
	private String horse;
	//排序优先级
	private Integer sort;
	//是否发布
	private String isRelease;
	private String isReleaseDesc;
	/**
	 * 设置：跑马灯内容
	 */
	public void setHorse(String horse) {
		this.horse = horse;
	}
	/**
	 * 获取：跑马灯内容
	 */
	public String getHorse() {
		return horse;
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
	/**
	 * 设置：是否发布
	 */
	public void setIsRelease(String isRelease) {
		this.isRelease = isRelease;
	}
	/**
	 * 获取：是否发布
	 */
	public String getIsRelease() {
		return isRelease;
	}
	
	public String getIsReleaseDesc() {
		isReleaseDesc = DictUtils.getDictLabel(isRelease, "is_release", "-");
		return isReleaseDesc;
	}
	public void setIsReleaseDesc(String isReleaseDesc) {
		this.isReleaseDesc = isReleaseDesc;
	}
	
}
