package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.HorseDO;

import java.util.List;
import java.util.Map;

/**
 * 跑马灯表
 * 
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-27 15:05:36
 */
public interface HorseService {
	
	HorseDO get(String id);
	
	List<HorseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(HorseDO horse);
	
	int update(HorseDO horse);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
