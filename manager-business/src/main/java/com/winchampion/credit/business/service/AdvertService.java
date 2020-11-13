package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.AdvertDO;

import java.util.List;
import java.util.Map;

/**
 * 广告图表
 * 
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-26 11:20:23
 */
public interface AdvertService {
	
	AdvertDO get(String id);
	
	List<AdvertDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AdvertDO advert);
	
	int update(AdvertDO advert);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
