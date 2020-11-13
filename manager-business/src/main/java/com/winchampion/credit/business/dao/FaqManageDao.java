package com.winchampion.credit.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.winchampion.credit.business.domain.FaqManageDO;

/**
 * 常见问题管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-03 16:58:56
 */
@Mapper
public interface FaqManageDao {

	FaqManageDO get(String id);
	
	List<FaqManageDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(FaqManageDO faqManage);
	
	int update(FaqManageDO faqManage);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
