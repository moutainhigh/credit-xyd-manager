package com.winchampion.credit.business.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.util.StringUtils;
import com.winchampion.credit.user.domain.DictDO;
import com.winchampion.credit.user.utils.DictUtils;


/**
 * 金融产品表
 * 
 * @author zhangcong
 * @email 15121007361@163.com
 * @date 2020-02-27 12:06:01
 */
public class ProductDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//产品编号
	private String productNo;
	//产品名称
	private String productName;
	//产品类型
	private String productType;
	//所属金融机构
	private String finEntId;
	//所属金融机构名称
	private String insName;
	//机构logo
	private String file;
	//发布状态
	private String isReleaseCode;
	private String isRelease;
	//创建时间
	private String createDate;
	//创建人
	private String createBy;
	//创建人
	private String createName;
	//更新时间
	private String updateDate;
	//更新人
	private String updateBy;
	//更新人姓名
	private String updateName;
	//是否删除
	private String delFlag;
	//是否首页热门
	private String isHomeHot;
	//是否首页企业
	private String isHomeCom;
	//是否首页个人
	private String isHomePer;
	//排序
	private String sort;
	//参考最低可贷额度 单位：元
	private String minAvailable;
	//参考最低可贷额度 单位：万元
	private String minAvailableConvert;
	//参考最高可贷额度 单位:元
	private String maxAvailable;
	//参考最高可贷额度 单位:万元
	private String maxAvailableConvert;
	//参考最低贷款利率
	private String minRate;
	//参考最高贷款利率
	private String maxRate;
	//最短贷款期限
	private String minLoanTerm;
	//最长贷款期限
	private String maxLoanTerm;
	//适用地区
	private String area;
	//最快放款简述
	private String quickRelease;
	//还款方式
	private String repaymentMethod;
	//担保方式
	private String guaranteeMode;
	//产品审批类型
	private String approvalType;
	//第一行简介
	private String firstLine;
	//第二行简介
	private String secondLine;
	//第三行简介
	private String thirdLine;
	//亮点关键词1
	private String keywordsOne;
	//亮点关键词2
	private String keywordsTwo;
	//亮点关键词3
	private String keywordsThree;
	//产品所属板块
	private String sector;
	//申请类型
	private String applicationType;
	//二维码
	private String qrFile;
	//联系电话
	private String contactInf;
	//联系人姓名
	private String contactName;
	//回收原因
	private String recycleDes;
	//策略最后更新人
	private String strategyUpdateBy;
	//策略更新时间
	private String strategyUpdateDate;
	//关注度
	private int heat;
	//最短贷款期限前台是否展示 1:不展示
	private String minLoanTermShow;
	//参考最高贷款利率前台是否展示 1:不展示
	private String maxRateShow;
	//参考利率对外描述
	private String rateDescribing;
	
	//接收前台详情集合数据
	private String infoStr;
	//详情集合
	private List<ProductInfoDO> infoList;
	
	/***************************对外显示***********************/
	//申请额度
	private String applyLimit;
	//申请期限
	private String applyDeadline;
	//参考利率
	private String referenceRate;
	//担保方式
	private String guarantyStyle;
	//还款方式
	private String repaymentStyle;
	

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
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	/**
	 * 获取：产品编号
	 */
	public String getProductNo() {
		return productNo;
	}
	/**
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：产品类型
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 获取：产品类型
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * 设置：所属金融机构
	 */
	public void setFinEntId(String finEntId) {
		this.finEntId = finEntId;
	}
	/**
	 * 获取：所属金融机构
	 */
	public String getFinEntId() {
		return finEntId;
	}
	/**
	 * 设置：发布状态
	 */
	public void setIsRelease(String isRelease) {
		this.isRelease = isRelease;
	}
	/**
	 * 获取：发布状态
	 */
	public String getIsRelease() {
		return isRelease;
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
	 * 设置：更新时间
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新人
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
	/**
	 * 设置：是否首页热门
	 */
	public void setIsHomeHot(String isHomeHot) {
		this.isHomeHot = isHomeHot;
	}
	/**
	 * 获取：是否首页热门
	 */
	public String getIsHomeHot() {
		return isHomeHot;
	}
	/**
	 * 设置：是否首页企业
	 */
	public void setIsHomeCom(String isHomeCom) {
		this.isHomeCom = isHomeCom;
	}
	/**
	 * 获取：是否首页企业
	 */
	public String getIsHomeCom() {
		return isHomeCom;
	}
	/**
	 * 设置：是否首页个人
	 */
	public void setIsHomePer(String isHomePer) {
		this.isHomePer = isHomePer;
	}
	/**
	 * 获取：是否首页个人
	 */
	public String getIsHomePer() {
		return isHomePer;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * 设置：参考最低可贷额度
	 */
	public void setMinAvailable(String minAvailable) {
		this.minAvailable = minAvailable;
	}
	/**
	 * 获取：参考最低可贷额度
	 */
	public String getMinAvailable() {
		return minAvailable;
	}
	/**
	 * 设置：参考最高可贷额度
	 */
	public void setMaxAvailable(String maxAvailable) {
		this.maxAvailable = maxAvailable;
	}
	/**
	 * 获取：参考最高可贷额度
	 */
	public String getMaxAvailable() {
		return maxAvailable;
	}
	
	public String getMinAvailableConvert() {
		minAvailableConvert = StringUtils.money(minAvailable);
		return minAvailableConvert;
	}
	public void setMinAvailableConvert(String minAvailableConvert) {
		this.minAvailableConvert = minAvailableConvert;
	}
	public String getMaxAvailableConvert() {
		maxAvailableConvert = StringUtils.money(maxAvailable);
		return maxAvailableConvert;
	}
	public void setMaxAvailableConvert(String maxAvailableConvert) {
		this.maxAvailableConvert = maxAvailableConvert;
	}
	/**
	 * 设置：参考最低贷款利率
	 */
	public void setMinRate(String minRate) {
		this.minRate = minRate;
	}
	/**
	 * 获取：参考最低贷款利率
	 */
	public String getMinRate() {
		return minRate;
	}
	/**
	 * 设置：参考最高贷款利率
	 */
	public void setMaxRate(String maxRate) {
		this.maxRate = maxRate;
	}
	/**
	 * 获取：参考最高贷款利率
	 */
	public String getMaxRate() {
		return maxRate;
	}
	/**
	 * 设置：最短贷款期限
	 */
	public void setMinLoanTerm(String minLoanTerm) {
		this.minLoanTerm = minLoanTerm;
	}
	/**
	 * 获取：最短贷款期限
	 */
	public String getMinLoanTerm() {
		return minLoanTerm;
	}
	/**
	 * 设置：最长贷款期限
	 */
	public void setMaxLoanTerm(String maxLoanTerm) {
		this.maxLoanTerm = maxLoanTerm;
	}
	/**
	 * 获取：最长贷款期限
	 */
	public String getMaxLoanTerm() {
		return maxLoanTerm;
	}
	/**
	 * 设置：适用地区
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取：适用地区
	 */
	public String getArea() {
		return area;
	}
	/**
	 * 设置：最快放款简述
	 */
	public void setQuickRelease(String quickRelease) {
		this.quickRelease = quickRelease;
	}
	/**
	 * 获取：最快放款简述
	 */
	public String getQuickRelease() {
		return quickRelease;
	}
	/**
	 * 设置：还款方式
	 */
	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}
	/**
	 * 获取：还款方式
	 */
	public String getRepaymentMethod() {
		return repaymentMethod;
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
	 * 设置：产品审批类型
	 */
	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}
	/**
	 * 获取：产品审批类型
	 */
	public String getApprovalType() {
		return approvalType;
	}
	/**
	 * 设置：第一行简介
	 */
	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}
	/**
	 * 获取：第一行简介
	 */
	public String getFirstLine() {
		return firstLine;
	}
	/**
	 * 设置：第二行简介
	 */
	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}
	/**
	 * 获取：第二行简介
	 */
	public String getSecondLine() {
		return secondLine;
	}
	/**
	 * 设置：第三行简介
	 */
	public void setThirdLine(String thirdLine) {
		this.thirdLine = thirdLine;
	}
	/**
	 * 获取：第三行简介
	 */
	public String getThirdLine() {
		return thirdLine;
	}
	/**
	 * 设置：亮点关键词1
	 */
	public void setKeywordsOne(String keywordsOne) {
		this.keywordsOne = keywordsOne;
	}
	/**
	 * 获取：亮点关键词1
	 */
	public String getKeywordsOne() {
		return keywordsOne;
	}
	/**
	 * 设置：亮点关键词2
	 */
	public void setKeywordsTwo(String keywordsTwo) {
		this.keywordsTwo = keywordsTwo;
	}
	/**
	 * 获取：亮点关键词2
	 */
	public String getKeywordsTwo() {
		return keywordsTwo;
	}
	/**
	 * 设置：亮点关键词3
	 */
	public void setKeywordsThree(String keywordsThree) {
		this.keywordsThree = keywordsThree;
	}
	/**
	 * 获取：亮点关键词3
	 */
	public String getKeywordsThree() {
		return keywordsThree;
	}
	/**
	 * 设置：产品所属板块
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}
	/**
	 * 获取：产品所属板块
	 */
	public String getSector() {
		return sector;
	}
	/**
	 * 设置：申请类型
	 */
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	/**
	 * 获取：申请类型
	 */
	public String getApplicationType() {
		return applicationType;
	}
	/**
	 * 设置：二维码
	 */
	public void setQrFile(String qrFile) {
		this.qrFile = qrFile;
	}
	/**
	 * 获取：二维码
	 */
	public String getQrFile() {
		return qrFile;
	}
	/**
	 * 设置：联系方式
	 */
	public void setContactInf(String contactInf) {
		this.contactInf = contactInf;
	}
	/**
	 * 获取：联系方式
	 */
	public String getContactInf() {
		return contactInf;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public String getRecycleDes() {
		return recycleDes;
	}
	public void setRecycleDes(String recycleDes) {
		this.recycleDes = recycleDes;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public List<ProductInfoDO> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<ProductInfoDO> infoList) {
		this.infoList = infoList;
	}
	public String getInfoStr() {
		return infoStr;
	}
	public void setInfoStr(String infoStr) {
		this.infoStr = infoStr;
	}
	public String getStrategyUpdateBy() {
		return strategyUpdateBy;
	}
	public void setStrategyUpdateBy(String strategyUpdateBy) {
		this.strategyUpdateBy = strategyUpdateBy;
	}
	public String getStrategyUpdateDate() {
		return strategyUpdateDate;
	}
	public void setStrategyUpdateDate(String strategyUpdateDate) {
		this.strategyUpdateDate = strategyUpdateDate;
	}
	public String getIsReleaseCode() {
		isReleaseCode = DictUtils.getDictLabel(isRelease, "product_issue_state", "-");
		return isReleaseCode;
	}
	public void setIsReleaseCode(String isReleaseCode) {
		this.isReleaseCode = isReleaseCode;
	}
	public int getHeat() {
		return heat;
	}
	public void setHeat(int heat) {
		this.heat = heat;
	}
	public String getMinLoanTermShow() {
		return minLoanTermShow;
	}
	public void setMinLoanTermShow(String minLoanTermShow) {
		this.minLoanTermShow = minLoanTermShow;
	}
	public String getMaxRateShow() {
		return maxRateShow;
	}
	public void setMaxRateShow(String maxRateShow) {
		this.maxRateShow = maxRateShow;
	}
	public String getRateDescribing() {
		return rateDescribing;
	}
	public void setRateDescribing(String rateDescribing) {
		this.rateDescribing = rateDescribing;
	}
	public String getApplyLimit() {
		if(StringUtils.isEmpty(maxAvailableConvert)) {
			maxAvailableConvert = StringUtils.money(maxAvailable);
		}
		applyLimit = "最高"+maxAvailableConvert+"万";
		return applyLimit;
	}
	public void setApplyLimit(String applyLimit) {
		this.applyLimit = applyLimit;
	}
	public String getApplyDeadline() {
		if(StringUtils.isNotEmpty(minLoanTermShow)&&minLoanTermShow.equals(Constant.SHOW)) {//最短贷款期限不展示
			applyDeadline = "最长"+maxLoanTerm+"月";
		}else {
			applyDeadline = minLoanTerm+"-"+maxLoanTerm+"月";
		}
		return applyDeadline;
	}
	public void setApplyDeadline(String applyDeadline) {
		this.applyDeadline = applyDeadline;
	}
	public String getReferenceRate() {
		if(StringUtils.isNotEmpty(rateDescribing)) {
			referenceRate = rateDescribing;
		}else {
			if(StringUtils.isNotEmpty(maxRateShow)&&maxRateShow.equals(Constant.SHOW)) {//最高贷款利率不展示
				referenceRate = minRate+"%起";
			}else {
				referenceRate = minRate+"-"+maxRate+"%";
			}
		}
		return referenceRate;
	}
	
	public void setReferenceRate(String referenceRate) {
		this.referenceRate = referenceRate;
	}
	public String getGuarantyStyle() {
		guarantyStyle = "";
		if(StringUtils.isNotEmpty(guaranteeMode)) {
			List<DictDO> list = DictUtils.getDictList("guaranty_style");
			String[] str = guaranteeMode.split(",");
			if(str!=null&&str.length>0&&!CollectionUtils.isEmpty(list)) {
				for (int i=0;i<str.length;i++) {
					for (DictDO dict : list) {
						if(str[i].equals(dict.getValue())) {
							guarantyStyle = guarantyStyle+dict.getName();
							break;
						}
					}
					if(i!=str.length-1) {
						guarantyStyle = guarantyStyle+"、";
					}
				}
				return guarantyStyle;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	public void setGuarantyStyle(String guarantyStyle) {
		this.guarantyStyle = guarantyStyle;
	}
	public String getRepaymentStyle() {
		repaymentStyle = "";
		if(StringUtils.isNotEmpty(repaymentMethod)) {
			List<DictDO> list = DictUtils.getDictList("payment_method");
			String[] str = repaymentMethod.split(",");
			if(str!=null&&str.length>0&&!CollectionUtils.isEmpty(list)) {
				for (int i=0;i<str.length;i++) {
					for (DictDO dict : list) {
						if(str[i].equals(dict.getValue())) {
							repaymentStyle = repaymentStyle+dict.getName();
							break;
						}
					}
					if(i!=str.length-1) {
						repaymentStyle = repaymentStyle+"、";
					}
				}
				return repaymentStyle;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	public void setRepaymentStyle(String repaymentStyle) {
		this.repaymentStyle = repaymentStyle;
	}
	
}
