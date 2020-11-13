package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.CreditReportDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 信用报告 Dao
 * @author liwei
 * @date 2020-02-27
 */
@Mapper
public interface CreditReportDao {

	List<CreditReportDo> list(Map<String, Object> map);

	int count(Map<String, Object> map);
}
