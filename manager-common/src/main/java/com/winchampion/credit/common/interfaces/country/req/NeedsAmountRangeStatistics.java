package com.winchampion.credit.common.interfaces.country.req;

import java.math.BigDecimal;

public class NeedsAmountRangeStatistics {
    /**
     * 需求金额区间：
     * 1:1-100 万 ，
     * 2:100-300 万 ，
     * 3:300-500 万 ，
     * 4:500 以上
     */

    //金额区间编码
    private Integer code;

    //各区间的需求金额
    private BigDecimal rangNeedsAmount;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BigDecimal getRangNeedsAmount() {
        return rangNeedsAmount;
    }

    public void setRangNeedsAmount(BigDecimal rangNeedsAmount) {
        this.rangNeedsAmount = rangNeedsAmount;
    }
}
