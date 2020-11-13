package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.HomeFinEntDO;

import java.util.List;
import java.util.Map;

/**
 * 首页合作金融/信用机构 Service
 * @author liwei
 */
public interface HomeFineEntService {

	HomeFinEntDO get(String id);

	List<HomeFinEntDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int save(HomeFinEntDO homeFinEntDO);

	int remove(String id);

	int update(HomeFinEntDO homeFinEntDO);
}
