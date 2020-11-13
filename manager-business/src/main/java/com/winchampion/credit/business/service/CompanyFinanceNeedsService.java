package com.winchampion.credit.business.service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.domain.CompanyFinanceNeedsDO;

/***
 * 企业融资需求
 * Created by WIN-CHAMPION China . 
 * @author: zhangxin  
 * @date:2020年2月25日
 * @time:上午10:05:52
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public interface CompanyFinanceNeedsService {
	
	CompanyFinanceNeedsDO get(String id);
	
	List<CompanyFinanceNeedsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompanyFinanceNeedsDO companyFinanceNeeds);
	
	int update(CompanyFinanceNeedsDO companyFinanceNeeds);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<CompanyFinanceNeedsDO> listByCustomerId(Map<String,Object> map);

	int countByCustomerId(Map<String,Object> map);
}
