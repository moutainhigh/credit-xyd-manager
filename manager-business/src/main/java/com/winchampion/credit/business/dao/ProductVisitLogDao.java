package com.winchampion.credit.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.winchampion.credit.business.domain.ProductVisitLogDO;

/**
 * 产品访问日志
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-10 11:40:34
 */
@Mapper
public interface ProductVisitLogDao {

	ProductVisitLogDO get(String id);
	
	List<ProductVisitLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductVisitLogDO productVisitLog);
	
	int update(ProductVisitLogDO productVisitLog);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
