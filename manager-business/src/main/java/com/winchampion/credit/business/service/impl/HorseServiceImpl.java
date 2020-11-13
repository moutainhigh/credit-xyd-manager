package com.winchampion.credit.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.dao.HorseDao;
import com.winchampion.credit.business.domain.HorseDO;
import com.winchampion.credit.business.service.HorseService;



@Service
public class HorseServiceImpl implements HorseService {
	@Autowired
	private HorseDao horseDao;
	
	@Override
	public HorseDO get(String id){
		return horseDao.get(id);
	}
	
	@Override
	public List<HorseDO> list(Map<String, Object> map){
		return horseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return horseDao.count(map);
	}
	
	@Override
	public int save(HorseDO horse){
		//初始化
		horse.saveBefore();
		horse.setIsRelease("0");//未发布
		return horseDao.save(horse);
	}
	
	@Override
	public int update(HorseDO horse){
		horse.saveBefore();
		return horseDao.update(horse);
	}
	
	@Override
	public int remove(String id){
		return horseDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return horseDao.batchRemove(ids);
	}
	
}
