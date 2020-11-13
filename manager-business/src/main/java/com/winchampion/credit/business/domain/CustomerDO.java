package com.winchampion.credit.business.domain;

import com.winchampion.credit.user.utils.DictUtils;
import com.winchampion.credit.user.utils.UserUtils;

import java.io.Serializable;



/***
 * 客户表
 * Created by WIN-CHAMPION China . 
 * @author: zhangxin  
 * @date:2020年2月25日
 * @time:上午10:57:15
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public class CustomerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private String id;
	//客户编码
	private String customerNo;
	//客户姓名
	private String customerName;
	//客户证件号
	private String customerIdNumber;
	//客户手机号
	private String customerPhone;
	//是否实名
	private String isRealName;
	private String isRealNameDesc;
	//认证企业数量
	private Integer authCompanyNum;
	//注册时间
	private String createDate;
	//个人实名认证时间
	private String realNameDate;
	//最后登陆时间
	private String loginDate;
	//在线状态
	private String onlineState;
	//来源描述
	private String sourceDesc;
	
	//来源描述
	private String sourceDesc_;
		
	//实名认证描述
	private String certificationDesc;
	
	//微信openid
	private String openid;
	
	

	/**
	 * 设置：ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：客户编码
	 */
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	/**
	 * 获取：客户编码
	 */
	public String getCustomerNo() {
		return customerNo;
	}
	/**
	 * 设置：客户姓名
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取：客户姓名
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置：客户证件号
	 */
	public void setCustomerIdNumber(String customerIdNumber) {
		this.customerIdNumber = customerIdNumber;
	}
	/**
	 * 获取：客户证件号
	 */
	public String getCustomerIdNumber() {
		return customerIdNumber;
	}
	/**
	 * 设置：客户手机号
	 */
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	/**
	 * 获取：客户手机号
	 */
	public String getCustomerPhone() {
		return customerPhone;
	}
	/**
	 * 设置：是否实名
	 */
	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}
	/**
	 * 获取：是否实名
	 */
	public String getIsRealName() {
		return isRealName;
	}

	public String getIsRealNameDesc() {
		isRealNameDesc = DictUtils.getDictLabel(isRealName, "is_real_name", "-");
		return isRealNameDesc;
	}

	public void setIsRealNameDesc(String isRealNameDesc) {
		this.isRealNameDesc = isRealNameDesc;
	}

	public Integer getAuthCompanyNum() {
		return authCompanyNum;
	}

	public void setAuthCompanyNum(Integer authCompanyNum) {
		this.authCompanyNum = authCompanyNum;
	}

	/**
	 * 设置：注册时间
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：注册时间
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：个人实名认证时间
	 */
	public void setRealNameDate(String realNameDate) {
		this.realNameDate = realNameDate;
	}
	/**
	 * 获取：个人实名认证时间
	 */
	public String getRealNameDate() {
		return realNameDate;
	}
	/**
	 * 设置：最后登陆时间
	 */
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	/**
	 * 获取：最后登陆时间
	 */
	public String getLoginDate() {
		return loginDate;
	}

	public String getOnlineState() {
		onlineState = UserUtils.isOnline(customerPhone) ? "on_line" : "off_line";
		return onlineState;
	}

	public void setOnlineState(String onlineState) {
		this.onlineState = onlineState;
	}
	public String getSourceDesc() {
		return sourceDesc;
	}
	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}
	public String getCertificationDesc() {
		return certificationDesc;
	}
	public void setCertificationDesc(String certificationDesc) {
		this.certificationDesc = certificationDesc;
	}
	@Override
	public String toString() {
		return "CustomerDO [id=" + id + ", customerNo=" + customerNo + ", customerName=" + customerName
				+ ", customerIdNumber=" + customerIdNumber + ", customerPhone=" + customerPhone + ", isRealName="
				+ isRealName + ", isRealNameDesc=" + isRealNameDesc + ", authCompanyNum=" + authCompanyNum
				+ ", createDate=" + createDate + ", realNameDate=" + realNameDate + ", loginDate=" + loginDate
				+ ", onlineState=" + onlineState + ", sourceDesc=" + sourceDesc + ", certificationDesc="
				+ certificationDesc + "]";
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSourceDesc_() {
		sourceDesc_ = DictUtils.getDictLabel(sourceDesc, "source_desc", "网页注册");
		return sourceDesc_;
	}
	public void setSourceDesc_(String sourceDesc_) {
		this.sourceDesc_ = sourceDesc_;
	}
	
	
}
