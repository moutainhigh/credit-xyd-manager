package com.winchampion.credit.business.domain;

import java.io.Serializable;

import com.winchampion.credit.user.domain.UserDO;
import com.winchampion.credit.user.utils.DictUtils;



/****
 * 企业融资需求
 * Created by WIN-CHAMPION China . 
 * @author: zhangxin  
 * @date:2020年2月25日
 * @time:上午10:05:36
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public class CompanyFinanceNeedsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//客户编号
	private String customerId;
	//
	private String id;
	//需求单号
	private String needsNo;
	//融资金额
	private String needsAmount;
	private String needsAmountDesc;

	//融资期限
	private String needsTerm;
	private String needsTermDesc;

	//担保方式
	private String guaranteeMode;
	private String guaranteeModeDesc;
	
	//指定发布金融机构
	private String pubFinIns;
	//企业成立年限
	private String establishedTime;
	private String establishedTimeDesc;
	//是否有抵押物
	private String hasCollateral;
	private String hasCollateralDesc;
	//上年度销售规模
	private String lastSalesScale;
	private String lastSalesScaleDesc;
	//经营地址所在区
	private String addressArea;
	private String addressAreaDesc;
	//企业全称
	private String cname;
	//发布时间
	private String releaseDate;
	//创建时间
	private String createDate;
	//受理状态
	private String acceptStatus;
	private String acceptStatusDes;
	//受理备注(不对外)
	private String acceptRemarks;
	//最后受理时间
	private String acceptDate;
	//最后受理人
	private String acceptBy;
	//企业信息
	private String companyInfo;
	
	private UserDO userDo;
	private CustomerDO customerDO;
	
	public CustomerDO getCustomerDO() {
		return customerDO;
	}
	public void setCustomerDO(CustomerDO customerDO) {
		this.customerDO = customerDO;
	}
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
	 * 设置：需求单号
	 */
	public void setNeedsNo(String needsNo) {
		this.needsNo = needsNo;
	}
	/**
	 * 获取：需求单号
	 */
	public String getNeedsNo() {
		return needsNo;
	}
	/**
	 * 设置：融资金额
	 */
	public void setNeedsAmount(String needsAmount) {
		this.needsAmount = needsAmount;
	}
	/**
	 * 获取：融资金额
	 */
	public String getNeedsAmount() {
		return needsAmount;
	}
	/**
	 * 设置：融资期限
	 */
	public void setNeedsTerm(String needsTerm) {
		this.needsTerm = needsTerm;
	}
	/**
	 * 获取：融资期限
	 */
	public String getNeedsTerm() {
		return needsTerm;
	}
	/**
	 * 设置：担保方式
	 */
	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}
	/**
	 * 获取：担保方式
	 */
	public String getGuaranteeMode() {
		return guaranteeMode;
	}
	/**
	 * 设置：指定发布金融机构
	 */
	public void setPubFinIns(String pubFinIns) {
		this.pubFinIns = pubFinIns;
	}
	/**
	 * 获取：指定发布金融机构
	 */
	public String getPubFinIns() {
		return pubFinIns;
	}
	/**
	 * 设置：企业成立年限
	 */
	public void setEstablishedTime(String establishedTime) {
		this.establishedTime = establishedTime;
	}
	/**
	 * 获取：企业成立年限
	 */
	public String getEstablishedTime() {
		return establishedTime;
	}
	/**
	 * 设置：是否有抵押物
	 */
	public void setHasCollateral(String hasCollateral) {
		this.hasCollateral = hasCollateral;
	}
	/**
	 * 获取：是否有抵押物
	 */
	public String getHasCollateral() {
		return hasCollateral;
	}
	/**
	 * 设置：上年度销售规模
	 */
	public void setLastSalesScale(String lastSalesScale) {
		this.lastSalesScale = lastSalesScale;
	}
	/**
	 * 获取：上年度销售规模
	 */
	public String getLastSalesScale() {
		return lastSalesScale;
	}
	/**
	 * 设置：经营地址所在区
	 */
	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}
	/**
	 * 获取：经营地址所在区
	 */
	public String getAddressArea() {
		return addressArea;
	}
	/**
	 * 设置：企业全称
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 获取：企业全称
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * 设置：发布时间
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	/**
	 * 获取：发布时间
	 */
	public String getReleaseDate() {
		return releaseDate;
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
	 * 设置：受理状态
	 */
	public void setAcceptStatus(String acceptStatus) {
		this.acceptStatus = acceptStatus;
	}
	/**
	 * 获取：受理状态
	 */
	public String getAcceptStatus() {
		return acceptStatus;
	}
	/**
	 * 设置：受理备注(不对外)
	 */
	public void setAcceptRemarks(String acceptRemarks) {
		this.acceptRemarks = acceptRemarks;
	}
	/**
	 * 获取：受理备注(不对外)
	 */
	public String getAcceptRemarks() {
		return acceptRemarks;
	}
	/**
	 * 设置：最后受理时间
	 */
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	/**
	 * 获取：最后受理时间
	 */
	public String getAcceptDate() {
		return acceptDate;
	}
	/**
	 * 设置：最后受理人
	 */
	public void setAcceptBy(String acceptBy) {
		this.acceptBy = acceptBy;
	}
	/**
	 * 获取：最后受理人
	 */
	public String getAcceptBy() {
		return acceptBy;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public UserDO getUserDo() {
		return userDo;
	}
	public void setUserDo(UserDO userDo) {
		this.userDo = userDo;
	}
	public String getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}
	public String getAcceptStatusDes() {
		acceptStatusDes = DictUtils.getDictLabel(acceptStatus, "com_fin_need_type", "-");
		return acceptStatusDes;
	}
	public void setAcceptStatusDes(String acceptStatusDes) {
		this.acceptStatusDes = acceptStatusDes;
	}
	public String getNeedsAmountDesc() {
		needsAmountDesc = DictUtils.getDictLabel(needsAmount, "A004", "-");
		return needsAmountDesc;
	}
	public void setNeedsAmountDesc(String needsAmountDesc) {
		this.needsAmountDesc = needsAmountDesc;
	}
	public String getNeedsTermDesc() {
		needsTermDesc = DictUtils.getDictLabel(needsTerm, "A005", "-");
		return needsTermDesc;
	}
	public void setNeedsTermDesc(String needsTermDesc) {
		this.needsTermDesc = needsTermDesc;
	}
	public String getGuaranteeModeDesc() {
		guaranteeModeDesc = DictUtils.getDictLabel(guaranteeMode, "A006", "-");
		return guaranteeModeDesc;
	}
	public void setGuaranteeModeDesc(String guaranteeModeDesc) {
		this.guaranteeModeDesc = guaranteeModeDesc;
	}
	public String getEstablishedTimeDesc() {
		establishedTimeDesc = DictUtils.getDictLabel(establishedTime, "A007", "-");
		return establishedTimeDesc;
	}
	public void setEstablishedTimeDesc(String establishedTimeDesc) {
		this.establishedTimeDesc = establishedTimeDesc;
	}
	public String getHasCollateralDesc() {
		hasCollateralDesc = DictUtils.getDictLabel(hasCollateral, "A008", "-");
		return hasCollateralDesc;
	}
	public void setHasCollateralDesc(String hasCollateralDesc) {
		this.hasCollateralDesc = hasCollateralDesc;
	}
	public String getLastSalesScaleDesc() {
		lastSalesScaleDesc = DictUtils.getDictLabel(lastSalesScale, "A009", "-");
		return lastSalesScaleDesc;
	}
	public void setLastSalesScaleDesc(String lastSalesScaleDesc) {
		this.lastSalesScaleDesc = lastSalesScaleDesc;
	}
	public String getAddressAreaDesc() {
		addressAreaDesc = DictUtils.getDictLabel(addressArea, "A010", "-");
		return addressAreaDesc;
	}
	public void setAddressAreaDesc(String addressAreaDesc) {
		this.addressAreaDesc = addressAreaDesc;
	}
}
