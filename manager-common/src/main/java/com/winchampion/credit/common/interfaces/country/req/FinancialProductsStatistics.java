package com.winchampion.credit.common.interfaces.country.req;

public class FinancialProductsStatistics {
    /**
     * 金融产品类型：
     * 1：信用
     * 2：抵押
     * 3：质押
     * 4：其他
     */

    //产品类型编码
    private Integer code;

    //各类型金融产品数
    private Integer productsNum;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getProductsNum() {
        return productsNum;
    }

    public void setProductsNum(Integer productsNum) {
        this.productsNum = productsNum;
    }
}
