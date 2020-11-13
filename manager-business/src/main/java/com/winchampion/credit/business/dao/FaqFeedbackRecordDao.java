package com.winchampion.credit.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.winchampion.credit.business.domain.FaqFeedbackRecordDO;

/**
 * 问题反馈记录表
 * @author zhangcong
 * @email zhangcong@163.com
 * @date 2020-04-07 09:30:07
 */
@Mapper
public interface FaqFeedbackRecordDao {

	FaqFeedbackRecordDO get(String id);
	
	List<FaqFeedbackRecordDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(FaqFeedbackRecordDO faqFeedbackRecord);
	
	int update(FaqFeedbackRecordDO faqFeedbackRecord);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
