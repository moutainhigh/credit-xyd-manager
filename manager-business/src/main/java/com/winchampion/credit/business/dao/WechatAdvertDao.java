package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.WechatAdvertDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 小程序广告图
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-08-07 09:30:21
 */
@Mapper
public interface WechatAdvertDao {

	WechatAdvertDO get(String id);
	
	List<WechatAdvertDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(WechatAdvertDO wechatAdvert);
	
	int update(WechatAdvertDO wechatAdvert);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
