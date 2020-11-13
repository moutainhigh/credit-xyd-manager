package com.winchampion.credit.user.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.winchampion.credit.common.redis.RedisString;
import com.winchampion.credit.common.util.SerializationUtil;
import com.winchampion.credit.common.util.StringUtils;
import com.winchampion.credit.user.config.ApplicationContextRegister;
import com.winchampion.credit.user.dao.DictDao;
import com.winchampion.credit.user.domain.DictDO;

/**
 * Created by Bizfocus China . 
 * @author: zhangxin  
 * @date:2020年3月12日
 * @time:下午3:40:45
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public class DictUtils {
	
	private static DictDao dictDao = ApplicationContextRegister.getBean(DictDao.class);

	private static RedisString redisString = ApplicationContextRegister.getBean(RedisString.class);
	
	public static final String CACHE_DICT_MAP = "XYD_DICTMAP_";
	
	public static void remove(String type){
		redisString.delete(CACHE_DICT_MAP + type);
	}
	
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (DictDO dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getName();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (DictDO dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getName())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<DictDO> getDictList(String type){
		@SuppressWarnings("unchecked")
		List<DictDO> dictList = SerializationUtil.unSerialize(redisString.get(CACHE_DICT_MAP + type), ArrayList.class);
		if (dictList == null || dictList.size() <= 0){
			Map<String, Object> param = Maps.newHashMap();
	        param.put("type", type);
	        dictList = dictDao.list(param);
	        if(dictList != null && dictList.size() > 0) {
	        	redisString.set(CACHE_DICT_MAP + type, SerializationUtil.serializeToString((Serializable) dictList));
	        }
		}
		if(dictList == null) {
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
}
