package com.winchampion.credit.web.controller.business;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.reflect.TypeToken;
import com.winchampion.credit.business.domain.FaqManageDO;
import com.winchampion.credit.business.domain.ProductDO;
import com.winchampion.credit.business.domain.ProductInfoDO;
import com.winchampion.credit.business.service.FaqManageService;
import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.common.util.GsonUtil;
import com.winchampion.credit.common.util.IdGen;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;
import com.winchampion.credit.user.utils.UserUtils;

/**
 * 常见问题管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-03 16:58:56
 */
 
@Controller
@RequestMapping("/business/faqManage")
public class FaqManageController {
	@Autowired
	private FaqManageService faqManageService;
	//前缀
	private String prefix = "business/faqManage" ;
	
	@GetMapping()
	@RequiresPermissions("business:faq:faqManage")
	String FaqManage(){
	    return prefix+"/faqManageList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:faq:faqManage")
	public PageUtils list(@RequestParam Map<String, Object> params){
		params.put("delFlag", Constant.DEL_FLAG_NORMAL);
		//查询列表数据
        Query query = new Query(params);
		List<FaqManageDO> faqManageList = faqManageService.list(query);
		int total = faqManageService.count(query);
		PageUtils pageUtils = new PageUtils(faqManageList, total);
		return pageUtils;
	}
	
	/**
	 * 详情+修改
	 * @param id 问题id
	 * @param type 页面控制 1：详情页；2：编辑页
	 * @param model
	 * @return
	 */
	@GetMapping("/details/{id}/{type}")
	@RequiresPermissions("business:faq:faqManage")
	public String details(@PathVariable("id") String id,@PathVariable("type")String type,Model model){
		FaqManageDO faqManageDO = faqManageService.get(id);
		model.addAttribute("faqManageDO", faqManageDO);
		if("1".equals(type)) {//详情页
			return prefix + "/faqManageDetails";
		}else {//编辑页
			return prefix + "/faqManageEdit";
		}
	}
	
	/**
	 * 新增/修改
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:faq:faqManage")
	public String addProduct(@RequestParam("faqManageStr")String faqManageStr){
		FaqManageDO faqManageDO = GsonUtil.gson.fromJson(faqManageStr, new TypeToken<FaqManageDO>(){}.getType());
		String userId = UserUtils.getUserId();
		String createDate = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		faqManageDO.setUpdateBy(userId);
		faqManageDO.setUpdateDate(createDate);
		//String url = prefix;
		if(StringUtils.isEmpty(faqManageDO.getId())) {//新增
			//获取最新排序的产品编号
			String id = IdGen.uuid();
			faqManageDO.setFaqNo(id);
			faqManageDO.setId(id);
			faqManageDO.setCreateBy(userId);
			faqManageDO.setCreateDate(createDate);
			faqManageDO.setIsRelease(Constant.PRODUCT_NOT_ISSUE);
			faqManageDO.setDelFlag(Constant.DEL_FLAG_NORMAL);
			faqManageService.save(faqManageDO);
		}else {//修改
			faqManageService.update(faqManageDO);
		}
		return faqManageDO.getId();
	}
	
	@GetMapping("/add")
	@RequiresPermissions("business:faq:faqManage")
	String add(){
	    return prefix+"/faqManageAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("web:faqManage:edit")
	String edit(@PathVariable("id") String id,Model model){
		FaqManageDO faqManage = faqManageService.get(id);
		model.addAttribute("faqManage", faqManage);
	    return "web/faqManage/edit";
	}
	
	/**
	 * 保存
	 */
	/*@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:faqManage:add")
	public R save( FaqManageDO faqManage){
		if(faqManageService.save(faqManage)>0){
			return R.ok();
		}
		return R.error();
	}*/
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:faqManage:edit")
	public R update( FaqManageDO faqManage){
		faqManageService.update(faqManage);
		return R.ok();
	}
	
	/**
	 * 发布 
	 * @param id 问题id
	 * @param type 1:发布；2：取消发布
	 * @return
	 */
	@PostMapping( "/issue")
	@ResponseBody
	@RequiresPermissions("business:faq:faqManage")
	public R issue( String id,String type){
		//if(faqManageService.remove(id)>0){
		FaqManageDO faqManage = new FaqManageDO();
		faqManage.setId(id);
		faqManage.setIsRelease("1".equals(type)?Constant.PRODUCT_YES_ISSUE:Constant.PRODUCT_NOT_ISSUE);
		if(faqManageService.update(faqManage)>0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:faq:faqManage")
	public R remove( String id){
		//if(faqManageService.remove(id)>0){
		FaqManageDO faqManage = new FaqManageDO();
		faqManage.setId(id);
		faqManage.setDelFlag(Constant.DEL_FLAG_DELETE);
		if(faqManageService.update(faqManage)>0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:faqManage:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		faqManageService.batchRemove(ids);
		return R.ok();
	}
	
}
