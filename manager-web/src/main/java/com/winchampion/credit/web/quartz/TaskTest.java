package com.winchampion.credit.web.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by Bizfocus China . 
 * @author: zhangxin  
 * @date:2020年4月15日
 * @time:上午9:54:28
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public class TaskTest implements Job{
	
	public void test01() {
		System.out.println("执行啦");
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("执行啦0001" + new Date());
	}
	
}
