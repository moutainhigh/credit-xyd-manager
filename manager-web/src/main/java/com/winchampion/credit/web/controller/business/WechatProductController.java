package com.winchampion.credit.web.controller.business;

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

import com.winchampion.credit.business.domain.WechatProductDO;
import com.winchampion.credit.business.service.HomeProductEditService;
import com.winchampion.credit.business.service.WechatProductService;
import com.winchampion.credit.business.vo.HouseProductEditVo;
import com.winchampion.credit.business.vo.WechatProductVo;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;

/**
 * 小程序产品
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-08-07 16:19:20
 */
 
@Controller
@RequestMapping("/business/wechatProduct")
public class WechatProductController {
	@Autowired
	private WechatProductService wechatProductService;
	
	@Autowired
	private HomeProductEditService homeProductEditService;
	
	@GetMapping()
	@RequiresPermissions("business:wechatProduct:wechatProduct")
	String WechatProduct(){
	    return "business/wechatProduct/wechatProduct";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:wechatProduct:wechatProduct")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WechatProductVo> wechatProductList = wechatProductService.list(query);
		int total = wechatProductService.count(query);
		PageUtils pageUtils = new PageUtils(wechatProductList, total);
		return pageUtils;
	}
	

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:wechatProduct:edit")
	String edit(@PathVariable("id") String id,Model model){
		WechatProductVo wechatProduct = wechatProductService.get(id);
		model.addAttribute("wechatProduct", wechatProduct);
	    return "business/wechatProduct/edit";
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:wechatProduct:edit")
	public R update( WechatProductDO wechatProduct){
		wechatProductService.update(wechatProduct);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:wechatProduct:remove")
	public R remove( String id){
		if(wechatProductService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("business:wechatProduct:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		wechatProductService.batchRemove(ids);
		return R.ok();
	}
	
	/**
	 * 移入产品页面
	 */
	@GetMapping("/moveIn")
	@RequiresPermissions("business:wechatProduct:add")
	public String moveIn(Model model){
		return  "business/wechatProduct/wechatProductMoveIn";
	}
	
	
	/**
	 * 获取可移入的已发布金融产品集合
	 */
	@ResponseBody
	@GetMapping("/releaseProductList")
	@RequiresPermissions("business:wechatProduct:add")
	public PageUtils releaseProductList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<HouseProductEditVo> houseProductEditVoList = homeProductEditService.releaseProductList(query);
		int total = homeProductEditService.releaseProductCount(query);
		PageUtils pageUtils = new PageUtils(houseProductEditVoList, total);
		return pageUtils;
	}

	/**
	 * 移入产品批量操作
	 */
	@ResponseBody
	@PostMapping("/batchMoveIn")
	@RequiresPermissions("business:wechatProduct:add")
	public R batchMoveIn(@RequestParam("productIds[]") String[] productIds){
		//先判断是否有存在得存在的话 返回存在的产品
		List<String> list = wechatProductService.validate(productIds);
		if(list.size() > 0) {
			return R.error("产品:" + list.toString() + "  已经移入小程序产品，请重新选择！");
		}
		int r = wechatProductService.batchMoveIn(productIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

}
