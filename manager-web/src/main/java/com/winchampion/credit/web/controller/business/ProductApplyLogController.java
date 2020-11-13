package com.winchampion.credit.web.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winchampion.credit.business.domain.FrontApplyFlowLogDO;
import com.winchampion.credit.business.domain.ProductApplyLogDO;
import com.winchampion.credit.business.domain.SinosureFinancingDO;
import com.winchampion.credit.business.service.FrontApplyFlowLogService;
import com.winchampion.credit.business.service.ProductApplyLogService;
import com.winchampion.credit.business.service.SinosureFinancingService;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;

/**
 * 产品申请记录表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-07 13:44:04
 */
 
@Controller
@RequestMapping("/business/productApplyLog")
public class ProductApplyLogController {
	@Autowired
	private ProductApplyLogService productApplyLogService;
	@Autowired
	private FrontApplyFlowLogService frontApplyFlowLogService;
	@Autowired
	private SinosureFinancingService sinosureFinancingService;
	//前缀
	private String prefix = "business/productApplyLog" ;
	
	@GetMapping()
	@RequiresPermissions("business:productApplyLog:productApplyLog")
	String ProductApplyLog(){
	    return prefix+"/productApplyLogList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:productApplyLog:productApplyLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductApplyLogDO> productApplyLogList = productApplyLogService.list(query);
		int total = productApplyLogService.count(query);
		PageUtils pageUtils = new PageUtils(productApplyLogList, total);
		return pageUtils;
	}
	
	/**
	 * 详情
	 * @param id 记录id
	 * @param model
	 * @return
	 */
	@GetMapping("/details/{id}")
	@RequiresPermissions("business:productApplyLog:productApplyLog")
	public String details(@PathVariable("id") String id,Model model){
		ProductApplyLogDO productApplyLogDO = productApplyLogService.get(id);
		model.addAttribute("productApplyLog", productApplyLogDO);
		Map<String, Object> params = new HashMap<>();
		params.put("applyOdd", productApplyLogDO.getApplyOdd());
		List<FrontApplyFlowLogDO> flowLoglist = frontApplyFlowLogService.list(params);
		model.addAttribute("flowLoglist", flowLoglist);
		return prefix + "/productApplyLogDetails";
	}
	
	@GetMapping("/add")
	@RequiresPermissions("web:productApplyLog:add")
	String add(){
	    return "web/productApplyLog/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("web:productApplyLog:edit")
	String edit(@PathVariable("id") String id,Model model){
		ProductApplyLogDO productApplyLog = productApplyLogService.get(id);
		model.addAttribute("productApplyLog", productApplyLog);
	    return "web/productApplyLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:productApplyLog:add")
	public R save( ProductApplyLogDO productApplyLog){
		if(productApplyLogService.save(productApplyLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:productApplyLog:edit")
	public R update( ProductApplyLogDO productApplyLog){
		productApplyLogService.update(productApplyLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("web:productApplyLog:remove")
	public R remove( String id){
		if(productApplyLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:productApplyLog:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		productApplyLogService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 中信保融资产品申请管理页面
	 * @param params
	 * @return
	 */
	@GetMapping("/sinosureFinancing")
	@RequiresPermissions("business:productApplyLog:sinosureFinancing")
	String sinosureFinancing(){
	    return prefix+"/sinosureFinancingList";
	}
	/**
	 * 中信保融资产品申请管理列表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/sinosureFinancingList")
	@RequiresPermissions("business:productApplyLog:sinosureFinancing")
	public PageUtils sinosureFinancingList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SinosureFinancingDO> sinosureFinancingList = sinosureFinancingService.list(query);
		int total = sinosureFinancingService.count(query);
		PageUtils pageUtils = new PageUtils(sinosureFinancingList, total);
		return pageUtils;
	}
	
	/***
	 * 中信保融资产品申请详情
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/sinosureFinancingDetails/{id}")
	@RequiresPermissions("business:productApplyLog:productApplyLog")
	public String sinosureFinancingDetails(@PathVariable("id") String id,Model model){
		model.addAttribute("sinosureFinancing", sinosureFinancingService.get(id));
		return prefix + "/sinosureFinancingDetails";
	}
	
}
