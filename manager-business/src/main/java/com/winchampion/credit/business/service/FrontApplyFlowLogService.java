package com.winchampion.credit.business.service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.domain.FrontApplyFlowLogDO;
import com.winchampion.credit.business.domain.ProductApplyLogDO;

/**
 * 产品申请流程表（前台）
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-04 16:20:45
 */
public interface FrontApplyFlowLogService {
	
	FrontApplyFlowLogDO get(String id);
	
	List<FrontApplyFlowLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FrontApplyFlowLogDO frontApplyFlowLog);
	
	int update(FrontApplyFlowLogDO frontApplyFlowLog);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
