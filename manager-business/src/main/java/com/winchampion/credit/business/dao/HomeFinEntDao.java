package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.HomeFinEntDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 首页合作金融/信用机构 Dao
 * @author liwei
 */
@Mapper
public interface HomeFinEntDao {

	HomeFinEntDO get(String id);

	List<HomeFinEntDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(HomeFinEntDO homeFinEntDO);

	int remove(String id);

	int update(HomeFinEntDO homeFinEntDO);
}
