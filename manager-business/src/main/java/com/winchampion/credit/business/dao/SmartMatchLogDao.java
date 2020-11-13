package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.SmartMatchLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 智能匹配日志
 * @author zhangcong
 * @email 15121007361@163.com
 * @date 2020-04-20 10:42:35
 */
@Mapper
public interface SmartMatchLogDao {

	SmartMatchLogDO get(String customerId);
	
	List<SmartMatchLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SmartMatchLogDO smartMatchLog);
	
	int update(SmartMatchLogDO smartMatchLog);
	
	int remove(String customer_id);
	
	int batchRemove(String[] customerIds);
}
