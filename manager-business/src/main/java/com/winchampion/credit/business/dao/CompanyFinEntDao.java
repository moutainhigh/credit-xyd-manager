package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.CompanyFinEntDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 企业融资需求可发布金融机构
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-29 10:58:35
 */
@Mapper
public interface CompanyFinEntDao {

	CompanyFinEntDO get(String id);
	
	List<CompanyFinEntDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CompanyFinEntDO companyFinEnt);
	
	int update(CompanyFinEntDO companyFinEnt);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
