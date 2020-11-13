package com.winchampion.credit.common.interfaces.country.req;

public class SettledInFinancialInstitutionStatistics {
    /**
     * 入驻金融机构类型：
     * 1：银行数量、
     * 2：融资租赁数量、
     * 3：小额贷款公司数量、
     * 4：其他
     */

    //机构类型编码
    private Integer code;

    //各类型金融机构数
    private Integer institutionsNum;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getInstitutionsNum() {
        return institutionsNum;
    }

    public void setInstitutionsNum(Integer institutionsNum) {
        this.institutionsNum = institutionsNum;
    }
}
