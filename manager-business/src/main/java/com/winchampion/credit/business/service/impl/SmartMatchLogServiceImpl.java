package com.winchampion.credit.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.dao.SmartMatchLogDao;
import com.winchampion.credit.business.domain.SmartMatchLogDO;
import com.winchampion.credit.business.service.SmartMatchLogService;



@Service
public class SmartMatchLogServiceImpl implements SmartMatchLogService {
	@Autowired
	private SmartMatchLogDao smartMatchLogDao;
	
	@Override
	public SmartMatchLogDO get(String customerId){
		return smartMatchLogDao.get(customerId);
	}
	
	@Override
	public List<SmartMatchLogDO> list(Map<String, Object> map){
		return smartMatchLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return smartMatchLogDao.count(map);
	}
	
	@Override
	public int save(SmartMatchLogDO smartMatchLog){
		return smartMatchLogDao.save(smartMatchLog);
	}
	
	@Override
	public int update(SmartMatchLogDO smartMatchLog){
		return smartMatchLogDao.update(smartMatchLog);
	}
	
	@Override
	public int remove(String customerId){
		return smartMatchLogDao.remove(customerId);
	}
	
	@Override
	public int batchRemove(String[] customerIds){
		return smartMatchLogDao.batchRemove(customerIds);
	}
	
}
