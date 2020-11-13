package com.winchampion.credit.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winchampion.credit.business.dao.ProductApplyLogDao;
import com.winchampion.credit.business.domain.ProductApplyLogDO;
import com.winchampion.credit.business.service.ProductApplyLogService;
import com.winchampion.credit.business.vo.ApplyTimeoutVo;


@Service
public class ProductApplyLogServiceImpl implements ProductApplyLogService {
	@Autowired
	private ProductApplyLogDao productApplyLogDao;
	
	@Override
	public ProductApplyLogDO get(String id){
		return productApplyLogDao.get(id);
	}
	
	@Override
	public List<ProductApplyLogDO> list(Map<String, Object> map){
		return productApplyLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productApplyLogDao.count(map);
	}
	
	@Override
	public int save(ProductApplyLogDO productApplyLog){
		return productApplyLogDao.save(productApplyLog);
	}
	
	@Override
	public int update(ProductApplyLogDO productApplyLog){
		return productApplyLogDao.update(productApplyLog);
	}
	
	@Override
	public int remove(String id){
		return productApplyLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return productApplyLogDao.batchRemove(ids);
	}
	/**
	 * 定时任务更新超过24小时未完成的申请
	 * @param vo
	 * @return
	 */
	@Override
	public int updateApplyTimeout(ApplyTimeoutVo vo) {
		return productApplyLogDao.updateApplyTimeout(vo);
	}

	@Override
	public List<ProductApplyLogDO> sinosureFinancingList(Map<String, Object> map) {
		return productApplyLogDao.sinosureFinancingList(map);
	}

	@Override
	public int sinosureFinancingCount(Map<String, Object> map) {
		return productApplyLogDao.sinosureFinancingCount(map);
	}
	
}
