package com.winchampion.credit.common.exception;
/**
 * Created by Champion China . 
 * @author: zhangxin  
 * @date:2020年3月2日
 * @time:下午3:00:50
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public class InterfaceResultException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9200145025320749731L;

	
	public InterfaceResultException(){
        super();
    }
	
	public InterfaceResultException(String message){
        super(message);
    }
}
