package com.winchampion.credit.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.dao.WechatAdvertDao;
import com.winchampion.credit.business.domain.WechatAdvertDO;
import com.winchampion.credit.business.service.WechatAdvertService;



@Service
public class WechatAdvertServiceImpl implements WechatAdvertService {
	@Autowired
	private WechatAdvertDao wechatAdvertDao;
	
	@Override
	public WechatAdvertDO get(String id){
		return wechatAdvertDao.get(id);
	}
	
	@Override
	public List<WechatAdvertDO> list(Map<String, Object> map){
		return wechatAdvertDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return wechatAdvertDao.count(map);
	}
	
	@Override
	public int save(WechatAdvertDO wechatAdvert){
		//设置UUID 和创建人
		wechatAdvert.saveBefore();
		wechatAdvert.setIsRelease("0");//默认设置为未发布
		return wechatAdvertDao.save(wechatAdvert);
	}
	
	@Override
	public int update(WechatAdvertDO wechatAdvert){
		wechatAdvert.saveBefore();
		return wechatAdvertDao.update(wechatAdvert);
	}
	
	@Override
	public int remove(String id){
		return wechatAdvertDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return wechatAdvertDao.batchRemove(ids);
	}
	
}
