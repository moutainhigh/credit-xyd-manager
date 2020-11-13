package com.winchampion.credit.common.interfaces.country.req;

public class IndustryStatistics {

    /**
     * 注册企业行业:
     * 1：农、林、牧、渔业
     * 2：采矿业
     * 3：制造业
     * 4：电力、热力、燃气及水生产和供应业
     * 5：建筑业
     * 6：批发和零售业
     * 7：交通运输、仓储和邮政业
     * 8：住宿和餐饮业
     * 9：信息传输、软件和信息技术服务业、
     * 10：金融业
     * 11 ：房地产业
     * 12：租赁和商务服务业
     * 13：科学研究和技术服务业
     * 14：水利、环境和公共设施管理业
     * 15：居民服务、修理和其他服务业
     * 16：教育
     * 17：卫生和社会工作
     * 18：文化、体育和娱乐业
     * 19：其他。
     */
    //行业类型编码
    private Integer code;
    //各行业类型注册企业数
    private Integer registeredNum;
    //各行业类型授信企业数
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
