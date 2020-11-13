package com.winchampion.credit.business.service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.domain.ProblemDO;

/**
 * 金融产品智能匹配问题
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-02 13:09:23
 */
public interface ProblemService {
	
	ProblemDO get(String id);
	
	List<ProblemDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProblemDO problem);
	
	int update(ProblemDO problem);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
