package com.winchampion.credit.common.interfaces.country.req;

public class TaxIntervalStatistics {
    /**
     * 企业税收区间：
     * 1：1-15 万
     * 2：15-30 万
     * 3：30-50 万
     * 4：50-100 万
     * 5：100 万以上
     */

    //税收区间编码
    private Integer code;

    //各税收区间注册企业数
    private Integer registeredNum;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getRegisteredNum() {
        return registeredNum;
    }

    public void setRegisteredNum(Integer registeredNum) {
        this.registeredNum = registeredNum;
    }
}
