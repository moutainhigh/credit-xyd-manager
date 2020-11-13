package com.winchampion.credit.common.interfaces.country.req;

import java.math.BigDecimal;

public class FinancialInstitutionStatistics {
    /**
     * 单位笔，取整数、
     * 单位家，取整数、
     * 单位为万元，保
     * 留两位小数
     */

    //金融机构名称
    private String name;

    //各金融机构放款金额
    private BigDecimal loanAmount;

    //各金融机构放款笔数
    private Integer loanNum;

    //各金融机构放款
    private Integer enterpriseNu;

    //各金融机构受理融资需求笔数
    private Integer needsNum;

    //各金融机构受理需求金额
    private BigDecimal needsAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(Integer loanNum) {
        this.loanNum = loanNum;
    }

    public Integer getEnterpriseNu() {
        return enterpriseNu;
    }

    public void setEnterpriseNu(Integer enterpriseNu) {
        this.enterpriseNu = enterpriseNu;
    }

    public Integer getNeedsNum() {
        return needsNum;
    }

    public void setNeedsNum(Integer needsNum) {
        this.needsNum = needsNum;
    }

    public BigDecimal getNeedsAmount() {
        return needsAmount;
    }

    public void setNeedsAmount(BigDecimal needsAmount) {
        this.needsAmount = needsAmount;
    }
}
