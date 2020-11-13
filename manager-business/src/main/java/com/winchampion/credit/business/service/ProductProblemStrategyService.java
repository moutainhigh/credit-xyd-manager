package com.winchampion.credit.business.service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.domain.ProductProblemStrategyDO;

/**
 * 产品及智能问题匹配策略
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-02 19:32:53
 */
public interface ProductProblemStrategyService {
	
	ProductProblemStrategyDO get(String id);
	
	List<ProductProblemStrategyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
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
	int getOptionsList(String problemId,List<String> list);
	
	List<ProductProblemStrategyDO> compositeList(Map<String, Object> map);
	
	int compositeCount(Map<String,Object> map);
	/**
	 * 根据产品id  删除数据
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
