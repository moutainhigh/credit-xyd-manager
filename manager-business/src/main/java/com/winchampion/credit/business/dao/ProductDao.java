package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.ProductDO;
import com.winchampion.credit.business.vo.ProductHeatVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 金融产品表
 * @author zhangcong
 * @email 15121007361@163.com
 * @date 2020-02-27 12:06:01
 */
@Mapper
public interface ProductDao {

	ProductDO get(String id);
	
	List<ProductDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProductDO product);
	
	int update(ProductDO product);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
	/**
	 * 获取当前最大产品编码
	 * @return
	 */
	String maxProductNo();
	/**
	 * 批量修改排序
	 * @param list
	 */
	//int updateSort(List<ProductDO> list);
	/**
	 * 查询产品关注热度
	 * @param map
	 * @return
	 */
	List<ProductHeatVo> getProductHeatList(Map<String,Object> map);
	/**
	 * 获取金融产品关注度列表总数量
	 * @param map
	 * @return
	 */
	int countHeat(Map<String,Object> map);
	
	/**
	 * 更改产品缺省排序
	 * @param product
	 * @return
	 */
	int updateSort(ProductDO product);
	/**
	 * 更改产品关注度
	 * @param product
	 * @return
	 */
	int updateHeat(ProductDO product);
}
