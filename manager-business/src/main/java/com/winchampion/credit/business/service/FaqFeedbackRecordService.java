package com.winchampion.credit.business.service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.domain.FaqFeedbackRecordDO;

/**
 * 问题反馈记录表
 * 
 * @author zhangcong
 * @email zhangcong@163.com
 * @date 2020-04-07 09:30:07
 */
public interface FaqFeedbackRecordService {
	
	FaqFeedbackRecordDO get(String id);
	
	List<FaqFeedbackRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FaqFeedbackRecordDO faqFeedbackRecord);
	
	int update(FaqFeedbackRecordDO faqFeedbackRecord);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
