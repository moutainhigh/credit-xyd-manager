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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winchampion.credit.business.domain.SmartMatchLogDO;
import com.winchampion.credit.business.service.SmartMatchLogService;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;

/**
 * 智能匹配日志
 * 
 * @author zhangcong
 * @email 15121007361@163.com
 * @date 2020-04-20 10:42:35
 */
 
@Controller
@RequestMapping("/business/smartMatchLog")
public class SmartMatchLogController {
	
	//前缀
	private String prefix = "business/smartMatchLog";
	
	@Autowired
	private SmartMatchLogService smartMatchLogService;
	
	@GetMapping()
	@RequiresPermissions("business:smartMatchLog:smartMatchLog")
	String SmartMatchLog(){
		return prefix + "/smartMatchLogList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:smartMatchLog:smartMatchLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SmartMatchLogDO> smartMatchLogList = smartMatchLogService.list(query);
		int total = smartMatchLogService.count(query);
		PageUtils pageUtils = new PageUtils(smartMatchLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("domain:smartMatchLog:add")
	String add(){
	    return "domain/smartMatchLog/add";
	}

	@GetMapping("/edit/{customerId}")
	@RequiresPermissions("domain:smartMatchLog:edit")
	String edit(@PathVariable("customerId") String customerId,Model model){
		SmartMatchLogDO smartMatchLog = smartMatchLogService.get(customerId);
		model.addAttribute("smartMatchLog", smartMatchLog);
	    return "domain/smartMatchLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("domain:smartMatchLog:add")
	public R save( SmartMatchLogDO smartMatchLog){
		if(smartMatchLogService.save(smartMatchLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("domain:smartMatchLog:edit")
	public R update( SmartMatchLogDO smartMatchLog){
		smartMatchLogService.update(smartMatchLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("domain:smartMatchLog:remove")
	public R remove( String customerId){
		if(smartMatchLogService.remove(customerId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("domain:smartMatchLog:batchRemove")
	public R remove(@RequestParam("ids[]") String[] customerIds){
		smartMatchLogService.batchRemove(customerIds);
		return R.ok();
	}
	
}
