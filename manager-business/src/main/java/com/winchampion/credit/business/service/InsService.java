package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.InsDO;

import java.util.List;
import java.util.Map;

/**
 * 合作机构管理
 * 
 * @author zhangcong
 * @email 1992lcg@163.com
 * @date 2020-02-28 21:12:41
 */
public interface InsService {
	
	InsDO get(String id);
	
	List<InsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(InsDO ins);
	
	int update(InsDO ins);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
