package com.winchampion.credit.business.vo;

import java.io.Serializable;

import com.winchampion.credit.user.utils.DictUtils;

/**
 * 产品关注热度
 * @author zhangcong
 *
 */
public class ProductHeatVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//产品id
	private String productNo;//产品编码
	private String productName;//产品名称
	private String isRelease;//产品状态
	private String isReleaseCode;
	private int examineCount;//查看次数
	private int applyForCount;//申请次数
	private int heat;//产品关注热度
	
	private String startTime;//查询开始时间
	private String endTime;//查询结束时间
	
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
	public int getExamineCount() {
		return examineCount;
	}
	public void setExamineCount(int examineCount) {
		this.examineCount = examineCount;
	}
	public int getApplyForCount() {
		return applyForCount;
	}
	public void setApplyForCount(int applyForCount) {
		this.applyForCount = applyForCount;
	}
	public int getHeat() {
		heat = examineCount + applyForCount*5;
		return heat;
	}
	public void setHeat(int heat) {
		this.heat = heat;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIsReleaseCode() {
		isReleaseCode = DictUtils.getDictLabel(isRelease, "product_issue_state", "-");
		return isReleaseCode;
	}
	public void setIsReleaseCode(String isReleaseCode) {
		this.isReleaseCode = isReleaseCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
