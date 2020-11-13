package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.AdvertDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 广告图表
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-26 11:20:23
 */
@Mapper
public interface AdvertDao {

	AdvertDO get(String id);
	
	List<AdvertDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AdvertDO advert);
	
	int update(AdvertDO advert);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
