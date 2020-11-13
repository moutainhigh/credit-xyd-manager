package com.winchampion.credit.web.controller.business;

import com.winchampion.credit.business.domain.HomeFinEntDO;
import com.winchampion.credit.business.service.HomeFineEntService;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 首页合作金融/信用机构
 * @author liwei
 */
 
@Controller
@RequestMapping("/business/homeFinEnt")
public class HomeFinEntController {
	//前缀
	private String prefix = "business/homeFinEnt" ;
		
	@Autowired
	private HomeFineEntService homeFineEntService;

	/**
	 * 首页合作金融机构页
	 */
	@GetMapping("/homeFinancePage")
	@RequiresPermissions("business:homeFinEnt:view")
	public String homeFinancePage(Model model){
		model.addAttribute("entType", "1");
		return prefix + "/homeFinEntList";
	}

	/**
	 * 首页合作信用机构页
	 */
	@GetMapping("/homeCreditPage")
	@RequiresPermissions("business:homeFinEnt:view")
	public String homeCreditPage(Model model){
		model.addAttribute("entType", "2");
		return prefix + "/homeFinEntList";
	}

	/**
	 * 首页合作金融/信用机构集合
	 */
	@ResponseBody
	@GetMapping("/homeFinEntList")
	@RequiresPermissions("business:homeFinEnt:view")
	public PageUtils homeFinEntList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<HomeFinEntDO> homeFinEntDOList = homeFineEntService.list(query);
		int total = homeFineEntService.count(query);
		PageUtils pageUtils = new PageUtils(homeFinEntDOList, total);
		return pageUtils;
	}

	/**
	 * 添加首页合作金融/信用机构页面
	 */
	@GetMapping("/addHomeFinEnt/{entType}")
	@RequiresPermissions("business:homeFinEnt:edit")
	public String addHomeFinEnt(@PathVariable("entType") String entType, Model model){
		model.addAttribute("entType", entType);
		return prefix + "/homeFinEntAdd";
	}

	/**
	 * 保存首页合作金融/信用操作
	 */
	@ResponseBody
	@PostMapping("/saveHomeFinEnt")
	@RequiresPermissions("business:homeFinEnt:edit")
	public R saveHomeFinEnt(HomeFinEntDO homeFinEntDO) {
		if (homeFineEntService.save(homeFinEntDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改首页合作金融/信用操作
	 */
	@ResponseBody
	@RequestMapping("/updateHomeFinEnt")
	@RequiresPermissions("business:institutionManage:edit")
	public R updateHomeFinEnt(HomeFinEntDO homeFinEntDO) {
		if (homeFineEntService.update(homeFinEntDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 编辑首页合作金融/信用机构页面
	 */
	@GetMapping("/editHomeFinEnt/{finEntId}")
	@RequiresPermissions("business:homeFinEnt:edit")
	public String editHomeFinEnt(@PathVariable("finEntId") String finEntId, Model model) {
		HomeFinEntDO homeFinEntDO = homeFineEntService.get(finEntId);
		model.addAttribute("homeFinEntDO", homeFinEntDO);
		return prefix + "/homeFinEntEdit";
	}

	/**
	 * 删除首页合作金融/信用机构
	 */
	@PostMapping("/removeHomeFinEnt")
	@RequiresPermissions("business:homeFinEnt:edit")
	@ResponseBody
	public R removeIns(String id) {
		int result = homeFineEntService.remove(id);
		if (result == 1) {
			return R.ok();
		}else {
			return R.error();
		}
	}
}
