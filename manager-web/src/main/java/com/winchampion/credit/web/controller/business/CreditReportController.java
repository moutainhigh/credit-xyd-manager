package com.winchampion.credit.web.controller.business;

import com.winchampion.credit.business.domain.CreditReportDo;
import com.winchampion.credit.business.service.CreditReportService;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.web.controller.user.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 个人/企业信用报告 Controller
 * @author liwei
 * @date 2020-02-27
 */
 
@Controller
@RequestMapping("/business/creditReport")
public class CreditReportController extends BaseController{

	@Autowired
	private CreditReportService creditReportService;

	@ResponseBody
	@GetMapping("/reportList")
	@RequiresPermissions("business:creditReport:list")
	public PageUtils reportList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<CreditReportDo> customerList = creditReportService.list(query);
		int total = creditReportService.count(query);
		PageUtils pageUtils = new PageUtils(customerList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/personReport")
	@RequiresPermissions("business:creditReport:list")
	public PageUtils personReport(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CreditReportDo> customerList = creditReportService.list(query);
		int total = creditReportService.count(query);
		PageUtils pageUtils = new PageUtils(customerList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/enterpriseReport")
	@RequiresPermissions("business:creditReport:list")
	public PageUtils enterpriseReport(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<CreditReportDo> customerList = creditReportService.list(query);
		int total = creditReportService.count(query);
		PageUtils pageUtils = new PageUtils(customerList, total);
		return pageUtils;
	}
}
