package com.winchampion.credit.web.controller.business;

import com.winchampion.credit.business.domain.InsDO;
import com.winchampion.credit.business.service.InsService;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;
import com.winchampion.credit.web.controller.user.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 合作机构管理 Controller
 * @author liwei
 * @date 2020-02-26
 */
 
@Controller
@RequestMapping("/business/institutionManage")
public class InstitutionManageController extends BaseController{
	//前缀
	private String prefix = "business/institutionManage";

	@Autowired
	private InsService insService;

	/**
	 * 金融机构跳转页
	 */
	@GetMapping("/financeInstitutionPage")
	@RequiresPermissions("business:institutionManage:view")
	String financeInstitutionPage(Model model){
		model.addAttribute("insType", "1");
	    return prefix + "/institutionList";
	}

	/**
	 * 信用服务机构跳转页
	 */
	@GetMapping("/creditInstitutionPage")
	@RequiresPermissions("business:institutionManage:view")
	String creditInstitutionPage(Model model){
		model.addAttribute("insType", "2");
		return prefix + "/institutionList";
	}

	/**
	 * 获取机构集合
	 * @param params
	 */
	@ResponseBody
	@GetMapping("/institutionList")
	@RequiresPermissions("business:institutionManage:view")
	public PageUtils registerAccountList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<InsDO> customerList = insService.list(query);
		int total = insService.count(query);
		PageUtils pageUtils = new PageUtils(customerList, total);
		return pageUtils;
	}

	/**
	 * 保存机构信息操作
	 */
	@ResponseBody
	@PostMapping("/saveIns")
	@RequiresPermissions("business:institutionManage:edit")
	public R saveIns(InsDO insDO) {
		if (insService.save(insDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改机构信息操作
	 */
	@ResponseBody
	@RequestMapping("/updateIns")
	@RequiresPermissions("business:institutionManage:edit")
	public R updateIns(InsDO insDO) {
		if (insService.update(insDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 添加机构信息页面
	 */
	@GetMapping("/addIns/{insType}")
	@RequiresPermissions("business:institutionManage:add")
	String addIns(@PathVariable("insType") String insType, Model model) {
		model.addAttribute("insType", insType);
		return prefix + "/institutionAdd";
	}

	/**
	 * 编辑机构信息页面
	 */
	@GetMapping("/editIns/{id}")
	@RequiresPermissions("business:institutionManage:edit")
	String editIns(@PathVariable("id") String id, Model model) {
		InsDO insDO = insService.get(id);
		model.addAttribute("insDO", insDO);
		return prefix + "/institutionEdit";
	}

	/**
	 * 显示机构信息详情
	 */
	@GetMapping("/infoIns/{id}")
	@RequiresPermissions("business:institutionManage:view")
	String infoIns(@PathVariable("id") String id, Model model) {
		InsDO insDO = insService.get(id);
		model.addAttribute("insDO", insDO);
		return prefix + "/institutionInfo";
	}

	/**
	 * 删除
	 */
	@PostMapping("/removeIns")
	@RequiresPermissions("business:institutionManage:remove")
	@ResponseBody
	public R removeIns(String id) {
		int result = insService.remove(id);
		if (result == 1) {
			return R.ok();
		}else {
			return R.error();
//			return R.error(1, "不存在该机构");
		}
	}
}
