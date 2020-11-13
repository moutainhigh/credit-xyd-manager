package com.winchampion.credit.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.dao.ProductOperateLogDao;
import com.winchampion.credit.business.domain.ProductOperateLogDO;
import com.winchampion.credit.business.service.ProductOperateLogService;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.common.util.IdGen;
import com.winchampion.credit.user.utils.UserUtils;



@Service
public class ProductOperateLogServiceImpl implements ProductOperateLogService {
	@Autowired
	private ProductOperateLogDao productOperateLogDao;
	
	@Override
	public ProductOperateLogDO get(String id){
		return productOperateLogDao.get(id);
	}
	
	@Override
	public List<ProductOperateLogDO> list(Map<String, Object> map){
		return productOperateLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productOperateLogDao.count(map);
	}
	
	@Override
	public int save(ProductOperateLogDO productOperateLog){
		return productOperateLogDao.save(productOperateLog);
	}
	
	@Override
	public int update(ProductOperateLogDO productOperateLog){
		return productOperateLogDao.update(productOperateLog);
	}
	
	@Override
	public int remove(String id){
		return productOperateLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return productOperateLogDao.batchRemove(ids);
	}
	
	@Override
	public void addLog(String productNo,String operate,String recycleDes,String productType){
		ProductOperateLogDO log = new ProductOperateLogDO();
		log.setId(IdGen.uuid());
		log.setProductId(productNo);
		log.setOperate(operate);
		log.setOperateDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		log.setOperateBy(UserUtils.getUserId());
		log.setOperateRemark(recycleDes);
		log.setProductType(productType);
		productOperateLogDao.save(log);
	}
}
