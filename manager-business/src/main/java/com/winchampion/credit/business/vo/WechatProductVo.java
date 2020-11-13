package com.winchampion.credit.business.vo;

import java.io.Serializable;

import com.winchampion.credit.user.utils.DictUtils;




/**
 * 小程序产品
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-08-07 16:19:20
 */
public class WechatProductVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	//
	private String creditProductId;
	
	//
	private String wechatAppid;
	//
	private String wechatUrl;
	
	private String productNo;//产品编码
	private String productName;//产品名称
	private String isRelease;//产品状态
	private String isReleaseCode;
	
	
	  //最后更新时间
    private String updateDate;
    //最后更新人ID
    private String updateBy;
    //最后更新人
    private String updateByName;
    
    //所属金融机构
    private String insName;
    
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getIsRelease() {
		return isRelease;
	}
	public void setIsRelease(String isRelease) {
		this.isRelease = isRelease;
	}
	public String getIsReleaseCode() {
		isReleaseCode = DictUtils.getDictLabel(isRelease, "product_issue_state", "-");
        return isReleaseCode;
	}
	public void setIsReleaseCode(String isReleaseCode) {
		this.isReleaseCode = isReleaseCode;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateByName() {
		return updateByName;
	}
	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
}
