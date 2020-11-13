package com.winchampion.credit.business.domain;

import java.io.Serializable;
import java.util.List;


/**
 * 产品及智能问题匹配策略
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-02 19:32:53
 */
public class ProductProblemStrategyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//产品编号
	private String productId;
	//问题编号
	private String problemId;
	//匹配选项
	private String matchOption;
	//前台问题值
	private String homeValue;
	//产品编号
	private String productNo;
	//产品名称
	private String productName;
	//所属金融机构
	private String insName;
	
	//问题的可选项
	private List<ProblemDO> problemList;
	
	private String problemPs;
	//最后更新人
	private String updateName;
	//最后更新时间
	private String updateDate;
	//private String problemOptions;
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
	 * 设置：产品编号
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品编号
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置：问题编号
	 */
	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
	/**
	 * 获取：问题编号
	 */
	public String getProblemId() {
		return problemId;
	}
	/**
	 * 设置：匹配选项
	 */
	public void setMatchOption(String matchOption) {
		this.matchOption = matchOption;
	}
	/**
	 * 获取：匹配选项
	 */
	public String getMatchOption() {
		return matchOption;
	}
	/**
	 * 设置：前台问题值
	 */
	public void setHomeValue(String homeValue) {
		this.homeValue = homeValue;
	}
	/**
	 * 获取：前台问题值
	 */
	public String getHomeValue() {
		return homeValue;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public List<ProblemDO> getProblemList() {
		return problemList;
	}
	public void setProblemList(List<ProblemDO> problemList) {
		this.problemList = problemList;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getProblemPs() {
		return problemPs;
	}
	public void setProblemPs(String problemPs) {
		this.problemPs = problemPs;
	}
	
}
