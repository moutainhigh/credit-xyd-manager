package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.CustomerCompanyAuthHisDO;

import java.util.List;
import java.util.Map;

/**
 * 前台用户认证企业状态变更 Service
 * @author liwei
 * @date 2020-02-25
 */
public interface CustomerCompanyAuthHisService {

	CustomerCompanyAuthHisDO get(String id);
	
	List<CustomerCompanyAuthHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
}
