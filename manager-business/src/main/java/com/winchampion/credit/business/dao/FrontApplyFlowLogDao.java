package com.winchampion.credit.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.winchampion.credit.business.domain.FrontApplyFlowLogDO;

/**
 * 产品申请流程表（前台）
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-04 16:20:45
 */
@Mapper
public interface FrontApplyFlowLogDao {

	FrontApplyFlowLogDO get(String id);
	
	List<FrontApplyFlowLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(FrontApplyFlowLogDO frontApplyFlowLog);
	
	int update(FrontApplyFlowLogDO frontApplyFlowLog);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
