package com.winchampion.credit.web.service;

import com.winchampion.credit.user.domain.LogDO;
import com.winchampion.credit.user.domain.PageDO;
import com.winchampion.credit.common.util.Query;
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
