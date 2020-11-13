package com.winchampion.credit.common.message.response;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @Description:变量短信发送响应实体类
 */
public class SmsVariableResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -707373575784335237L;
	/**
	 * 短信回执状态码，判断成功失败的标志
	 */
	@JSONField(name = "Code")
	private String code;
	/**
	 * 短信回执状态描述
	 */
	@JSONField(name = "Message")
	private String message;
	/**
	 * 短信回执编号，为唯一识别码，用户可通过此编号获取记录详情
	 */
	@JSONField(name = "SendId")
	private String sendId;
	/**
	 * 无效号码数量
	 */
	@JSONField(name = "InvalidCount")
	private String invalidCount;
	/**
	 * 有效号码数量
	 */
	@JSONField(name = "SuccessCount")
	private String successCount;
	/**
	 * 黑名单号码数量
	 */
	@JSONField(name = "BlackCount")
	private String blackCount;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getInvalidCount() {
		return invalidCount;
	}
	public void setInvalidCount(String invalidCount) {
		this.invalidCount = invalidCount;
	}
	public String getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}
	public String getBlackCount() {
		return blackCount;
	}
	public void setBlackCount(String blackCount) {
		this.blackCount = blackCount;
	}
	@Override
	public String toString() {
		return "SmsVariableResponse [code=" + code + ", message=" + message + ", sendId=" + sendId + ", invalidCount="
				+ invalidCount + ", successCount=" + successCount + ", blackCount=" + blackCount + "]";
	}
	
	
	
}
