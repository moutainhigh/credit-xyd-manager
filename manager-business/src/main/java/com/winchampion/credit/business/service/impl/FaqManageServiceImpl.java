package com.winchampion.credit.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winchampion.credit.business.dao.FaqManageDao;
import com.winchampion.credit.business.domain.FaqManageDO;
import com.winchampion.credit.business.service.FaqManageService;

@Service
public class FaqManageServiceImpl implements FaqManageService {
	@Autowired
	private FaqManageDao faqManageDao;
	
	@Override
	public FaqManageDO get(String id){
		return faqManageDao.get(id);
	}
	
	@Override
	public List<FaqManageDO> list(Map<String, Object> map){
		return faqManageDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return faqManageDao.count(map);
	}
	
	@Override
	public int save(FaqManageDO faqManage){
		return faqManageDao.save(faqManage);
	}
	
	@Override
	public int update(FaqManageDO faqManage){
		return faqManageDao.update(faqManage);
	}
	
	@Override
	public int remove(String id){
		return faqManageDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return faqManageDao.batchRemove(ids);
	}
	
}
