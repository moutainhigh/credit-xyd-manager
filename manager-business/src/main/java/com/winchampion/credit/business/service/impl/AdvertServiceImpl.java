package com.winchampion.credit.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winchampion.credit.business.dao.AdvertDao;
import com.winchampion.credit.business.domain.AdvertDO;
import com.winchampion.credit.business.service.AdvertService;



@Service
public class AdvertServiceImpl implements AdvertService {
	@Autowired
	private AdvertDao advertDao;
	
	@Override
	public AdvertDO get(String id){
		return advertDao.get(id);
	}
	
	@Override
	public List<AdvertDO> list(Map<String, Object> map){
		return advertDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return advertDao.count(map);
	}
	
	@Override
	public int save(AdvertDO advert){
		//设置UUID 和创建人
		advert.saveBefore();
		advert.setIsRelease("0");//默认设置为未发布
		return advertDao.save(advert);
	}
	
	@Override
	public int update(AdvertDO advert){
		advert.saveBefore();
		return advertDao.update(advert);
	}
	
	@Override
	public int remove(String id){
		return advertDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return advertDao.batchRemove(ids);
	}
	
}
