package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.ProductInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 金融产品详情表
 * 
 * @author zhangcong
 * @email 1992lcg@163.com
 * @date 2020-02-28 14:32:12
 */
public interface ProductInfoService {
	
	ProductInfoDO get(String id);
	
	List<ProductInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductInfoDO productInfo);
	
	int update(ProductInfoDO productInfo);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
	/**
	 * 批量新增
	 * @param list
	 */
	void batchInsert(List<ProductInfoDO> list);
}
