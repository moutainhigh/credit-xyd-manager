package com.winchampion.credit.business.domain;

import com.winchampion.credit.business.domain.base.UserBaseDO;

import java.io.Serializable;
import java.util.Date;

/**
 * 合作机构管理
 * 
 * @author zhangcong
 * @email 1992lcg@163.com
 * @date 2020-02-28 21:12:41
 */
public class InsDO extends UserBaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//统一社会信用代码
	private String creditCode;
	//机构简称
	private String insName;
	//产品数量
	private Integer productCount;
	//机构图
	private String file;
	//备注
	private String remark;
	//更新人
	private String updateUserName;
	//机构类型
	private String insType;
	//机构对外简称
	private String creditInfo;

	/**
	 * 设置：统一社会信用代码
	 */
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	/**
	 * 获取：统一社会信用代码
	 */
	public String getCreditCode() {
		return creditCode;
	}
	/**
	 * 设置：机构简称
	 */
	public void setInsName(String insName) {
		this.insName = insName;
	}
	/**
	 * 获取：机构简称
	 */
	public String getInsName() {
		return insName;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	/**
	 * 设置：机构图
	 */
	public void setFile(String file) {
		this.file = file;
	}
	/**
	 * 获取：机构图
	 */
	public String getFile() {
		return file;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	/**
	 * 设置：机构类型
	 */
	public void setInsType(String insType) {
		this.insType = insType;
	}
	/**
	 * 获取：机构类型
	 */
	public String getInsType() {
		return insType;
	}
	/**
	 * 设置：机构对外简称
	 */
	public void setCreditInfo(String creditInfo) {
		this.creditInfo = creditInfo;
	}
	/**
	 * 获取：机构对外简称
	 */
	public String getCreditInfo() {
		return creditInfo;
	}
}
