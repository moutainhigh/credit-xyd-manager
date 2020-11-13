package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.CreditProductDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 信用产品
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-01 18:18:36
 */
@Mapper
public interface CreditProductDao {

	CreditProductDO get(String id);
	
	List<CreditProductDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CreditProductDO creditProduct);
	
	int update(CreditProductDO creditProduct);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
	/**
	 * 获取当前最大产品编码
	 * @return
	 */
	String maxProductNo();
}
