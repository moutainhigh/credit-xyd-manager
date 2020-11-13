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

import com.winchampion.credit.business.domain.HorseDO;
import com.winchampion.credit.business.service.HorseService;
import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;
import com.winchampion.credit.common.util.StringUtils;
import com.winchampion.credit.web.controller.user.BaseController;

/**
 * 跑马灯表
 * 
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-27 15:05:36
 */
 
@Controller
@RequestMapping("/business/horse")
public class HorseController extends BaseController{
	@Autowired
	private HorseService horseService;
	
	@GetMapping()
	@RequiresPermissions("business:horse:horse")
	String Horse(){
	    return "business/horse/horse";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:horse:horse")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<HorseDO> horseList = horseService.list(query);
		int total = horseService.count(query);
		PageUtils pageUtils = new PageUtils(horseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("business:horse:add")
	String add(){
	    return "business/horse/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:horse:edit")
	String edit(@PathVariable("id") String id,Model model){
		HorseDO horse = horseService.get(id);
		model.addAttribute("horse", horse);
	    return "business/horse/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:horse:add")
	public R save( HorseDO horse){
		if(horseService.save(horse)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:horse:edit")
	public R update( HorseDO horse){
		horseService.update(horse);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:horse:remove")
	public R remove( String id){
		//逻辑删除
		HorseDO horseDO  = new HorseDO();
		horseDO.setDelFlag(Constant.DEL_FLAG_DELETE);
		horseDO.setId(id);
		if(horseService.update(horseDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 发布/取消发布
	 */
	@PostMapping( "/release")
	@ResponseBody
	@RequiresPermissions("business:advert:edit")
	public R release(String id, String isRelease){
		if(StringUtils.isBlank(id)) {
			return R.error();
		}
		HorseDO horseDO  = new HorseDO();
		//设置发布还是取消发布, 传入未0 的话 则把状态改成发布，其他都改为未发布
		if("0".equals(isRelease)) {
			horseDO.setIsRelease("1");
		}else {
			horseDO.setIsRelease("0");
		}
		horseDO.setId(id);
		horseService.update(horseDO);
		return R.ok();
	}
	
}
