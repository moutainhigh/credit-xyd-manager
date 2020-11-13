package com.winchampion.credit.business.vo;
/**
 * 用户认证信息补充vo
 * @author zhangcong
 *
 */
public class CustomerReplenishVo {
	private String pname;//姓名
	private String certiCode;//身份证号
	private String phoneNo;//手机号
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCertiCode() {
		return certiCode;
	}
	public void setCertiCode(String certiCode) {
		this.certiCode = certiCode;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "CustomerReplenishVo [pname=" + pname + ", certiCode=" + certiCode + ", phoneNo=" + phoneNo + "]";
	}
	
	
}
