package com.winchampion.credit.business.service;

import com.winchampion.credit.business.vo.HouseProductEditVo;

import java.util.List;
import java.util.Map;

/**
 * 首页热门、企业、个人产品 Service
 * @author liwei
 */
public interface HomeProductEditService {
	
	List<HouseProductEditVo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	List<HouseProductEditVo> releaseProductList(Map<String, Object> map);

	int releaseProductCount(Map<String, Object> map);

	int batchMoveIn(String[] productIds, String productType);

	int moveOut(String id, String productType);
}
