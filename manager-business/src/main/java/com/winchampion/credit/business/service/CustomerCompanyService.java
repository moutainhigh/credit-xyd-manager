package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.CustomerCompanyDO;

import java.util.List;
import java.util.Map;

/**
 * 前台用户认证企业 Service
 * @author liwei
 * @date 2020-02-25
 */
public interface CustomerCompanyService {
	
	CustomerCompanyDO get(String id);
	
	List<CustomerCompanyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	CustomerCompanyDO getCompanyByCustomerId(String customerNo);
	
	/**
	 * 更新企业认证过期
	 * @return
	 */
	int updateIsAuthentication();
}
