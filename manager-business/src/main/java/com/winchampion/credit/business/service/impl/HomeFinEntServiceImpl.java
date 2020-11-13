package com.winchampion.credit.business.service.impl;

import com.winchampion.credit.business.dao.HomeFinEntDao;
import com.winchampion.credit.business.domain.HomeFinEntDO;
import com.winchampion.credit.business.service.HomeFineEntService;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.common.util.IdGen;
import com.winchampion.credit.user.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomeFinEntServiceImpl implements HomeFineEntService {
	@Autowired
	private HomeFinEntDao homeFinEntDao;

	@Override
	public HomeFinEntDO get(String id) {
		return homeFinEntDao.get(id);
	}

	@Override
	public List<HomeFinEntDO> list(Map<String, Object> map){
		return homeFinEntDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return homeFinEntDao.count(map);
	}
	
	@Override
	public int save(HomeFinEntDO homeFinEntDO){
		homeFinEntDO.setId(IdGen.uuid());
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		String optUserId = UserUtils.getUserId();
		homeFinEntDO.setCreateDate(date);
		homeFinEntDO.setCreateBy(optUserId);
		homeFinEntDO.setUpdateDate(date);
		homeFinEntDO.setUpdateBy(optUserId);
		homeFinEntDO.setDelFlag("0");
		return homeFinEntDao.save(homeFinEntDO);
	}

	@Override
	public int remove(String id) {
		return homeFinEntDao.remove(id);
	}

	@Override
	public int update(HomeFinEntDO homeFinEntDO){
		homeFinEntDO.setUpdateDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		homeFinEntDO.setUpdateBy(UserUtils.getUserId());
		return homeFinEntDao.update(homeFinEntDO);
	}
}
