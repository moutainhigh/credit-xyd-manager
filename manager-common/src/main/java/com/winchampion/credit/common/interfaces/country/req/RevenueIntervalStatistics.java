package com.winchampion.credit.common.interfaces.country.req;

public class RevenueIntervalStatistics {
    /**
     * 企业营收区间：
     * 1：1-500 万、
     * 2：500-1000 万
     * 3：1000-2000 万
     * 4：2000 万以上
     */

    //营收区间编码
    private Integer code;

    //各营收区间注册企业数
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
