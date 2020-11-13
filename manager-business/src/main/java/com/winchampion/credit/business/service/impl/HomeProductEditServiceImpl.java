package com.winchampion.credit.business.service.impl;

import com.winchampion.credit.business.dao.HomeProductEditDao;
import com.winchampion.credit.business.service.HomeProductEditService;
import com.winchampion.credit.business.vo.HouseProductEditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class HomeProductEditServiceImpl implements HomeProductEditService {
	@Autowired
	private HomeProductEditDao homeProductEditDao;

	@Override
	public List<HouseProductEditVo> list(Map<String, Object> map){
		return homeProductEditDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return homeProductEditDao.count(map);
	}
	
	@Override
	public List<HouseProductEditVo> releaseProductList(Map<String, Object> map) {

		return homeProductEditDao.releaseProductList(map);
	}

	@Override
	public int releaseProductCount(Map<String, Object> map) {
		return homeProductEditDao.releaseProductCount(map);
	}

	@Transactional
	@Override
	public int batchMoveIn(String[] productIds, String productType) {
		int count = homeProductEditDao.batchMoveIn(productIds, productType);
		return count;
	}

	@Override
	public int moveOut(String id, String productType) {
		return homeProductEditDao.moveOut(id, productType);
	}
}
