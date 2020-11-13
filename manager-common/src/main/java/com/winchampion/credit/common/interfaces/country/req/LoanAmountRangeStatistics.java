package com.winchampion.credit.common.interfaces.country.req;

import java.math.BigDecimal;

public class LoanAmountRangeStatistics {
    /**
     * 放款机构金额区间：
     * 1：1-100 万
     * 2：100-300 万
     * 3：300-500 万
     * 4：500 以上
     */

    //金额区间编码
    private Integer code;

    //各区间放款金额
    private BigDecimal rangeAmount;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BigDecimal getRangeAmount() {
        return rangeAmount;
    }

    public void setRangeAmount(BigDecimal rangeAmount) {
        this.rangeAmount = rangeAmount;
    }
}
