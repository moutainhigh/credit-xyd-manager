package com.winchampion.credit.business.service;

import java.util.List;
import java.util.Map;

import com.winchampion.credit.business.domain.WechatProductDO;
import com.winchampion.credit.business.vo.WechatProductVo;

/**
 * 小程序产品
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-08-07 16:19:20
 */
public interface WechatProductService {
	
	WechatProductVo get(String id);
	
	List<WechatProductVo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WechatProductDO wechatProduct);
	
	int update(WechatProductDO wechatProduct);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int batchMoveIn(String[] productIds);
	
	/**
	 * 判断产品是否已经添加
	 * @author: zhangxin  
	 * @date:2020年8月7日  下午5:36:40
	 * @param productIds
	 * @return
	 */
	List<String> validate(String[] productIds);
}
