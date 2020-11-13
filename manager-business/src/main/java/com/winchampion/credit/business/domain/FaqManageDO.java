package com.winchampion.credit.business.domain;

import java.io.Serializable;

import com.winchampion.credit.user.utils.DictUtils;

/**
 * 常见问题管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-03 16:58:56
 */
public class FaqManageDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private String id;
	//问题编号 暂时存放主键id
	private String faqNo;
	//标题
	private String faqTitle;
	//排序优先级
	private String sort;
	//内容
	private String faqContent;
	//发布状态 0：未发布；1：已发布
	private String isRelease;
	private String isReleaseCode;
	//创建时间
	private String createDate;
	//创建人
	private String createBy;
	//更新时间
	private String updateDate;
	//更新人
	private String updateBy;
	//更新人姓名
	private String updateByName;
	//是否删除 0：未删除；1：已删除
	private String delFlag;

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
	 * 设置：问题编号 暂时存放主键id
	 */
	public void setFaqNo(String faqNo) {
		this.faqNo = faqNo;
	}
	/**
	 * 获取：问题编号 暂时存放主键id
	 */
	public String getFaqNo() {
		return faqNo;
	}
	/**
	 * 设置：标题
	 */
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
	/**
	 * 获取：标题
	 */
	public String getFaqTitle() {
		return faqTitle;
	}
	/**
	 * 设置：排序优先级
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序优先级
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * 设置：内容
	 */
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	/**
	 * 获取：内容
	 */
	public String getFaqContent() {
		return faqContent;
	}
	/**
	 * 设置：发布状态 0：未发布；1：已发布
	 */
	public void setIsRelease(String isRelease) {
		this.isRelease = isRelease;
	}
	/**
	 * 获取：发布状态 0：未发布；1：已发布
	 */
	public String getIsRelease() {
		return isRelease;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：是否删除 0：未删除；1：已删除
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除 0：未删除；1：已删除
	 */
	public String getDelFlag() {
		return delFlag;
	}
	public String getIsReleaseCode() {
		isReleaseCode = DictUtils.getDictLabel(isRelease, "product_issue_state", "-");
		return isReleaseCode;
	}
	public void setIsReleaseCode(String isReleaseCode) {
		this.isReleaseCode = isReleaseCode;
	}
	public String getUpdateByName() {
		return updateByName;
	}
	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}
	
}
