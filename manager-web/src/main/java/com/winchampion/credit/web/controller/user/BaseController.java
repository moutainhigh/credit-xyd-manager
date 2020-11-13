package com.winchampion.credit.web.controller.user;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import com.winchampion.credit.user.utils.ShiroUtils;
import com.winchampion.credit.business.Enum.ResultEnum;
import com.winchampion.credit.business.vo.base.ResultVo;
import com.winchampion.credit.common.util.StringUtils;
import com.winchampion.credit.user.domain.UserDO;

@Controller
public class BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
	
	/**
	 * 验证输入 ， validateStrs 目前只支持String 格式
	 * @author: zhangxin  
	 * @date:2020年3月2日  上午11:01:18
	 * @param smsDto
	 * @param resultVo
	 * @param validateStrs
	 * @return true 需要验证的都不为空，false 有一个为空
	 */
	public boolean validateValue(Object obj, ResultVo<?> resultVo, String... validateStrs) {
		try {
			if(obj == null) {
				resultVo.setResultCode(ResultEnum.CODE_1001.getCode());
				resultVo.setResultDesc(String.format(ResultEnum.CODE_1001.getMsg(),""));
				return false;
			}else {
				for(String validateStr : validateStrs) {
					Object value = PropertyUtils.getProperty(obj, validateStr);
					if(value == null || StringUtils.isBlank(value.toString()) || "null".equals(value.toString())) {
						resultVo.setResultCode(ResultEnum.CODE_1001.getCode());
						resultVo.setResultDesc(String.format(ResultEnum.CODE_1001.getMsg(),validateStr));
						return false;	
					}
				}
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
}