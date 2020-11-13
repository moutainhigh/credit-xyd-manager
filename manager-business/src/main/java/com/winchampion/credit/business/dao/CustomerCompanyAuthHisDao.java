package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.CustomerCompanyAuthHisDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 前台用户企业认证状态变更 Dao
 * @author liwei
 * @date 2020-03-05
 */
@Mapper
public interface CustomerCompanyAuthHisDao {

	CustomerCompanyAuthHisDO get(String id);

	List<CustomerCompanyAuthHisDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
}
