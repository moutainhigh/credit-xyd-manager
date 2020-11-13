package com.winchampion.credit.business.vo;

/**
 * 个人用户注册或完善接口处理结果
 * @author zhangcong
 *
 */
public class RegisterInterVo {

	private String result;//处理结果

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RegisterInterVo [result=" + result + "]";
	}
	
}
