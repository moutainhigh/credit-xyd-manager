package com.winchampion.credit.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.dao.ProductDao;
import com.winchampion.credit.business.domain.ProductDO;
import com.winchampion.credit.business.service.ProductService;
import com.winchampion.credit.business.vo.ProductHeatVo;



@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Override
	public ProductDO get(String id){
		return productDao.get(id);
	}
	
	@Override
	public List<ProductDO> list(Map<String, Object> map){
		return productDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productDao.count(map);
	}
	
	@Override
	public int save(ProductDO product){
		return productDao.save(product);
	}
	
	@Override
	public int update(ProductDO product){
		return productDao.update(product);
	}
	
	@Override
	public int remove(String id){
		return productDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return productDao.batchRemove(ids);
	}

	/**
	 * 获取当前最大产品编码
	 */
	@Override
	public String maxProductNo() {
		return productDao.maxProductNo();
	}
	/**
	 * 批量修改排序
	 * @param list
	 */
	/*@Override
	public int updateSort(List<ProductDO> list) {
		// TODO Auto-generated method stub
		return productDao.updateSort(list);
	}*/
	/**
	 * 查询产品关注热度
	 * @param map
	 * @return
	 */
	@Override
	public List<ProductHeatVo> getProductHeatList(Map<String,Object> map){
		return productDao.getProductHeatList(map);
	}
	
	/**
	 * 获取金融产品关注度列表总数量
	 * @param map
	 * @return
	 */
	@Override
	public int countHeat(Map<String,Object> map) {
		return productDao.countHeat(map);
	}
	
	/**
	 * 更改产品缺省排序
	 * @param product
	 * @return
	 */
	@Override
	public int updateSort(ProductDO product) {
		return productDao.updateSort(product);
	}

	/**
	 * 更改产品关注度
	 */
	@Override
	public int updateHeat(ProductDO product) {
		return productDao.updateHeat(product);
	}
}
