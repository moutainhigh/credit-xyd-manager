package com.winchampion.credit.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.winchampion.credit.business.domain.ProductApplyLogDO;
import com.winchampion.credit.business.vo.ApplyTimeoutVo;

/**
 * 产品申请记录表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-04 16:20:45
 */
@Mapper
public interface ProductApplyLogDao {

	ProductApplyLogDO get(String id);
	
	List<ProductApplyLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductApplyLogDO productApplyLog);
	
	int update(ProductApplyLogDO productApplyLog);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
	/**
	 * 定时任务更新超过24小时未完成的申请
	 * @param vo
	 * @return
	 */
	int updateApplyTimeout(ApplyTimeoutVo vo);
	/**
	 * 中信保融资产品申请列表
	 * @param map
	 * @return
	 */
	List<ProductApplyLogDO> sinosureFinancingList(Map<String,Object> map);
	/**
	 * 中信保融资产品申请列表计数统计
	 * @param map
	 * @return
	 */
	int sinosureFinancingCount(Map<String,Object> map);
}
