package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.CustomerDO;
import com.winchampion.credit.business.vo.CustomerReplenishVo;
import com.winchampion.credit.business.vo.RegisterAccountDetailVo;

import java.util.List;
import java.util.Map;

/**
 * 前台用户 Service
 * @author liwei
 * @date 2020-02-25
 */
public interface CustomerService {
	
	CustomerDO get(String id);

	CustomerDO getByCustomerNo(String customerNo);

	List<CustomerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	RegisterAccountDetailVo getRegisterDetail(String customerNo);
	/**
	 * 个人用户注册或完善接口
	 * @param customerReplenishVo
	 * @return
	 */
	String customerReplenish(CustomerReplenishVo customerReplenishVo);
}
