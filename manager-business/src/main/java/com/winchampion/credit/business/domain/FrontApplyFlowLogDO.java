package com.winchampion.credit.business.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import com.winchampion.credit.business.Enum.ProductCourseEnum;
import com.winchampion.credit.common.util.StringUtils;



/**
 * 产品申请流程表（前台）
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-04 16:20:45
 */
public class FrontApplyFlowLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private String id;
	//申请单号
	private String applyOdd;
	//发生时间
	private String occurTime;
	//记录信息
	private String logMessage;
	//替换值
	private String replacementValue;

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
	 * 设置：申请单号
	 */
	public void setApplyOdd(String applyOdd) {
		this.applyOdd = applyOdd;
	}
	/**
	 * 获取：申请单号
	 */
	public String getApplyOdd() {
		return applyOdd;
	}
	/**
	 * 设置：发生时间
	 */
	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}
	/**
	 * 获取：发生时间
	 */
	public String getOccurTime() {
		return occurTime;
	}
	/**
	 * 设置：记录信息
	 */
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	/**
	 * 获取：记录信息
	 */
	public String getLogMessage() {
		if(StringUtils.isNotEmpty(replacementValue)) {
			logMessage = ProductCourseEnum.getMsgStr(logMessage);
			return String.format(logMessage, replacementValue.split(";"));
		}else {
			return logMessage = ProductCourseEnum.getMsgStr(logMessage);
		}
	}
	/**
	 * 设置：替换值
	 */
	public void setReplacementValue(String replacementValue) {
		this.replacementValue = replacementValue;
	}
	/**
	 * 获取：替换值
	 */
	public String getReplacementValue() {
		return replacementValue;
	}
}
