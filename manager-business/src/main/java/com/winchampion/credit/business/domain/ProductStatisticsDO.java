package com.winchampion.credit.business.domain;

import java.io.Serializable;


/**
 * 统计数字临时表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-10-30 12:02:15
 */
public class ProductStatisticsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//所属机构
	private String organCode;
	//所属月份：格式：202004
	private String monthNum;
	//开始时间
	private String startTime;
	//结束时间
	private String endTime;
	//统计类型：1：累计申请次数；2：累计授信金额；3：注册用户数
	private String statisticsType;
	//统计数
	private Double statisticsCount;
	//删除标识：0不删除；1：删除
	private String delFlag;

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
	 * 设置：所属机构
	 */
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}
	/**
	 * 获取：所属机构
	 */
	public String getOrganCode() {
		return organCode;
	}
	/**
	 * 设置：所属月份：格式：202004
	 */
	public void setMonthNum(String monthNum) {
		this.monthNum = monthNum;
	}
	/**
	 * 获取：所属月份：格式：202004
	 */
	public String getMonthNum() {
		return monthNum;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 设置：统计类型：1：累计申请次数；2：累计授信金额；3：注册用户数
	 */
	public void setStatisticsType(String statisticsType) {
		this.statisticsType = statisticsType;
	}
	/**
	 * 获取：统计类型：1：累计申请次数；2：累计授信金额；3：注册用户数
	 */
	public String getStatisticsType() {
		return statisticsType;
	}
	/**
	 * 设置：统计数
	 */
	public void setStatisticsCount(Double statisticsCount) {
		this.statisticsCount = statisticsCount;
	}
	/**
	 * 获取：统计数
	 */
	public Double getStatisticsCount() {
		return statisticsCount;
	}
	/**
	 * 设置：删除标识：0不删除；1：删除
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：删除标识：0不删除；1：删除
	 */
	public String getDelFlag() {
		return delFlag;
	}
}
