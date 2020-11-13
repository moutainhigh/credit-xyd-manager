package com.winchampion.credit.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winchampion.credit.business.dao.CompanyFinanceNeedsDao;
import com.winchampion.credit.business.domain.CompanyFinanceNeedsDO;
import com.winchampion.credit.business.service.CompanyFinanceNeedsService;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.user.utils.UserUtils;


/***
 * 企业融资需求
 * Created by WIN-CHAMPION China . 
 * @author: zhangxin  
 * @date:2020年2月25日
 * @time:上午10:05:44
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
@Service
public class CompanyFinanceNeedsServiceImpl implements CompanyFinanceNeedsService {
	@Autowired
	private CompanyFinanceNeedsDao companyFinanceNeedsDao;
	
	@Override
	public CompanyFinanceNeedsDO get(String id){
		return companyFinanceNeedsDao.get(id);
	}
	
	@Override
	public List<CompanyFinanceNeedsDO> list(Map<String, Object> map){
		//设置值
		
		Map<String, Object> param = new HashMap<>(map);
		
		if(param.get("acceptStatus") != null) {
			String acceptStatus = (String) param.get("acceptStatus") ;
			if("-1".equals(acceptStatus)) {
				param.put("acceptStatus","");
			}
		}
		
		return companyFinanceNeedsDao.list(param);
	}
	
	@Override
	public int count(Map<String, Object> map){
		Map<String, Object> param = new HashMap<>(map);
		
		if(param.get("acceptStatus") != null) {
			String acceptStatus = (String) param.get("acceptStatus") ;
			if("-1".equals(acceptStatus)) {
				param.put("acceptStatus","");
			}
		}
		return companyFinanceNeedsDao.count(param);
	}
	
	@Override
	public int save(CompanyFinanceNeedsDO companyFinanceNeeds){
		return companyFinanceNeedsDao.save(companyFinanceNeeds);
	}
	
	@Override
	public int update(CompanyFinanceNeedsDO companyFinanceNeeds){
		//设置当前修改人
		companyFinanceNeeds.setAcceptBy(UserUtils.getUserId());
		companyFinanceNeeds.setAcceptDate(DateUtils.getSysDateAndTime());
		return companyFinanceNeedsDao.update(companyFinanceNeeds);
	}
	
	@Override
	public int remove(String id){
		return companyFinanceNeedsDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return companyFinanceNeedsDao.batchRemove(ids);
	}

	@Override
	public List<CompanyFinanceNeedsDO> listByCustomerId(Map<String, Object> map) {
		List<CompanyFinanceNeedsDO> companyFinanceNeedsDOList = companyFinanceNeedsDao.listByCustomerId(map);
		return companyFinanceNeedsDOList;
	}

	@Override
	public int countByCustomerId(Map<String, Object> map) {
		return companyFinanceNeedsDao.countByCustomerId(map);
	}
}
