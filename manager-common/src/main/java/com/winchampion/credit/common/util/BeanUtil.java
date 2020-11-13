package com.winchampion.credit.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Bizfocus China . 
 * @author: zhangxin  
 * @date:2016年10月12日
 * @time:上午10:42:04
 * @email:nero.zhang@bizfocus.cn  
 * @version: 1.0
 */
public class BeanUtil {
	 /** 
     * 利用反射实现对象之间相同属性复制 
     *  
     * @param source 要复制的 
     * @param to     复制给 
     */  
    public static void copyProperties(Object source, Object target) throws Exception {  
          
        copyPropertiesExclude(source, target, null);  
    }  
  
    /** 
     * 复制对象属性 
     *  
     * @param from 
     * @param to 
     * @param excludsArray 
     *            排除属性列表 
     * @throws Exception 
     */  
    public static void copyPropertiesExclude(Object from, Object to,  
            String[] excludsArray) throws Exception {  
          
        List<String> excludesList = null;  
          
        if (excludsArray != null && excludsArray.length > 0) {  
              
            excludesList = Arrays.asList(excludsArray); // 构造列表对象  
        }  
          
        Method[] fromMethods = from.getClass().getDeclaredMethods();  
        Method[] toMethods = to.getClass().getDeclaredMethods();  
        Method fromMethod = null, toMethod = null;  
        String fromMethodName = null, toMethodName = null;  
          
        for (int i = 0; i < fromMethods.length; i++) {  
              
            fromMethod = fromMethods[i];  
            fromMethodName = fromMethod.getName();  
              
            if (!fromMethodName.contains("get"))  
                continue;  
            // 排除列表检测  
            if (excludesList != null  
                    && excludesList.contains(fromMethodName.substring(3)  
                            .toLowerCase())) {  
                  
                continue;  
            }  
            toMethodName = "set" + fromMethodName.substring(3);  
            toMethod = findMethodByName(toMethods, toMethodName);  
              
            if (toMethod == null)  
                continue;  
            Object value = fromMethod.invoke(from, new Object[0]);  
              
            if (value == null)  
                continue;  
            // 集合类判空处理  
            if (value instanceof Collection) {  
                  
                Collection<?> newValue = (Collection<?>) value;  
                  
                if (newValue.size() <= 0)  
                    continue;  
            }  
              
            toMethod.invoke(to, new Object[] { value });  
        }  
    }  
  
    /** 
     * 对象属性值复制，仅复制指定名称的属性值 
     *  
     * @param from 
     * @param to 
     * @param includsArray 
     * @throws Exception 
     */  
    public static void copyPropertiesInclude(Object from, Object to,  
            String[] includsArray) throws Exception {  
          
        List<String> includesList = null;  
          
        if (includsArray != null && includsArray.length > 0) {  
              
            includesList = Arrays.asList(includsArray);   
              
        } else {  
              
            return;  
        }  
        Method[] fromMethods = from.getClass().getDeclaredMethods();  
        Method[] toMethods = to.getClass().getDeclaredMethods();  
        Method fromMethod = null, toMethod = null;  
        String fromMethodName = null, toMethodName = null;  
          
        for (int i = 0; i < fromMethods.length; i++) {  
              
            fromMethod = fromMethods[i];  
            fromMethodName = fromMethod.getName();  
              
            if (!fromMethodName.contains("get"))  
                continue;  
              
            // 排除列表检测  
            String str = fromMethodName.substring(3);  
              
            if (!includesList.contains(str.substring(0, 1).toLowerCase()  
                    + str.substring(1))) {  
                continue;  
            }  
              
            toMethodName = "set" + fromMethodName.substring(3);  
            toMethod = findMethodByName(toMethods, toMethodName);  
              
            if (toMethod == null)  
                continue;  
              
            Object value = fromMethod.invoke(from, new Object[0]);  
              
            if (value == null)  
                continue;  
              
            // 集合类判空处理  
            if (value instanceof Collection) {  
                  
                Collection<?> newValue = (Collection<?>) value;  
                  
                if (newValue.size() <= 0)  
                    continue;  
            }  
              
            toMethod.invoke(to, new Object[] { value });  
        }  
    }  
  
    /** 
     * 从方法数组中获取指定名称的方法 
     *  
     * @param methods 
     * @param name 
     * @return 
     */  
    public static Method findMethodByName(Method[] methods, String name) {  
          
        for (int j = 0; j < methods.length; j++) {  
              
            if (methods[j].getName().equals(name)) {  
                  
                return methods[j];  
            }  
                  
        }  
        return null;  
    }  
    
    /**
     * 复制源对象和目标对象的属性值
     * @author: zhangxin  
     * @date:2016年10月12日  下午1:49:36
     * @param to
     * @param from
     */
    public static void copy(Object to,Object from){
    	copy(to, from, "0");
    }
    
   /***
    * 复制源对象和目标对象的属性值
    * @author: zhangxin  
    * @date:2016年10月12日  下午1:48:03
    * @param to
    * @param from
    * @param hasSup 1 在去取父类【不建议】，0本类
    */
    @SuppressWarnings({"rawtypes","unchecked"})
	public static void copy(Object to,Object from,String hasSup){
    	try{
    		Class sourceClass = from.getClass();//得到对象的Class
			Class targetClass = to.getClass();//得到对象的Class
			
			Field[] sourceFields = sourceClass.getDeclaredFields();//得到Class对象的所有属性
			Field[] targetFields = targetClass.getDeclaredFields();//得到Class对象的所有属性
			
			for(Field sourceField : sourceFields){
				String name = sourceField.getName();//属性名
				Class type = sourceField.getType();//属性类型
				
				String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
				
				try {
					Method getMethod = sourceClass.getMethod("get" + methodName);//得到属性对应get方法
					
					Object value = getMethod.invoke(from);//执行源对象的get方法得到属性值
					
					for(Field targetField : targetFields){
						String targetName = targetField.getName();//目标对象的属性名
						
						if(targetName.equals(name)){
							Method setMethod = targetClass.getMethod("set" + methodName, type);//属性对应的set方法
							
							setMethod.invoke(to, value);//执行目标对象的set方法
						}
					}
				} catch (NoSuchMethodException | SecurityException e) {
					//不处理
				}
			}
			//取父类
			if("1".equals(hasSup)){
				Class supClazz = sourceClass.getSuperclass();
				Field[] supSourceFields = supClazz.getDeclaredFields();//得到Class对象的所有属性
				for(Field supSourceField : supSourceFields){
					String name = supSourceField.getName();//属性名
					Class type = supSourceField.getType();//属性类型
					
					String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
					
					try {
						Method getMethod = sourceClass.getMethod("get" + methodName);//得到属性对应get方法
						
						Object value = getMethod.invoke(from);//执行源对象的get方法得到属性值
						
						for(Field targetField : targetFields){
							String targetName = targetField.getName();//目标对象的属性名
							
							if(targetName.equals(name)){
								Method setMethod = targetClass.getMethod("set" + methodName, type);//属性对应的set方法
								
								setMethod.invoke(to, value);//执行目标对象的set方法
							}
						}
					} catch (NoSuchMethodException | SecurityException e) {
						// 不处理
					}
				}
			}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
    
    /**
     * list 和对象 转MAP
     * @author: zhangxin  
     * @date:2016年12月21日  上午11:04:45
     * @param bean
     * @return
     */
    public static Object beanToMap(Object bean){
		
		if(bean instanceof List){
			return listBeanToMapAll(bean);
		}
		
		return beanToMapAll(bean);
	}
	
    /***
     * list 转Map
     * @author: zhangxin  
     * @date:2016年12月21日  上午11:04:29
     * @param beans
     * @return
     */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> listBeanToMapAll(Object beans) {
		List<Map<String, Object>> lists = Lists.newArrayList();
		
		List<Object> beanList = (List<Object>) beans;
		
		for(Object bean: beanList){
			lists.add(beanToMapAll(bean));
		}
		
		return lists;
	}
	
	/**
	 * 对象转Map
	 * @author: zhangxin  
	 * @date:2016年12月21日  上午11:04:15
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> beanToMapAll(Object bean) {
		Map<String, Object> params = Maps.newHashMap();
		
		try {
			Class<?> clazz = bean.getClass();
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);

				Object obj = field.get(bean);

				params.put(field.getName(), obj);
			}
			
			Class<?> supClazz = clazz.getSuperclass();
//			if(supClazz!=null){
				Field[] supSourceFields = supClazz.getDeclaredFields();//得到Class对象的所有属性
				for(Field supSourceField : supSourceFields){
					supSourceField.setAccessible(true);
					Object obj = supSourceField.get(bean);
					params.put(supSourceField.getName(), obj);
				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
    
	/**
	 * 复制List
	 * @author songtx
	 * @date 2016年11月22日 下午3:48:40
	 * @param classS  返回LIST 的类型
	 * @param listT 源LIST
	 * @param listS 返回LIST
	 */
	public static <T,S> void copy(Class<S> classS, List<T> listT,List<S> listS){
		if(listT == null || listT.isEmpty()){
			return;
		}
		try{
			for(T item : listT){
				S itemNew = classS.newInstance();
				copy(itemNew, item);
				listS.add(itemNew);
			}
			
		}catch(InstantiationException | IllegalAccessException ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 复制List
	 * @author songtx
	 * @date 2016年11月22日 下午3:48:40
	 * @param classS  返回LIST 的类型
	 * @param listT 源LIST
	 * @param listS 返回LIST
	 */
	public static <T,S> List<S> copy(Class<S> classS, List<T> listT){
		List<S> listS = new ArrayList<S>();
		if(listT == null || listT.isEmpty()){
			return listS;
		}
		
		try{
			for(T item : listT){
				S itemNew = classS.newInstance();
				copy(itemNew, item);
				listS.add(itemNew);
			}
			return listS;
		}catch(InstantiationException | IllegalAccessException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 复制List-存在父类情况
	 * @author songtx
	 * @date 2016年11月22日 下午3:48:40
	 * @param classS
	 * @param listT
	 * @param listS
	 */
	public static <T,S> void copy(Class<S> classS, List<T> listT,List<S> listS,String hasSup){
		if(listT == null || listT.isEmpty()){
			return;
		}
		try{
			for(T item : listT){
				S itemNew = classS.newInstance();
				BeanUtil.copy(itemNew, item,hasSup);
				listS.add(itemNew);
			}
		}catch(InstantiationException | IllegalAccessException ex){
			ex.printStackTrace();
		}
	}
	
	
	public static <T> List<T> fillProperty(List<T> listT, String propName, String propValue) {
		if (CollectionUtils.isEmpty(listT)) {
			return new ArrayList<T>();
		}
		if (StringUtils.isBlank(propName)) {
			return listT;
		}
		for (T t : listT) {
			try {
				Field field = t.getClass().getDeclaredField(propName);
				field.setAccessible(true);
				field.set(t, propValue);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return listT;
	}
	
	/**
	 * copy 某个值到 对象属性中
	 * @author: zhangxin  
	 * @param <T>
	 * @date:2018年4月16日  下午5:03:39
	 * @param t copy对象
	 * @param value copy 值
	 * @param propName 属性
	 */
	public static <T> void copyProperties(T t, String value, String propName) {
		try {
			if(t == null){
				return;
			}
			Field field = t.getClass().getDeclaredField(propName);
			if(field != null){
				field.setAccessible(true);
				field.set(t, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * copy 某个值到 List对象属性中
	 * @author: zhangxin  
	 * @date:2018年4月16日  下午5:12:34
	 * @param ListT
	 * @param value
	 * @param propName
	 */
	public static <T> void copyProperties(List<T> ListT, String value, String propName) {
		try {
			if(ListT == null || ListT.size()<=0){
				return;
			}
			for(T t : ListT){
				copyProperties(t, value, propName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
