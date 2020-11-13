package com.winchampion.credit.web.controller.business;

import com.winchampion.credit.business.service.*;
import com.winchampion.credit.business.vo.HouseProductEditVo;
import com.winchampion.credit.common.util.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 首页热门、企业、个人产品
 * @author liwei
 */
 
@Controller
@RequestMapping("/business/homeProductEdit")
public class HomeProductEditController {
	//前缀
	private String prefix = "business/homeProductEdit" ;
		
	@Autowired
	private HomeProductEditService homeProductEditService;

	/**
	 * 热门贷款产品列表页
	 */
	@GetMapping("/hotProductPage")
	@RequiresPermissions("business:homeProductEdit:view")
	public String hotProductPage(Model model){
		model.addAttribute("productType", "0");
		return prefix + "/homeProductEditList";
	}
	/**
	 * 企业贷款产品列表页
	 */
	@GetMapping("/comProductPage")
	@RequiresPermissions("business:homeProductEdit:view")
	public String comProductPage(Model model){
		model.addAttribute("productType", "1");
		return prefix + "/homeProductEditList";
	}
	/**
	 * 个人贷款产品列表页
	 */
	@GetMapping("/perProductPage")
	@RequiresPermissions("business:homeProductEdit:view")
	public String perProductPage(Model model){
		model.addAttribute("productType", "2");
		return prefix + "/homeProductEditList";
	}

	/**
	 * 获取当前类型产品集合
	 */
	@ResponseBody
	@GetMapping("/productList")
	@RequiresPermissions("business:homeProductEdit:view")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        if(StringUtils.isNotBlank(String.valueOf(query.get("productType")))){
			List<HouseProductEditVo> houseProductEditVoList = homeProductEditService.list(query);
			int total = homeProductEditService.count(query);
			PageUtils pageUtils = new PageUtils(houseProductEditVoList, total);
			return pageUtils;
		}
		return new PageUtils(new ArrayList<>(), 0);
	}

	/**
	 * 移入产品页面
	 */
	@GetMapping("/moveIn/{productType}")
	@RequiresPermissions("business:homeProductEdit:edit")
	public String moveIn(@PathVariable("productType")String productType, Model model){
		model.addAttribute("productType", productType);
		return prefix + "/homeProductMoveIn";
	}

	/**
	 * 获取可移入的已发布金融产品集合
	 */
	@ResponseBody
	@GetMapping("/releaseProductList")
	@RequiresPermissions("business:homeProductEdit:view")
	public PageUtils releaseProductList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		if(StringUtils.isNotBlank(String.valueOf(query.get("productType")))){
			List<HouseProductEditVo> houseProductEditVoList = homeProductEditService.releaseProductList(query);
			int total = homeProductEditService.releaseProductCount(query);
			PageUtils pageUtils = new PageUtils(houseProductEditVoList, total);
			return pageUtils;
		}
		return new PageUtils(new ArrayList<>(), 0);
	}

	/**
	 * 移入产品批量操作
	 */
	@ResponseBody
	@PostMapping("/batchMoveIn")
	@RequiresPermissions("business:homeProductEdit:edit")
	public R batchMoveIn(@RequestParam("productIds[]") String[] productIds, @RequestParam("productType")String productType){
		int r = homeProductEditService.batchMoveIn(productIds, productType);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 移除产品操作
	 */
	@ResponseBody
	@PostMapping("/moveOut")
	@RequiresPermissions("business:homeProductEdit:edit")
	public R moveOut(String id, String productType){
		if (homeProductEditService.moveOut(id, productType) > 0) {
			return R.ok();
		}
		return R.error();
	}
}