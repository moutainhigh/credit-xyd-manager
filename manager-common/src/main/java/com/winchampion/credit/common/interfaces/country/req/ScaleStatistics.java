package com.winchampion.credit.common.interfaces.country.req;

public class ScaleStatistics {

    /**
     * 企业规模：
     * 1：大型
     * 2：中型
     * 3：小型
     * 4、其他
     */
    //规模编码
    private Integer code;
    //各规模注册企业数
    private Integer registeredNum;
    //各规模授信企业数
    private Integer loanNum;

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

    public Integer getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(Integer loanNum) {
        this.loanNum = loanNum;
    }
}
