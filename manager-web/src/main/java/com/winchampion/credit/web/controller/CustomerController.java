package com.winchampion.credit.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winchampion.credit.business.Enum.ResultEnum;
import com.winchampion.credit.business.service.CustomerService;
import com.winchampion.credit.business.vo.CustomerReplenishVo;
import com.winchampion.credit.business.vo.RegisterInterVo;
import com.winchampion.credit.business.vo.base.ResultVo;
import com.winchampion.credit.web.controller.user.BaseController;

@RestController
@RequestMapping("/interface/customerInter")
public class CustomerController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customerReplenish")
	public ResultVo<RegisterInterVo> customerReplenish(@RequestBody( required = false)CustomerReplenishVo customerReplenishVo) {
		ResultVo<RegisterInterVo> resultVo = new ResultVo<RegisterInterVo>();
		try {
			if(validateValue(customerReplenishVo, resultVo,"pname","certiCode","phoneNo")) {
				RegisterInterVo registerInterVo = new RegisterInterVo();
				registerInterVo.setResult(customerService.customerReplenish(customerReplenishVo));
				resultVo.setData(registerInterVo);
				resultVo.setResultCode(ResultEnum.CODE_0000.getCode());
				resultVo.setResultDesc(ResultEnum.getMsg(ResultEnum.CODE_0000.getCode()).getMsg());
				return resultVo;
			}else {
				return resultVo;
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultVo.setResultCode(ResultEnum.CODE_1000.getCode());
			resultVo.setResultDesc(ResultEnum.CODE_1000.getMsg());
			return resultVo;
		}
	}
}
