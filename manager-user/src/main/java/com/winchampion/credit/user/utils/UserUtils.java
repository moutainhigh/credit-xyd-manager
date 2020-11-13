package com.winchampion.credit.user.utils;

import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.redis.RedisString;
import com.winchampion.credit.common.util.StringUtils;
import com.winchampion.credit.user.config.ApplicationContextRegister;
import com.winchampion.credit.user.domain.UserDO;

/**
 * Created by Champion China . 
 * @author: zhangxin  
 * @date:2020年2月26日
 * @time:下午4:38:44
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public class UserUtils {
	private static RedisString redisString = ApplicationContextRegister.getBean(RedisString .class);

	public static UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public static String getUserId() {
		return getUser().getUserId().toString();
	}

	public static String getUsername() {
		return getUser().getUsername();
	}

	public static boolean isOnline(String phone){
		String token = redisString.get(Constant.CUSTOMER_LOGIN_PREFIX + phone);
		if(StringUtils.isNotBlank(token)){
			return true;
		}
		return false;
	}
}
