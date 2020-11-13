package com.winchampion.credit.web.controller.business;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winchampion.credit.business.domain.CompanyFinEntDO;
import com.winchampion.credit.business.service.CompanyFinEntService;
import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;

/**
 * 企业融资需求可发布金融机构
 * 
 * @author zhangxin
 * @email zhangxin@champion-credit.com
 * @date 2020-02-29 10:58:35
 */
 
@Controller
@RequestMapping("/business/companyFinEnt")
public class CompanyFinEntController {
	@Autowired
	private CompanyFinEntService companyFinEntService;
	
	@GetMapping()
	@RequiresPermissions("business:companyFinEnt:companyFinEnt")
	String CompanyFinEnt(){
	    return "business/companyFinEnt/companyFinEnt";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:companyFinEnt:companyFinEnt")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CompanyFinEntDO> companyFinEntList = companyFinEntService.list(query);
		int total = companyFinEntService.count(query);
		PageUtils pageUtils = new PageUtils(companyFinEntList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("business:companyFinEnt:add")
	String add(){
	    return "business/companyFinEnt/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:companyFinEnt:edit")
	String edit(@PathVariable("id") String id,Model model){
		CompanyFinEntDO companyFinEnt = companyFinEntService.get(id);
		model.addAttribute("companyFinEnt", companyFinEnt);
	    return "business/companyFinEnt/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:companyFinEnt:add")
	public R save( CompanyFinEntDO companyFinEnt){
		if(companyFinEntService.save(companyFinEnt)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:companyFinEnt:edit")
	public R update( CompanyFinEntDO companyFinEnt){
		companyFinEntService.update(companyFinEnt);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:companyFinEnt:remove")
	public R remove( String id){
		CompanyFinEntDO companyFinEntDO = new CompanyFinEntDO();
		companyFinEntDO.setDelFlag(Constant.DEL_FLAG_DELETE);
		companyFinEntDO.setId(id);
		if(companyFinEntService.update(companyFinEntDO)>0){
		return R.ok();
		}
		return R.error();
	}
	
}
