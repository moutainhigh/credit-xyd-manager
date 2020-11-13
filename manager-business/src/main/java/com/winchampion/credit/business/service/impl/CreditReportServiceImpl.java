package com.winchampion.credit.business.service.impl;

import com.winchampion.credit.business.dao.CreditReportDao;
import com.winchampion.credit.business.domain.CreditReportDo;
import com.winchampion.credit.business.service.CreditReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CreditReportServiceImpl implements CreditReportService {
	@Autowired
	private CreditReportDao creditReportDao;

	@Override
	public List<CreditReportDo> list(Map<String, Object> map){
		return creditReportDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return creditReportDao.count(map);
	}
}
