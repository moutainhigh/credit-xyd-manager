package com.winchampion.credit.web.controller.business;

import com.winchampion.credit.business.domain.*;
import com.winchampion.credit.business.service.*;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.StringUtils;
import com.winchampion.credit.web.controller.user.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 前台用户管理 Controller
 * @author liwei
 * @date 2020-02-25
 */
 
@Controller
@RequestMapping("/business/userManage")
public class UserManageController extends BaseController{
	//前缀
	private String prefix = "business/userManage";

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerCompanyService customerCompanyService;
	@Autowired
	private CompanyFinanceNeedsService companyFinanceNeedsService;
	@Autowired
	private CreditReportService creditReportService;
	@Autowired
	private CustomerCompanyAuthHisService customerCompanyAuthHisService;

	@GetMapping("/registerAccountPage")
	@RequiresPermissions("business:userManage:accountView")
	String registerAccountPage(){
	    return "business/userManage/registerAccountList";
	}
	
	@ResponseBody
	@GetMapping("/registerAccountList")
	@RequiresPermissions("business:userManage:accountView")
	public PageUtils registerAccountList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CustomerDO> customerList = customerService.list(query);
		int total = customerService.count(query);
		PageUtils pageUtils = new PageUtils(customerList, total);
		return pageUtils;
	}

	/**
	 * 用户详情页面
	 * @param customerId
	 */
	@GetMapping("/registerAccountDetail/{customerId}")
	@RequiresPermissions("business:userManage:accountView")
	public String registerAccountDetail(@PathVariable("customerId") String customerId, Model model){
		CustomerDO customerDO = customerService.get(customerId);
		model.addAttribute("customerDO", customerDO);
		return prefix + "/registerAccountDetail";
	}

	/**
	 * 用户详情——获取认证企业集合
	 */
	@ResponseBody
	@GetMapping("/customerCompanyList")
	@RequiresPermissions("business:userManage:accountView")
	public PageUtils customerCompanyList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		if(StringUtils.isNotBlank(String.valueOf(query.get("customerId")))){
			List<CustomerCompanyDO> customerCompanyDOList = customerCompanyService.list(query);
			int total = customerCompanyService.count(query);
			PageUtils pageUtils = new PageUtils(customerCompanyDOList, total);
			return pageUtils;
		}
		return new PageUtils(new ArrayList<>(), 0);
	}

	/**
	 * 用户详情——获取企业融资需求集合
	 */
	@ResponseBody
	@GetMapping("/companyFinanceNeedsList")
	@RequiresPermissions("business:userManage:accountView")
	public PageUtils companyFinanceNeedsList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		if(StringUtils.isNotBlank(String.valueOf(query.get("customerId")))){
			List<CompanyFinanceNeedsDO> companyFinanceNeedsDOList = companyFinanceNeedsService.listByCustomerId(query);
			int total = companyFinanceNeedsService.countByCustomerId(query);
			PageUtils pageUtils = new PageUtils(companyFinanceNeedsDOList, total);
			return pageUtils;
		}
		return new PageUtils(new ArrayList<>(), 0);
	}

	/**
	 * 用户详情——获取认证企业集合
	 */
	@ResponseBody
	@GetMapping("/creditReportList")
	@RequiresPermissions("business:userManage:accountView")
	public PageUtils creditReportList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<CreditReportDo> creditReportDoList = creditReportService.list(query);
		int total = creditReportService.count(query);
		PageUtils pageUtils = new PageUtils(creditReportDoList, total);
		return pageUtils;
	}

	/**
	 * 企业管理页面跳转
	 */
	@GetMapping("/companyPage")
	@RequiresPermissions("business:userManage:companyView")
	String companyPage(){
		return "business/userManage/companyList";
	}

	/**
	 * 认证企业集合
	 */
	@ResponseBody
	@GetMapping("/companyList")
	@RequiresPermissions("business:userManage:companyView")
	public PageUtils companyList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<CustomerCompanyDO> customerCompanyList = customerCompanyService.list(query);
		int total = customerService.count(query);
		PageUtils pageUtils = new PageUtils(customerCompanyList, total);
		return pageUtils;
	}

	/**
	 * 企业详情页面
	 */
	@GetMapping("/companyDetail/{companyId}")
	@RequiresPermissions("business:userManage:companyView")
	public String companyDetail(@PathVariable("companyId") String companyId, Model model){
		CustomerCompanyDO customerCompanyDO = customerCompanyService.get(companyId);
		model.addAttribute("customerCompanyDO", customerCompanyDO);
		return prefix + "/companyDetail";
	}

	/**
	 * 企业详情——企业认证状态变更记录
	 */
	@ResponseBody
	@GetMapping("/companyAuthList")
	@RequiresPermissions("business:userManage:companyView")
	public PageUtils companyAuthList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<CustomerCompanyAuthHisDO> customerCompanyAuthHisDOS = customerCompanyAuthHisService.list(query);
		int total = customerCompanyAuthHisService.count(query);
		PageUtils pageUtils = new PageUtils(customerCompanyAuthHisDOS, total);
		return pageUtils;
	}
}
