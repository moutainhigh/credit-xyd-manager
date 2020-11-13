package com.winchampion.credit.business.vo;

import com.winchampion.credit.user.utils.DictUtils;

import java.io.Serializable;

/**
 * 首页热门、企业、个人产品 Vo
 * @author liwei
 */
public class HouseProductEditVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //产品Id
    private String id;
    //排序
    private String sort;
    //产品编号
    private String productNo;
    //产品名称
    private String productName;
    //所属金融机构Id
    private String insId;
    //所属金融机构
    private String insName;
    //产品状态
    private String isRelease;
    private String isReleaseDesc;
    //最后更新时间
    private String updateDate;
    //最后更新人ID
    private String updateBy;
    //最后更新人
    private String updateByName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

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

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    public String getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(String isRelease) {
        this.isRelease = isRelease;
    }

    public String getIsReleaseDesc() {
        isReleaseDesc = DictUtils.getDictLabel(isRelease, "product_issue_state", "-");
        return isReleaseDesc;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }
}
