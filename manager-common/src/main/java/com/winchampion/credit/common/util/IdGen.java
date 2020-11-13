package com.winchampion.credit.common.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by Champion China . 
 * @author: zhangxin  
 * @date:2020年2月26日
 * @time:下午4:45:05
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public class IdGen {
	
	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

}

