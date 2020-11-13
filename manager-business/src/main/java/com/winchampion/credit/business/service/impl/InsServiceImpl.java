package com.winchampion.credit.business.service.impl;

import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.user.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.dao.InsDao;
import com.winchampion.credit.business.domain.InsDO;
import com.winchampion.credit.business.service.InsService;

@Service
public class InsServiceImpl implements InsService {
	@Autowired
	private InsDao insDao;
	
	@Override
	public InsDO get(String id){
		return insDao.get(id);
	}
	
	@Override
	public List<InsDO> list(Map<String, Object> map){
		return insDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return insDao.count(map);
	}
	
	@Override
	public int save(InsDO insDo){
		insDo.saveBefore();
		insDo.setDelFlag("0");
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		insDo.setCreateDate(date);
		String userId = UserUtils.getUserId();
		insDo.setCreateBy(userId);
		insDo.setUpdateDate(date);
		insDo.setUpdateBy(userId);
		return insDao.save(insDo);
	}
	
	@Override
	public int update(InsDO insDO){
		insDO.setUpdateBy(UserUtils.getUserId());
		insDO.setUpdateDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		return insDao.update(insDO);
	}
	
	@Override
	public int remove(String id){
		return insDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return insDao.batchRemove(ids);
	}
	
}
