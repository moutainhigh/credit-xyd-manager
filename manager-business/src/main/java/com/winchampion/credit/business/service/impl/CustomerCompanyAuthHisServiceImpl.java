package com.winchampion.credit.business.service.impl;

import com.winchampion.credit.business.dao.CustomerCompanyAuthHisDao;
import com.winchampion.credit.business.domain.CustomerCompanyAuthHisDO;
import com.winchampion.credit.business.service.CustomerCompanyAuthHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerCompanyAuthHisServiceImpl implements CustomerCompanyAuthHisService {
	@Autowired
	private CustomerCompanyAuthHisDao customerCompanyAuthHisDao;
	
	@Override
	public CustomerCompanyAuthHisDO get(String id){
		return customerCompanyAuthHisDao.get(id);
	}
	
	@Override
	public List<CustomerCompanyAuthHisDO> list(Map<String, Object> map){
		return customerCompanyAuthHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return customerCompanyAuthHisDao.count(map);
	}

}
