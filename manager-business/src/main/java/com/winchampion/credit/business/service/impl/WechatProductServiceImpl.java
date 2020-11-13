package com.winchampion.credit.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.winchampion.credit.business.dao.WechatProductDao;
import com.winchampion.credit.business.domain.WechatProductDO;
import com.winchampion.credit.business.service.WechatProductService;
import com.winchampion.credit.business.vo.WechatProductVo;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.user.utils.UserUtils;



@Service
public class WechatProductServiceImpl implements WechatProductService {
	@Autowired
	private WechatProductDao wechatProductDao;
	
	@Override
	public WechatProductVo get(String id){
		return wechatProductDao.get(id);
	}
	
	@Override
	public List<WechatProductVo> list(Map<String, Object> map){
		return wechatProductDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return wechatProductDao.count(map);
	}
	
	@Override
	public int save(WechatProductDO wechatProduct){
		return wechatProductDao.save(wechatProduct);
	}
	
	@Override
	public int update(WechatProductDO wechatProduct){
		wechatProduct.saveBefore();
		return wechatProductDao.update(wechatProduct);
	}
	
	@Override
	public int remove(String id){
		return wechatProductDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return wechatProductDao.batchRemove(ids);
	}

	@Override
	public int batchMoveIn(String[] productIds) {
		
		List<WechatProductDO> list = Lists.newArrayList();
		for(String productId : productIds) {
			WechatProductDO wechatProductDO = new WechatProductDO();
			wechatProductDO.saveBefore();
			//并且将更新时间加上
			wechatProductDO.setUpdateBy(UserUtils.getUserId());
			wechatProductDO.setUpdateDate(DateUtils.getSysDateAndTime());
			wechatProductDO.setCreditProductId(productId);
			list.add(wechatProductDO);
		}
		int count = wechatProductDao.batchInsert(list);
		return count;
	}

	@Override
	public List<String> validate(String[] productIds) {
		List<String> list = Lists.newArrayList();
		for(String productId : productIds) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("creditProductId", productId);
			List<WechatProductVo> wechatProductDOList = list(param);
			if(wechatProductDOList != null && wechatProductDOList.size() > 0) {
				WechatProductVo wechatProductVo = wechatProductDOList.get(0);
				list.add(wechatProductVo.getProductNo() + " " + wechatProductVo.getProductName());
			}
		}
		return list;
	}
	
}
