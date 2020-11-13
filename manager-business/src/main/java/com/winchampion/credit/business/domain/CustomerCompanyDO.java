package com.winchampion.credit.business.domain;

import com.winchampion.credit.user.utils.DictUtils;

import java.io.Serializable;


/***
 * 客户企业表
 * @author: liwei
 * @date: 2020-02-25
 */
public class CustomerCompanyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private String id;
	//企业唯一标识
	private String companyNo;
	//企业名称
	private String cname;
	//统一社会信用代码
	private String creditCode;
	//是否认证
	private String isAuthentication;
	private String isAuthenticationDesc;
	//创建时间
	private String createDate;
	//最后认证时间
	private String createAuthentication;
	//客户编号
	private String customerId;
	//客户手机号
	private String customerPhone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getIsAuthentication() {
		return isAuthentication;
	}

	public void setIsAuthentication(String isAuthentication) {
		this.isAuthentication = isAuthentication;
	}

	public String getIsAuthenticationDesc() {
		isAuthenticationDesc = DictUtils.getDictLabel(isAuthentication, "is_authentication", "-");
		return isAuthenticationDesc;
	}

	public void setIsAuthenticationDesc(String isAuthenticationDesc) {
		this.isAuthenticationDesc = isAuthenticationDesc;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateAuthentication() {
		return createAuthentication;
	}

	public void setCreateAuthentication(String createAuthentication) {
		this.createAuthentication = createAuthentication;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
}
