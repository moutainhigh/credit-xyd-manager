package com.winchampion.credit.business.domain;

import java.io.Serializable;
import java.util.Date;

import com.winchampion.credit.user.utils.DictUtils;



/**
 * 产品更新日志
 * 
 * @author zhangcong
 * @email 1992lcg@163.com
 * @date 2020-02-28 14:32:12
 */
public class ProductOperateLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//产品编号
	private String productId;
	//操作
	private String operate;
	private String operateDes;
	//操作时间
	private String operateDate;
	//操作人
	private String operateBy;
	//操作人姓名
	private String operateName;
	//操作备注
	private String operateRemark;
	//产品类型 1.金融产品，2信用产品
	private String productType;

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
	 * 设置：操作
	 */
	public void setOperate(String operate) {
		this.operate = operate;
	}
	/**
	 * 获取：操作
	 */
	public String getOperate() {
		return operate;
	}
	/**
	 * 设置：操作时间
	 */
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	/**
	 * 获取：操作时间
	 */
	public String getOperateDate() {
		return operateDate;
	}
	/**
	 * 设置：操作人
	 */
	public void setOperateBy(String operateBy) {
		this.operateBy = operateBy;
	}
	/**
	 * 获取：操作人
	 */
	public String getOperateBy() {
		return operateBy;
	}
	/**
	 * 设置：操作备注
	 */
	public void setOperateRemark(String operateRemark) {
		this.operateRemark = operateRemark;
	}
	/**
	 * 获取：操作备注
	 */
	public String getOperateRemark() {
		return operateRemark;
	}
	/**
	 * 设置：产品类型 1.金融产品，2信用产品
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 获取：产品类型 1.金融产品，2信用产品
	 */
	public String getProductType() {
		return productType;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getOperateDes() {
		operateDes = DictUtils.getDictLabel(operate, "product_operate_log", "-");
		return operateDes;
	}
	public void setOperateDes(String operateDes) {
		this.operateDes = operateDes;
	}
	
}
