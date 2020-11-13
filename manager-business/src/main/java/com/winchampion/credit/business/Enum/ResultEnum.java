package com.winchampion.credit.business.Enum;

import com.winchampion.credit.common.util.StringUtils;

/**
 * Created by Champion China . 
 * @author: zhangxin  
 * @date:2020年3月2日
 * @time:上午10:51:44
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public enum ResultEnum {
	
	//2开头是调用银行接口返回的结果
	/**成功**/
	CODE_0000("0000","成功"),
	/**之前有登陆过了，并且现在已经T了**/
	CODE_0001("0001","之前有登陆过了，并且现在已经T了"),
	/**系统异常**/
	CODE_1000("1000","系统异常"),
	/**输入为空**/
	CODE_1001("1001","输入%s为空"),
	/**手机号格式有误**/
	CODE_1002("1002","手机号格式有误"),
	/**类型值没有定义**/
	CODE_1004("1004","类型值没有定义");
	
	private String code;
    private String msg;
    
    private ResultEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static ResultEnum getMsg(String code) {
        if (StringUtils.isEmpty(code)) {
            return CODE_1000;
        }
        for (ResultEnum obj : values()) {
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
