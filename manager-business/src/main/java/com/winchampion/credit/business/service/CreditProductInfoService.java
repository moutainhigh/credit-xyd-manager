package com.winchampion.credit.business.service;


import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.domain.CreditProductInfoDO;

/**
 * 信用产品
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-01 18:18:37
 */
public interface CreditProductInfoService {
	
	CreditProductInfoDO get(String id);
	
	List<CreditProductInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CreditProductInfoDO creditProductInfo);
	
	int update(CreditProductInfoDO creditProductInfo);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
	/**
	 * 批量新增
	 * @param list
	 */
	void batchInsert(List<CreditProductInfoDO> list);
}
