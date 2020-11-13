package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.HorseDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 跑马灯表
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-27 15:05:36
 */
@Mapper
public interface HorseDao {

	HorseDO get(String id);
	
	List<HorseDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(HorseDO horse);
	
	int update(HorseDO horse);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
