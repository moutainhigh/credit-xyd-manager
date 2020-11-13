package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.ProductOperateLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 产品更新日志
 * @author zhangcong
 * @email 1992lcg@163.com
 * @date 2020-02-28 14:32:12
 */
@Mapper
public interface ProductOperateLogDao {

	ProductOperateLogDO get(String id);
	
	List<ProductOperateLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductOperateLogDO productOperateLog);
	
	int update(ProductOperateLogDO productOperateLog);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
