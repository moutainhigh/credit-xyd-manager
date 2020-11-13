package com.winchampion.credit.common.interfaces.country.req;

import java.math.BigDecimal;

public class LoanAmountTypeStatistics {
    /**
     * 放款类型：
     * 1：抵押
     * 2：信用
     * 3：质押
     * 4：其他
     */

    //放款类型编码
    private Integer code;

    //各类型放款金额
    private BigDecimal typeAmount;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BigDecimal getTypeAmount() {
        return typeAmount;
    }

    public void setTypeAmount(BigDecimal typeAmount) {
        this.typeAmount = typeAmount;
    }
}
