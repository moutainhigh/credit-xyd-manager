package com.winchampion.credit.business.domain;

import com.winchampion.credit.business.domain.base.UserBaseDO;

import java.io.Serializable;

/**
 * 首页合作金融/信用机构表
 * @author liwei
 */
public class HomeFinEntDO extends UserBaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//排序优先级
	private Integer sort;
	//机构LOGO
	private String finFile;
	//机构url
	private String finUrl;
	//机构类型
	private String entType;
	//更新人姓名
	private String updateByName;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getFinFile() {
		return finFile;
	}

	public void setFinFile(String finFile) {
		this.finFile = finFile;
	}

	public String getFinUrl() {
		return finUrl;
	}

	public void setFinUrl(String finUrl) {
		this.finUrl = finUrl;
	}

	public String getEntType() {
		return entType;
	}

	public void setEntType(String entType) {
		this.entType = entType;
	}

	public String getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}
}
