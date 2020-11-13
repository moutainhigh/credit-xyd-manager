package com.winchampion.credit.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.dao.CompanyFinEntDao;
import com.winchampion.credit.business.domain.CompanyFinEntDO;
import com.winchampion.credit.business.service.CompanyFinEntService;



@Service
public class CompanyFinEntServiceImpl implements CompanyFinEntService {
	@Autowired
	private CompanyFinEntDao companyFinEntDao;
	
	@Override
	public CompanyFinEntDO get(String id){
		return companyFinEntDao.get(id);
	}
	
	@Override
	public List<CompanyFinEntDO> list(Map<String, Object> map){
		return companyFinEntDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return companyFinEntDao.count(map);
	}
	
	@Override
	public int save(CompanyFinEntDO companyFinEnt){
		companyFinEnt.saveBefore();
		return companyFinEntDao.save(companyFinEnt);
	}
	
	@Override
	public int update(CompanyFinEntDO companyFinEnt){
		companyFinEnt.saveBefore();
		return companyFinEntDao.update(companyFinEnt);
	}
	
	@Override
	public int remove(String id){
		return companyFinEntDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return companyFinEntDao.batchRemove(ids);
	}
	
}
