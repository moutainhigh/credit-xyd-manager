package com.winchampion.credit.business.vo.base;

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

	/**系统异常**/
	CODE_1000("1000","系统异常"),
	/**短信验证码发送失败**/
	CODE_1016("1016","短信验证码发送失败");

	
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
