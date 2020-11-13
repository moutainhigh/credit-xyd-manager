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

import com.winchampion.credit.business.domain.WechatAdvertDO;
import com.winchampion.credit.business.service.WechatAdvertService;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;
import com.winchampion.credit.common.util.StringUtils;

/**
 * 小程序广告图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-08-07 09:30:21
 */
 
@Controller
@RequestMapping("/business/wechatAdvert")
public class WechatAdvertController {
	@Autowired
	private WechatAdvertService wechatAdvertService;
	
	@GetMapping()
	@RequiresPermissions("business:wechatAdvert:wechatAdvert")
	String WechatAdvert(){
	    return "business/wechatAdvert/wechatAdvert";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:wechatAdvert:wechatAdvert")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WechatAdvertDO> wechatAdvertList = wechatAdvertService.list(query);
		int total = wechatAdvertService.count(query);
		PageUtils pageUtils = new PageUtils(wechatAdvertList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("business:wechatAdvert:add")
	String add(){
	    return "business/wechatAdvert/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:wechatAdvert:edit")
	String edit(@PathVariable("id") String id,Model model){
		WechatAdvertDO wechatAdvert = wechatAdvertService.get(id);
		model.addAttribute("wechatAdvert", wechatAdvert);
	    return "business/wechatAdvert/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:wechatAdvert:add")
	public R save( WechatAdvertDO wechatAdvert){
		//验证
		if(StringUtils.isBlank(wechatAdvert.getAdvertName())) {
			return R.error(1, "请输入广告图名称");
		}
		if(wechatAdvert.getSort() == null ) {
			return R.error(1, "请输入排序优先级");
		}
		if(StringUtils.isBlank(wechatAdvert.getAdvertFile())) {
			return R.error(1, "请上传广告图片");
		}
		if(wechatAdvertService.save(wechatAdvert)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:wechatAdvert:edit")
	public R update( WechatAdvertDO wechatAdvert){
		wechatAdvertService.update(wechatAdvert);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:wechatAdvert:remove")
	public R remove( String id){
		if(wechatAdvertService.remove(id)>0){
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
		WechatAdvertDO advert  = new WechatAdvertDO();
		//设置发布还是取消发布, 传入未0 的话 则把状态改成发布，其他都改为未发布
		if("0".equals(isRelease)) {
			advert.setIsRelease("1");
		}else {
			advert.setIsRelease("0");
		}
		advert.setId(id);
		wechatAdvertService.update(advert);
		return R.ok();
	}
	
}
