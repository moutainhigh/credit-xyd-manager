package com.winchampion.credit.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.winchampion.credit.business.domain.ProductProblemStrategyDO;

/**
 * 产品及智能问题匹配策略
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-02 19:32:53
 */
@Mapper
public interface ProductProblemStrategyDao {

	ProductProblemStrategyDO get(String id);
	
	List<ProductProblemStrategyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductProblemStrategyDO productProblemStrategy);
	
	int update(ProductProblemStrategyDO productProblemStrategy);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
	/**
	 * 根据问题编号+可选项集合 
	 * @param problemId 问题编号
	 * @param list 可选项集合
	 * @return
	 */
	int getOptionsList(@Param("problemId")String problemId,@Param("list")List<String> list);
	/**
	 * 策略列表页数据加载
	 * @param map
	 * @return
	 */
	List<ProductProblemStrategyDO> compositeList(Map<String,Object> map);
	/**
	 * 策略列表页数据计数
	 * @param map
	 * @return
	 */
	int compositeCount(Map<String,Object> map);
	/**
	 * 根据产品id删除数据
	 * @param productId
	 * @return
	 */
	int removeProductId(String productId);
	/**
	 * 批量新增
	 * @param list
	 */
	void batchInsert(List<ProductProblemStrategyDO> list);
}
