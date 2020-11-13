package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.vo.HouseProductEditVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 首页热门、企业、个人产品 Dao
 * @author liwei
 */
@Mapper
public interface HomeProductEditDao {

	List<HouseProductEditVo> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	List<HouseProductEditVo> releaseProductList(Map<String, Object> map);

	int releaseProductCount(Map<String, Object> map);

	int batchMoveIn(@Param("productIds")String[] productIds, @Param("productType")String productType);

	int moveOut(@Param("id") String id, @Param("productType") String productType);
}
