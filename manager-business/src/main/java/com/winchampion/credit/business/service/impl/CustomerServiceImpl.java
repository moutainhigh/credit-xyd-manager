package com.winchampion.credit.business.service.impl;

import com.winchampion.credit.business.dao.CustomerDao;
import com.winchampion.credit.business.domain.CompanyFinanceNeedsDO;
import com.winchampion.credit.business.domain.CreditReportDo;
import com.winchampion.credit.business.domain.CustomerCompanyDO;
import com.winchampion.credit.business.domain.CustomerDO;
import com.winchampion.credit.business.service.CompanyFinanceNeedsService;
import com.winchampion.credit.business.service.CreditReportService;
import com.winchampion.credit.business.service.CustomerCompanyService;
import com.winchampion.credit.business.service.CustomerService;
import com.winchampion.credit.business.vo.CustomerReplenishVo;
import com.winchampion.credit.business.vo.RegisterAccountDetailVo;
import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.common.util.IdGen;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerCompanyService customerCompanyService;
	@Autowired
	private CompanyFinanceNeedsService companyFinanceNeedsService;
	@Autowired
	private CreditReportService creditReportService;
	
	@Override
	public CustomerDO get(String id){
		return customerDao.get(id);
	}

	@Override
	public CustomerDO getByCustomerNo(String customerNo){
		return customerDao.getByCustomerNo(customerNo);
	}
	
	@Override
	public List<CustomerDO> list(Map<String, Object> map){
		return customerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return customerDao.count(map);
	}

	@Override
	public RegisterAccountDetailVo getRegisterDetail(String customerNo){

		RegisterAccountDetailVo registerAccountDetailVo = new RegisterAccountDetailVo();
		CustomerDO customerDO = this.getByCustomerNo(customerNo);

		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("customerId", customerDO.getId());
		List<CustomerCompanyDO> customerCompanyDOList = customerCompanyService.list(queryMap);

		queryMap.put("customerPhone", customerDO.getCustomerPhone());
		List<CompanyFinanceNeedsDO> companyFinanceNeedsDOList = companyFinanceNeedsService.list(queryMap);

//		queryMap.put("searchId", "");
		queryMap.put("reportType", "1");
//		queryMap.put("customerId", customerDO.getId());
		List<CreditReportDo> creditReportDoList = creditReportService.list(queryMap);

		registerAccountDetailVo.setCustomerDO(customerDO);
		registerAccountDetailVo.setCustomerCompanyDOList(customerCompanyDOList);
		registerAccountDetailVo.setCompanyFinanceNeedsDOList(companyFinanceNeedsDOList);
		registerAccountDetailVo.setCreditReportDoList(creditReportDoList);
		return registerAccountDetailVo;
	}

	@Override
	public String customerReplenish(CustomerReplenishVo customerReplenishVo) {
		//根据手机号查询用户
		Map<String, Object> map = new HashedMap();
		map.put("customerPhone", customerReplenishVo.getPhoneNo());
		List<CustomerDO> list = customerDao.list(map);
		logger.debug("个人用户注册或完善接口入参："+customerReplenishVo.toString());
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		if(CollectionUtils.isNotEmpty(list)){
			logger.debug("已存在用户，进行信息核对！");
			CustomerDO customerDO = list.get(0);
			if (!"1".equals(customerDO.getIsRealName())) {//已注册未实名
				//更新“姓名”，“身份证号”，“实名认证状态=已认证”，“个人实名认证时间=当前系统时间”，“实名认证描述=取信接口实名”
				customerDO.setCustomerName(customerReplenishVo.getPname());
				customerDO.setCustomerIdNumber(customerReplenishVo.getCertiCode());
				customerDO.setIsRealName("1");
				customerDO.setRealNameDate(date);
				customerDO.setCertificationDesc(Constant.COUSTOMER_CERTIFICATIONDESC_INTERFACE);
				customerDao.update(customerDO);
				logger.debug("检测到已注册未实名用户，已进行用户数据更新！");
				return Constant.IS_DIRTY;
			}else {//已注册已实名
				logger.debug("检测到用户已注册并且已实名，无需重复实名注册！");
				return Constant.NOT_UPDATED;
			}
		}else {//未注册
			String id = IdGen.uuid();
			CustomerDO customerDO = new CustomerDO();
			customerDO.setId(id);
			customerDO.setCustomerNo(id);
			customerDO.setCustomerName(customerReplenishVo.getPname());
			customerDO.setCustomerIdNumber(customerReplenishVo.getCertiCode());
			customerDO.setCustomerPhone(customerReplenishVo.getPhoneNo());
			customerDO.setIsRealName("1");
			customerDO.setCreateDate(date);
			customerDO.setRealNameDate(date);
			customerDO.setSourceDesc(Constant.COUSTOMER_SOURCEDESC_INTERFACE);
			customerDO.setCertificationDesc(Constant.COUSTOMER_CERTIFICATIONDESC_INTERFACE);
			customerDao.save(customerDO);
			logger.debug("未检测到用户，直接进行实名注册！");
			return Constant.REGISTERED;
		}
	}
}
