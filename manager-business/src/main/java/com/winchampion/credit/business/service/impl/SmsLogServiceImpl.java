package com.winchampion.credit.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.winchampion.credit.business.service.SmsLogService;
import com.winchampion.credit.business.vo.base.ResultEnum;
import com.winchampion.credit.common.exception.InterfaceResultException;
import com.winchampion.credit.common.message.response.SmsVariableResponse;
import com.winchampion.credit.common.util.MessageUtils;
import com.winchampion.credit.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SmsLogServiceImpl implements SmsLogService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Transactional
	@Override
	public String sendSms() throws Exception{

		//获取随机验证码 6位数字
		String code = StringUtils.getRandomNumberCode(6);
		//发送短信
		String result = MessageUtils.sendSmsByPost(code,"18217072176" );
		//String result2 = MessageUtils.sendSmsByPost(code,"13167076589" );

		logger.debug("发送短信回馈：手机号'15901607911'发送短信情况：  " + result );
		//logger.debug("发送短信回馈：手机号'13167076589'发送短信情况：  " + result2);

		SmsVariableResponse smsVariableResponse = JSON.parseObject(result, SmsVariableResponse.class);
		//SmsVariableResponse smsVariableResponse2 = JSON.parseObject(result2, SmsVariableResponse.class);
		if(!"0".equals(smsVariableResponse.getCode())) {
			throw new InterfaceResultException(ResultEnum.CODE_1016.getCode());
		}

		return code;
	}


}	
 