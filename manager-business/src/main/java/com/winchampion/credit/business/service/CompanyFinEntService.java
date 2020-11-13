package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.CompanyFinEntDO;

import java.util.List;
import java.util.Map;

/**
 * 企业融资需求可发布金融机构
 * 
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-29 10:58:35
 */
public interface CompanyFinEntService {
	
	CompanyFinEntDO get(String id);
	
	List<CompanyFinEntDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompanyFinEntDO companyFinEnt);
	
	int update(CompanyFinEntDO companyFinEnt);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
