package com.winchampion.credit.common.redis;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis String 炒作
 * Created by Champion China . 
 * @author: zhangxin  
 * @date:2020年3月3日
 * @time:下午12:19:14
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
@Component
public class RedisString {
	
	
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 新增一个字符串类型的值,key是键，value是值。
     *
     * set(K key, V value)
     */
    public void set(String key, String value) {
        // 存入永久数据
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /***
     * 插入，秒
     * @author: zhangxin  
     * @date:2020年3月3日  下午12:23:01
     * @param key
     * @param value
     * @param timeout
     */
    public void set(String key, String value,long timeout) {
        // 也可以向redis里存入数据和设置缓存时间
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }
    

    /**
     * 如果不存在则插入，返回true为插入成功,false失败
     *
     * setIfAbsent(K key, V value)
     */
    public boolean setIfAbsent(String key, String value) {
        Boolean absent = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        System.out.println(absent);
        return absent;
    }
    
    /**
     * 如果不存在则插入，返回true为插入成功,false失败  秒
     * @author: zhangxin  
     * @date:2020年3月3日  下午12:24:53
     * @param key
     * @param value
     * @return
     */
   public boolean setIfAbsent(String key, String value,long timeout) {
        Boolean absent = stringRedisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
        System.out.println(absent);
        return absent;
    }
    /**
     * 获取值,key不存在返回null
     *
     * get(Object key)
     */
    public String get(String key) {
       return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除指定key,成功返回true，否则false
     * 
     * delete(k key)
     */
    public boolean delete(String key) {
        Boolean delete = stringRedisTemplate.opsForValue().getOperations().delete(key);
        System.out.println(delete);
        return delete;
    }

    /**
     * 删除多个key，返回删除key的个数
     * 
     * delete(k ...keys)
     */
    public Long deleteMulti(String... keys) {
        Long delete = stringRedisTemplate.opsForValue().getOperations().delete(Arrays.asList(keys));
        System.out.println(delete);
        return delete;
    }
}
