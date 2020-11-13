package com.winchampion.credit.web.quartz;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winchampion.credit.business.domain.ProductDO;
import com.winchampion.credit.business.service.ProductService;
import com.winchampion.credit.business.vo.ProductHeatVo;
import com.winchampion.credit.user.config.ApplicationContextRegister;

//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 2.开启定时任务
public class AttentionTimer  implements Job {
	private Logger logger = LoggerFactory.getLogger(AttentionTimer.class);
	
	private ProductService productService = ApplicationContextRegister.getBean(ProductService.class);
	/**
	 * 更新产品关注度
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		List<ProductHeatVo> productList = productService.getProductHeatList(null);
		if(CollectionUtils.isNotEmpty(productList)) {
			logger.info("开始更新产品关注度----------");
			ProductDO productDO = null;
			for (ProductHeatVo productHeatVo : productList) {
				productDO = new ProductDO();
				productDO.setId(productHeatVo.getId());
				productDO.setHeat(productHeatVo.getHeat());
				productService.updateHeat(productDO);
			}
		}else {
			logger.info("未更新到数据----------");
		}
	}
}
