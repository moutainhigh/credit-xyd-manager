package com.winchampion.credit.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.dao.ProductInfoDao;
import com.winchampion.credit.business.domain.ProductInfoDO;
import com.winchampion.credit.business.service.ProductInfoService;



@Service
public class ProductInfoServiceImpl implements ProductInfoService {
	@Autowired
	private ProductInfoDao productInfoDao;
	
	@Override
	public ProductInfoDO get(String id){
		return productInfoDao.get(id);
	}
	
	@Override
	public List<ProductInfoDO> list(Map<String, Object> map){
		return productInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productInfoDao.count(map);
	}
	
	@Override
	public int save(ProductInfoDO productInfo){
		return productInfoDao.save(productInfo);
	}
	
	@Override
	public int update(ProductInfoDO productInfo){
		return productInfoDao.update(productInfo);
	}
	
	@Override
	public int remove(String id){
		return productInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return productInfoDao.batchRemove(ids);
	}
	/**
	 * 批量新增
	 * @param list
	 */
	@Override
	public void batchInsert(List<ProductInfoDO> list) {
		productInfoDao.batchInsert(list);
	}
}
