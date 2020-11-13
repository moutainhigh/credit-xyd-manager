package com.winchampion.credit.business.domain;

import java.io.Serializable;
import java.util.List;


/**
 * 金融产品智能匹配问题
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-02 13:09:23
 */
public class ProblemDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//问题编号
	private String problemCode;
	//问题简述
	private String problemTitle;
	//可选项
	private String problemOptions;
	//备注
	private String problemRemarks;
	//创建时间
	private String createDate;
	//创建人
	private String createBy;
	//创建人姓名
	private String createName;
	//操作时间
	private String updateDate;
	//操作人
	private String updateBy;
	//修改人姓名
	private String updateName;
	//是否删除
	private String delFlag;
	/**
	 * 用于策略配置
	 */
	private List<String> problemPsList;

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
	 * 设置：问题编号
	 */
	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}
	/**
	 * 获取：问题编号
	 */
	public String getProblemCode() {
		return problemCode;
	}
	/**
	 * 设置：问题简述
	 */
	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}
	/**
	 * 获取：问题简述
	 */
	public String getProblemTitle() {
		return problemTitle;
	}
	/**
	 * 设置：可选项
	 */
	public void setProblemOptions(String problemOptions) {
		this.problemOptions = problemOptions;
	}
	/**
	 * 获取：可选项
	 */
	public String getProblemOptions() {
		return problemOptions;
	}
	/**
	 * 设置：备注
	 */
	public void setProblemRemarks(String problemRemarks) {
		this.problemRemarks = problemRemarks;
	}
	/**
	 * 获取：备注
	 */
	public String getProblemRemarks() {
		return problemRemarks;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：操作时间
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：操作时间
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：操作人
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：操作人
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：是否删除
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除
	 */
	public String getDelFlag() {
		return delFlag;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public List<String> getProblemPsList() {
		return problemPsList;
	}
	public void setProblemPsList(List<String> problemPsList) {
		this.problemPsList = problemPsList;
	}
	
}
