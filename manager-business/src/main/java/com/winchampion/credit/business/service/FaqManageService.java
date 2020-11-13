package com.winchampion.credit.business.service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.domain.FaqManageDO;

/**
 * 常见问题管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-03 16:58:56
 */
public interface FaqManageService {
	
	FaqManageDO get(String id);
	
	List<FaqManageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FaqManageDO faqManage);
	
	int update(FaqManageDO faqManage);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
