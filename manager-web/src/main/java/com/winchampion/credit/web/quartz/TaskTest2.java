package com.winchampion.credit.web.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.winchampion.credit.business.service.AdvertService;
import com.winchampion.credit.user.config.ApplicationContextRegister;

/**
 * Created by Bizfocus China . 
 * @author: zhangxin  
 * @date:2020年4月15日
 * @time:上午9:54:28
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public class TaskTest2 implements Job{
	
	private AdvertService AdvertService = ApplicationContextRegister.getBean(AdvertService.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("执行啦0002" + new Date());
		System.out.println(AdvertService.get("093eaafefd4343d38f17c2fe93e4bef9"));
	}
	
}
