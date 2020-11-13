package com.winchampion.credit.business.domain;

import java.io.Serializable;
import java.util.List;

import com.winchampion.credit.user.utils.DictUtils;

/**
 * 信用产品
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-01 18:18:36
 */
public class CreditProductDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//产品编号
	private String productNo;
	//产品名称
	private String productName;
	//所属信用机构简称
	private String institutionId;
	//所属金融机构名称
	private String insName;
	//机构logo
	private String file;
	//发布状态
	private String isReleaseCode;
	private String isRelease;
	//创建时间
	private String createDate;
	//创建人
	private String createBy;
	//创建人姓名
	private String createName;
	//更新时间
	private String updateDate;
	//更新人
	private String updateBy;
	//更新人姓名
	private String updateName;
	//是否删除
	private String delFlag;
	//产品概述
	private String productInfo;
	//亮点关键词1
	private String keywordOne;
	//亮点关键词2
	private String keywordTwo;
	//亮点关键词3
	private String keywordThree;
	//产品所属板块
	private String sector;
	//回收原因
	private String recycleDes;
	//接收前台详情集合数据
	private String infoStr;
	//详情集合
	private List<CreditProductInfoDO> infoList;
	

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
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	/**
	 * 获取：产品编号
	 */
	public String getProductNo() {
		return productNo;
	}
	/**
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：所属信用机构简称
	 */
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	/**
	 * 获取：所属信用机构简称
	 */
	public String getInstitutionId() {
		return institutionId;
	}
	/**
	 * 设置：发布状态
	 */
	public void setIsRelease(String isRelease) {
		this.isRelease = isRelease;
	}
	/**
	 * 获取：发布状态
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
	 * 设置：是否删除
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置：产品概述
	 */
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	/**
	 * 获取：产品概述
	 */
	public String getProductInfo() {
		return productInfo;
	}
	/**
	 * 设置：亮点关键词1
	 */
	public void setKeywordOne(String keywordOne) {
		this.keywordOne = keywordOne;
	}
	/**
	 * 获取：亮点关键词1
	 */
	public String getKeywordOne() {
		return keywordOne;
	}
	/**
	 * 设置：亮点关键词2
	 */
	public void setKeywordTwo(String keywordTwo) {
		this.keywordTwo = keywordTwo;
	}
	/**
	 * 获取：亮点关键词2
	 */
	public String getKeywordTwo() {
		return keywordTwo;
	}
	/**
	 * 设置：亮点关键词3
	 */
	public void setKeywordThree(String keywordThree) {
		this.keywordThree = keywordThree;
	}
	/**
	 * 获取：亮点关键词3
	 */
	public String getKeywordThree() {
		return keywordThree;
	}
	/**
	 * 设置：产品所属板块
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}
	/**
	 * 获取：产品所属板块
	 */
	public String getSector() {
		return sector;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public String getRecycleDes() {
		return recycleDes;
	}
	public void setRecycleDes(String recycleDes) {
		this.recycleDes = recycleDes;
	}
	public String getInfoStr() {
		return infoStr;
	}
	public void setInfoStr(String infoStr) {
		this.infoStr = infoStr;
	}
	public List<CreditProductInfoDO> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<CreditProductInfoDO> infoList) {
		this.infoList = infoList;
	}
	public String getIsReleaseCode() {
		isReleaseCode = DictUtils.getDictLabel(isRelease, "product_issue_state", "-");
		return isReleaseCode;
	}
	public void setIsReleaseCode(String isReleaseCode) {
		this.isReleaseCode = isReleaseCode;
	}
	
}
