package com.winchampion.credit.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winchampion.credit.business.dao.ProductProblemStrategyDao;
import com.winchampion.credit.business.domain.ProductProblemStrategyDO;
import com.winchampion.credit.business.service.ProductProblemStrategyService;

@Service
public class ProductProblemStrategyServiceImpl implements ProductProblemStrategyService {
	@Autowired
	private ProductProblemStrategyDao productProblemStrategyDao;
	
	@Override
	public ProductProblemStrategyDO get(String id){
		return productProblemStrategyDao.get(id);
	}
	
	@Override
	public List<ProductProblemStrategyDO> list(Map<String, Object> map){
		return productProblemStrategyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productProblemStrategyDao.count(map);
	}
	
	@Override
	public int save(ProductProblemStrategyDO productProblemStrategy){
		return productProblemStrategyDao.save(productProblemStrategy);
	}
	
	@Override
	public int update(ProductProblemStrategyDO productProblemStrategy){
		return productProblemStrategyDao.update(productProblemStrategy);
	}
	
	@Override
	public int remove(String id){
		return productProblemStrategyDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return productProblemStrategyDao.batchRemove(ids);
	}
	/**
	 * 根据问题编号+可选项集合 
	 * @param problemId 问题编号
	 * @param list 可选项集合
	 * @return
	 */
	@Override
	public int getOptionsList(String problemId,List<String> list){
		return productProblemStrategyDao.getOptionsList(problemId,list);
	}

	@Override
	public List<ProductProblemStrategyDO> compositeList(Map<String, Object> map) {
		return productProblemStrategyDao.compositeList(map);
	}

	@Override
	public int compositeCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return productProblemStrategyDao.compositeCount(map);
	}

	@Override
	public int removeProductId(String productId) {
		return productProblemStrategyDao.removeProductId(productId);
	}

	@Override
	public void batchInsert(List<ProductProblemStrategyDO> list) {
		// TODO Auto-generated method stub
		productProblemStrategyDao.batchInsert(list);
	}
}
