package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.CustomerDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 前台用户 Dao
 * @author liwei
 * @date 2020-02-25
 */
@Mapper
public interface CustomerDao {

	CustomerDO get(String id);

	CustomerDO getByCustomerNo(String customerNo);

	List<CustomerDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(CustomerDO customerDO);
	
	int update(CustomerDO customerDO);
}
