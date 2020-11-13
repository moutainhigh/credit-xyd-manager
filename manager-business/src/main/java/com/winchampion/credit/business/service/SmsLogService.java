package com.winchampion.credit.business.service;

import org.springframework.scheduling.annotation.Async;

/**
 * 短信发送日志表
 * 
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-03-02 09:34:16
 */
public interface SmsLogService {

	@Async
	public String sendSms() throws Exception;
}
