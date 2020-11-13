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

import com.winchampion.credit.business.domain.AdvertDO;
import com.winchampion.credit.business.service.AdvertService;
import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;
import com.winchampion.credit.common.util.StringUtils;
import com.winchampion.credit.web.controller.user.BaseController;

/**
 * 广告图表
 * 
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-26 11:20:23
 */
 
@Controller
@RequestMapping("/business/advert")
public class AdvertController extends BaseController{
	@Autowired
	private AdvertService advertService;
	
	@GetMapping()
	@RequiresPermissions("business:advert:advert")
	String Advert(){
	    return "business/advert/advert";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:advert:advert")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AdvertDO> advertList = advertService.list(query);
		int total = advertService.count(query);
		PageUtils pageUtils = new PageUtils(advertList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("business:advert:add")
	String add(){
	    return "business/advert/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:advert:edit")
	String edit(@PathVariable("id") String id,Model model){
		AdvertDO advert = advertService.get(id);
		model.addAttribute("advert", advert);
	    return "business/advert/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:advert:add")
	public R save( AdvertDO advert){
		//设置
		//验证
		if(StringUtils.isBlank(advert.getAdvertName())) {
			return R.error(1, "请输入广告图名称");
		}
		if(advert.getSort() == null ) {
			return R.error(1, "请输入排序优先级");
		}
		if(StringUtils.isBlank(advert.getAdvertFile())) {
			return R.error(1, "请上传广告图片");
		}
		if(advertService.save(advert)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:advert:edit")
	public R update( AdvertDO advert){
		advertService.update(advert);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:advert:remove")
	public R remove( String id){
		//逻辑删除
		AdvertDO advert  = new AdvertDO();
		advert.setDelFlag(Constant.DEL_FLAG_DELETE);
		advert.setId(id);
		if(advertService.update(advert)>0){
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
		AdvertDO advert  = new AdvertDO();
		//设置发布还是取消发布, 传入未0 的话 则把状态改成发布，其他都改为未发布
		if("0".equals(isRelease)) {
			advert.setIsRelease("1");
		}else {
			advert.setIsRelease("0");
		}
		advert.setId(id);
		advertService.update(advert);
		return R.ok();
	}
	
}
