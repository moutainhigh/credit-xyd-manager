package com.winchampion.credit.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winchampion.credit.business.dao.CreditProductDao;
import com.winchampion.credit.business.domain.CreditProductDO;
import com.winchampion.credit.business.service.CreditProductService;



@Service
public class CreditProductServiceImpl implements CreditProductService {
	@Autowired
	private CreditProductDao creditProductDao;
	
	@Override
	public CreditProductDO get(String id){
		return creditProductDao.get(id);
	}
	
	@Override
	public List<CreditProductDO> list(Map<String, Object> map){
		return creditProductDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return creditProductDao.count(map);
	}
	
	@Override
	public int save(CreditProductDO creditProduct){
		return creditProductDao.save(creditProduct);
	}
	
	@Override
	public int update(CreditProductDO creditProduct){
		return creditProductDao.update(creditProduct);
	}
	
	@Override
	public int remove(String id){
		return creditProductDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return creditProductDao.batchRemove(ids);
	}

	@Override
	public String maxProductNo() {
		return creditProductDao.maxProductNo();
	}
	
}
