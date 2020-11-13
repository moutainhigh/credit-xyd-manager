package com.winchampion.credit.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.winchampion.credit.business.domain.ProblemDO;

/**
 * 金融产品智能匹配问题
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-02 13:09:23
 */
@Mapper
public interface ProblemDao {

	ProblemDO get(String id);
	
	List<ProblemDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProblemDO problem);
	
	int update(ProblemDO problem);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
