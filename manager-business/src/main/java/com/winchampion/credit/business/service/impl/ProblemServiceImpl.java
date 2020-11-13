package com.winchampion.credit.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winchampion.credit.business.dao.ProblemDao;
import com.winchampion.credit.business.domain.ProblemDO;
import com.winchampion.credit.business.service.ProblemService;

@Service
public class ProblemServiceImpl implements ProblemService {
	@Autowired
	private ProblemDao problemDao;
	
	@Override
	public ProblemDO get(String id){
		return problemDao.get(id);
	}
	
	@Override
	public List<ProblemDO> list(Map<String, Object> map){
		return problemDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return problemDao.count(map);
	}
	
	@Override
	public int save(ProblemDO problem){
		return problemDao.save(problem);
	}
	
	@Override
	public int update(ProblemDO problem){
		return problemDao.update(problem);
	}
	
	@Override
	public int remove(String id){
		return problemDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return problemDao.batchRemove(ids);
	}
	
}
