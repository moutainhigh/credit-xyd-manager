package com.winchampion.credit.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winchampion.credit.business.dao.CreditProductInfoDao;
import com.winchampion.credit.business.domain.CreditProductInfoDO;
import com.winchampion.credit.business.service.CreditProductInfoService;




@Service
public class CreditProductInfoServiceImpl implements CreditProductInfoService {
	@Autowired
	private CreditProductInfoDao creditProductInfoDao;
	
	@Override
	public CreditProductInfoDO get(String id){
		return creditProductInfoDao.get(id);
	}
	
	@Override
	public List<CreditProductInfoDO> list(Map<String, Object> map){
		return creditProductInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return creditProductInfoDao.count(map);
	}
	
	@Override
	public int save(CreditProductInfoDO creditProductInfo){
		return creditProductInfoDao.save(creditProductInfo);
	}
	
	@Override
	public int update(CreditProductInfoDO creditProductInfo){
		return creditProductInfoDao.update(creditProductInfo);
	}
	
	@Override
	public int remove(String id){
		return creditProductInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return creditProductInfoDao.batchRemove(ids);
	}
	
	/**
	 * 批量新增
	 * @param list
	 */
	@Override
	public void batchInsert(List<CreditProductInfoDO> list) {
		creditProductInfoDao.batchInsert(list);
	}
}
