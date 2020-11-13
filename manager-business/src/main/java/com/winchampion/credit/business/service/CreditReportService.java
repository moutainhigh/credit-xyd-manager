package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.CreditReportDo;

import java.util.List;
import java.util.Map;

/**
 * 信用报告 Service
 * @author liwei
 * @date 2020-02-27
 */
public interface CreditReportService {
	
	List<CreditReportDo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
}
