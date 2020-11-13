package com.winchampion.credit.business.domain;

import java.io.Serializable;


/***
 * 客户企业认证状态更新表
 * @author: liwei
 * @date: 2020-03-05
 */
public class CustomerCompanyAuthHisDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private String id;
	//企业id
	private String companyId;
	//企业唯一标识
	private String companyNo;
	//认证时间/过期时间
	private String authDate;
	//认证结果
	private String authResult;
	private String authResultDesc;
	//变更时的企业名称
	private String cname;
	//变更时的统一社会信用代码
	private String creditCode;
	//客户编号
	private String customerId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getAuthDate() {
		return authDate;
	}

	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}

	public String getAuthResult() {
		return authResult;
	}

	public void setAuthResult(String authResult) {
		this.authResult = authResult;
	}

	public String getAuthResultDesc() {
		if("1".equals(authResult)){
			return "已认证";
		}
		return "认证过期";
	}

	public void setAuthResultDesc(String authResultDesc) {
		this.authResultDesc = authResultDesc;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
