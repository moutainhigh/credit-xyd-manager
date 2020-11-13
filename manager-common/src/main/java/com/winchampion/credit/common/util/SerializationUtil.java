package com.winchampion.credit.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化公共类
 * Created by Bizfocus China . 
 * @author: zhangxin  
 * @date:2016年8月15日
 * @time:上午10:01:21
 * @email:nero.zhang@bizfocus.cn  
 * @version: 1.0
 */
public class SerializationUtil extends SerializationUtils{
	
	private static Logger logger = LoggerFactory.getLogger(SerializationUtil.class);
	
	 /**
     * 序列化
     * 
     * @param object
     * @return
     */
    public static byte[] serializeToByteArray(Serializable object) {
    	
    	return serialize(object);
    	
      /*  ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        	logger.debug(e.getMessage());
        }finally{
        	try {
        		if (baos != null) {
        			baos.close();
        		}
        		if (oos != null) {
        			oos.close();
        		}
			} catch (IOException e) {
				logger.debug(e.getMessage());
			}
        }
        return null;*/
    }
	
    /**
     * 序列化
     * 
     * @param object
     * @return
     */
    public static String serializeToString(Serializable object) {
    	
    
    	
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            
            String serStr = baos.toString("ISO-8859-1");//必须是ISO-8859-1
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
            return serStr;
        } catch (Exception e) {
        	logger.debug(e.getMessage());
        }finally{
        	try {
        		if (baos != null) {
        			baos.close();
        		}
        		if (oos != null) {
        			oos.close();
        		}
			} catch (IOException e) {
				logger.debug(e.getMessage());
			}
        }
        return null;
    }
    
    
	@SuppressWarnings("unchecked")
	public static <T> T unSerialize(String source,Class<T> clazz) {
	   ByteArrayInputStream bais = null;
	   ObjectInputStream ois = null;
        try {
        	String redStr = java.net.URLDecoder.decode(source, "UTF-8");
            bais = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
            ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
        	logger.debug(e.getMessage());
        }finally{
        	try {
        		if (bais != null) {
        			bais.close();
        		}
        		if (ois != null) {
        			ois.close();
        		}
			} catch (IOException e) {
				logger.debug(e.getMessage());
			}
        }
        return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T unSerialize(byte[] source,Class<T> clazz) {
		
		return (T) SerializationUtils.deserialize(source);
		
	  /* ByteArrayInputStream bais = null;
	   ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(source);
            ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
        	logger.debug(e.getMessage());
        }finally{
        	try {
        		if (bais != null) {
        			bais.close();
        		}
        		if (ois != null) {
        			ois.close();
        		}
			} catch (IOException e) {
				logger.debug(e.getMessage());
			}
        }
        return null;*/
	}
}
