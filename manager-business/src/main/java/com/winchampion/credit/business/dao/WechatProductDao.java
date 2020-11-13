package com.winchampion.credit.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.winchampion.credit.business.domain.WechatProductDO;
import com.winchampion.credit.business.vo.WechatProductVo;

/**
 * 小程序产品
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-08-07 16:19:20
 */
@Mapper
public interface WechatProductDao {

	WechatProductVo get(String id);
	
	List<WechatProductVo> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(WechatProductDO wechatProduct);
	
	int update(WechatProductDO wechatProduct);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int batchInsert(List<WechatProductDO> list);

}
