package com.winchampion.credit.business.service.impl;

import com.winchampion.credit.business.dao.CustomerCompanyDao;
import com.winchampion.credit.business.domain.CustomerCompanyDO;
import com.winchampion.credit.business.service.CustomerCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerCompanyServiceImpl implements CustomerCompanyService {
	@Autowired
	private CustomerCompanyDao customerCompanyDao;
	
	@Override
	public CustomerCompanyDO get(String id){
		return customerCompanyDao.get(id);
	}
	
	@Override
	public List<CustomerCompanyDO> list(Map<String, Object> map){
		return customerCompanyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return customerCompanyDao.count(map);
	}

	@Override
	public CustomerCompanyDO getCompanyByCustomerId(String customerNo) {
		return customerCompanyDao.getCompanyByCustomerId(customerNo);
	}

	@Override
	public int updateIsAuthentication() {
		return customerCompanyDao.updateIsAuthentication();
	}
}
