package com.winchampion.credit.web.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winchampion.credit.business.service.CustomerCompanyService;
import com.winchampion.credit.user.config.ApplicationContextRegister;
/**
 *  更新超过90天的企业状态为认证过期
 * @author zhangcong
 *
 */
public class CertificationTimer  implements Job{
	
	private Logger logger = LoggerFactory.getLogger(CertificationTimer.class);

	private CustomerCompanyService customerCompanyService = ApplicationContextRegister.getBean(CustomerCompanyService.class);
	
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		if(customerCompanyService.updateIsAuthentication()>0) {
			logger.info("已成功更新企业认证状态----------");
		}else {
			logger.info("未检测到需要更新认证状态的企业----------");
		}
	}

}
