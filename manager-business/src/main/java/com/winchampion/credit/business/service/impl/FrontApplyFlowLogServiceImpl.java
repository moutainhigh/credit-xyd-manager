package com.winchampion.credit.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winchampion.credit.business.dao.FrontApplyFlowLogDao;
import com.winchampion.credit.business.domain.FrontApplyFlowLogDO;
import com.winchampion.credit.business.service.FrontApplyFlowLogService;

@Service
public class FrontApplyFlowLogServiceImpl implements FrontApplyFlowLogService {
	@Autowired
	private FrontApplyFlowLogDao frontApplyFlowLogDao;
	
	@Override
	public FrontApplyFlowLogDO get(String id){
		return frontApplyFlowLogDao.get(id);
	}
	
	@Override
	public List<FrontApplyFlowLogDO> list(Map<String, Object> map){
		return frontApplyFlowLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return frontApplyFlowLogDao.count(map);
	}
	
	@Override
	public int save(FrontApplyFlowLogDO frontApplyFlowLog){
		return frontApplyFlowLogDao.save(frontApplyFlowLog);
	}
	
	@Override
	public int update(FrontApplyFlowLogDO frontApplyFlowLog){
		return frontApplyFlowLogDao.update(frontApplyFlowLog);
	}
	
	@Override
	public int remove(String id){
		return frontApplyFlowLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return frontApplyFlowLogDao.batchRemove(ids);
	}
	
}
