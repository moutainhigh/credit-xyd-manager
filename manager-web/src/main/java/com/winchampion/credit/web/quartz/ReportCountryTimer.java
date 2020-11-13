package com.winchampion.credit.web.quartz;

import com.winchampion.credit.business.domain.UploadIncrementDO;
import com.winchampion.credit.business.domain.UploadQuantityDO;
import com.winchampion.credit.business.service.ReportCountryService;
import com.winchampion.credit.common.interfaces.country.CountryInterfaceUtils;
import com.winchampion.credit.user.config.ApplicationContextRegister;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/28
 * @description: 每日9点调 上报国家接口,推送增量 和 全量 数据
 */
public class ReportCountryTimer implements Job {

    private Logger logger = LoggerFactory.getLogger(CertificationTimer.class);
    private ReportCountryService reportCountryService = ApplicationContextRegister.getBean(ReportCountryService.class);

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        logger.debug("====定时任务：调用‘上报国家平台接口’ 开始========");
        // 获取增量数据
        UploadIncrementDO newestIncrement = reportCountryService.getNewestIncrement();
        // 获取全量数据
        UploadQuantityDO newestQuantity = reportCountryService.getNewestQuantity();
        // 推送 增量和全量数据到国家信易贷平台
        reportCountryService.pushDate(newestIncrement,newestQuantity,"2");
        logger.debug("====定时任务：调用‘上报国家平台接口’ 结束========");


    }
}
