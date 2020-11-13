package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.SinosureFinancingDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 中信保融资产品申请详情
 * @author zhangcong
 * @email 15121007361@163.com
 * @date 2020-06-22 13:23:33
 */
@Mapper
public interface SinosureFinancingDao {

	SinosureFinancingDO get(String id);
	
	List<SinosureFinancingDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SinosureFinancingDO sinosureFinancing);
	
	int update(SinosureFinancingDO sinosureFinancing);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
