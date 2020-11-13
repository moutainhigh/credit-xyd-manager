package com.winchampion.credit.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winchampion.credit.business.dao.FaqFeedbackRecordDao;
import com.winchampion.credit.business.domain.FaqFeedbackRecordDO;
import com.winchampion.credit.business.service.FaqFeedbackRecordService;

@Service
public class FaqFeedbackRecordServiceImpl implements FaqFeedbackRecordService {
	@Autowired
	private FaqFeedbackRecordDao faqFeedbackRecordDao;
	
	@Override
	public FaqFeedbackRecordDO get(String id){
		return faqFeedbackRecordDao.get(id);
	}
	
	@Override
	public List<FaqFeedbackRecordDO> list(Map<String, Object> map){
		return faqFeedbackRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return faqFeedbackRecordDao.count(map);
	}
	
	@Override
	public int save(FaqFeedbackRecordDO faqFeedbackRecord){
		return faqFeedbackRecordDao.save(faqFeedbackRecord);
	}
	
	@Override
	public int update(FaqFeedbackRecordDO faqFeedbackRecord){
		return faqFeedbackRecordDao.update(faqFeedbackRecord);
	}
	
	@Override
	public int remove(String id){
		return faqFeedbackRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return faqFeedbackRecordDao.batchRemove(ids);
	}
	
}
