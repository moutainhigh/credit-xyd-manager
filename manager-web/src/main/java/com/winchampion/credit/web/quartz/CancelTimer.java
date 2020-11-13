package com.winchampion.credit.web.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winchampion.credit.business.service.ProductApplyLogService;
import com.winchampion.credit.business.vo.ApplyTimeoutVo;
import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.user.config.ApplicationContextRegister;
/**
 * 修改申请超时的产品申请记录状态为取消申请
 * @author zhangcong
 *
 */
public class CancelTimer implements Job{
	private Logger logger = LoggerFactory.getLogger(CertificationTimer.class);

	private ProductApplyLogService productApplyLogService = ApplicationContextRegister.getBean(ProductApplyLogService.class);
	/**
	 * 修改申请超时的产品申请记录状态为取消申请
	 * product_apply_log
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		ApplyTimeoutVo applyTimeoutVo = new ApplyTimeoutVo();
		applyTimeoutVo.setType1(Constant.PRODUCT_APPLY_RESULT_TWO);
		applyTimeoutVo.setType2(Constant.PRODUCT_APPLY_RESULT_ONE);
		if(productApplyLogService.updateApplyTimeout(applyTimeoutVo)>0) {
			logger.info("已成功更新申请超时的记录的状态----------");
		}else {
			logger.info("未检测到需要更新的申请记录----------");
		}
	}
	
}
