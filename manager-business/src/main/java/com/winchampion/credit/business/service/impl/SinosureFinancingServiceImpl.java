package com.winchampion.credit.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.dao.SinosureFinancingDao;
import com.winchampion.credit.business.domain.SinosureFinancingDO;
import com.winchampion.credit.business.service.SinosureFinancingService;



@Service
public class SinosureFinancingServiceImpl implements SinosureFinancingService {
	@Autowired
	private SinosureFinancingDao sinosureFinancingDao;
	
	@Override
	public SinosureFinancingDO get(String id){
		return sinosureFinancingDao.get(id);
	}
	
	@Override
	public List<SinosureFinancingDO> list(Map<String, Object> map){
		return sinosureFinancingDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sinosureFinancingDao.count(map);
	}
	
	@Override
	public int save(SinosureFinancingDO sinosureFinancing){
		return sinosureFinancingDao.save(sinosureFinancing);
	}
	
	@Override
	public int update(SinosureFinancingDO sinosureFinancing){
		return sinosureFinancingDao.update(sinosureFinancing);
	}
	
	@Override
	public int remove(String id){
		return sinosureFinancingDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return sinosureFinancingDao.batchRemove(ids);
	}
	
}
