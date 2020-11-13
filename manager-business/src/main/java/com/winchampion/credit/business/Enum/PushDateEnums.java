package com.winchampion.credit.business.Enum;


import com.winchampion.credit.common.util.StringUtils;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/27
 * @description: 推送数据枚举类
 */
public enum PushDateEnums {
    CODE_0000("0000","成功！"),
    /**系统异常**/
    CODE_1000("1000","系统异常"),
    /**增量字段<0**/
    CODE_1001("1001","数据上传失败，增量数据字段 %s 本次上报数据不满足要求"),
    /**必填字段**/
    CODE_1002("1002","数据上传失败，增量数据字段 %s 为必填字段"),
    /**本次上报数据 < 上次报送数据**/
    CODE_1003("1003","数据上传失败，全量数据字段 %s 本次上报数据不满足要求"),
    /**必填字段**/
    CODE_1004("1004","数据上传失败，全量数据字段 %s 为必填字段"),
    /**首页展示数据**/
    CODE_1005("1005","首页展示数据必填项为空"),
    CODE_1006("1006","请重新上传"),
    ;


    private String code;
    private String msg;

    private PushDateEnums(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static PushDateEnums getMsg(String code) {
        if (StringUtils.isEmpty(code)) {
            return CODE_1000;
        }
        for (PushDateEnums obj : values()) {
            if(code.equals(obj.code)) {
                return obj;
            }
        }
        return CODE_1000;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
