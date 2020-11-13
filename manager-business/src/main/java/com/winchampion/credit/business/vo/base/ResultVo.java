package com.winchampion.credit.business.vo.base;

import com.winchampion.credit.business.Enum.ResultEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 接口返回
 * Created by Champion China . 
 * @author: zhangxin  
 * @date:2020年3月2日
 * @time:上午9:45:51
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
@ApiModel(description = "接口返回")
public class ResultVo<T> {
	/**
	 * 返回码
	 */
	@ApiModelProperty(value = "返回码",name="resultCode")
	private String resultCode;
	
	/**
	 * 返回描述
	 */
	@ApiModelProperty(value = "返回描述",name="resultCode")
	private String resultDesc;
	
	/**
	 * 返回结果
	 */
	@ApiModelProperty(value = "返回结果",name="resultCode")
	private T data;

	public ResultVo() {
		this.resultCode = ResultEnum.CODE_1000.getCode();
		this.resultDesc = ResultEnum.CODE_1000.getMsg();
		
	}
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultVo [resultCode=" + resultCode + ", resultDesc=" + resultDesc + ", data=" + data + "]";
	}

	
}
