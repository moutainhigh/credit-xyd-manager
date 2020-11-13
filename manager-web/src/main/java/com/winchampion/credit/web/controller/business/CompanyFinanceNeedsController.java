package com.winchampion.credit.web.controller.business;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winchampion.credit.business.domain.CompanyFinanceNeedsDO;
import com.winchampion.credit.business.service.CompanyFinanceNeedsService;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;
import com.winchampion.credit.common.util.StringUtils;
import com.winchampion.credit.web.controller.user.BaseController;

/**
 * 企业融资需求
 * Created by WIN-CHAMPION China . 
 * @author: zhangxin  
 * @date:2020年2月25日
 * @time:上午10:04:13
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
@Controller
@RequestMapping("/business/companyFinanceNeeds")
public class CompanyFinanceNeedsController extends BaseController{
	//前缀
	private String prefix = "business/companyFinanceNeeds" ;
		
	
	@Autowired
	private CompanyFinanceNeedsService companyFinanceNeedsService;
	
	@GetMapping()
	@RequiresPermissions("business:companyFinanceNeeds:companyFinanceNeeds")
	public String CompanyFinanceNeeds(){
	    return prefix + "/companyFinanceNeeds";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:companyFinanceNeeds:companyFinanceNeeds")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		//设置默认查询
		if(params == null || params.get("acceptStatus") == null || StringUtils.isBlank(params.get("acceptStatus").toString()) ) {
			params.put("acceptStatus","1");
		}
        Query query = new Query(params);
		List<CompanyFinanceNeedsDO> companyFinanceNeedsList = companyFinanceNeedsService.list(query);
		int total = companyFinanceNeedsService.count(query);
		PageUtils pageUtils = new PageUtils(companyFinanceNeedsList, total);
		return pageUtils;
	}
	
	/****
	 * 编辑
	 * @author: zhangxin  
	 * @date:2020年2月25日  下午3:27:43
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:companyFinanceNeeds:edit")
	public String edit(@PathVariable("id") String id,Model model){
		CompanyFinanceNeedsDO companyFinanceNeeds = companyFinanceNeedsService.get(id);
		model.addAttribute("companyFinanceNeeds", companyFinanceNeeds);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:companyFinanceNeeds:edit")
	public R update( CompanyFinanceNeedsDO companyFinanceNeeds){
		
		companyFinanceNeedsService.update(companyFinanceNeeds);
		return R.ok();
	}
	

	/****
	 * 查看
	 * @author: zhangxin  
	 * @date:2020年2月25日  下午3:27:43
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/details/{id}")
	@RequiresPermissions("business:companyFinanceNeeds:companyFinanceNeeds")
	public String detail(@PathVariable("id") String id,Model model){
		CompanyFinanceNeedsDO companyFinanceNeeds = companyFinanceNeedsService.get(id);
		model.addAttribute("companyFinanceNeeds", companyFinanceNeeds);
	    return prefix + "/details";
	}
}
