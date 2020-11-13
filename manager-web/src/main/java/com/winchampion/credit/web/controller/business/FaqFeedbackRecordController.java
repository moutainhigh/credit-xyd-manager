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

import com.winchampion.credit.business.domain.FaqFeedbackRecordDO;
import com.winchampion.credit.business.domain.FaqManageDO;
import com.winchampion.credit.business.service.FaqFeedbackRecordService;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;

/**
 * 问题反馈记录表
 * 
 * @author zhangcong
 * @email zhangcong@163.com
 * @date 2020-04-07 09:30:07
 */
 
@Controller
@RequestMapping("/business/faq/record")
public class FaqFeedbackRecordController {
	@Autowired
	private FaqFeedbackRecordService faqFeedbackRecordService;
	//前缀
	private String prefix = "business/faqFeedbackRecord" ;
	
	@GetMapping()
	@RequiresPermissions("business:faq:faqFeedbackRecord")
	String FaqFeedbackRecord(){
	    return prefix+"/faqFeedbackRecordList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:faq:faqFeedbackRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		
        Query query = new Query(params);
		List<FaqFeedbackRecordDO> faqFeedbackRecordList = faqFeedbackRecordService.list(query);
		int total = faqFeedbackRecordService.count(query);
		PageUtils pageUtils = new PageUtils(faqFeedbackRecordList, total);
		return pageUtils;
	}
	/**
	 * 详情
	 * @param id 问题id
	 * @param model
	 * @return
	 */
	@GetMapping("/details/{id}")
	@RequiresPermissions("business:faq:faqFeedbackRecord")
	public String details(@PathVariable("id") String id,Model model){
		FaqFeedbackRecordDO faqFeedbackRecordDO = faqFeedbackRecordService.get(id);
		model.addAttribute("faqFeedbackRecord", faqFeedbackRecordDO);
		return prefix + "/faqFeedbackRecordDetails";
	}
	
	@GetMapping("/add")
	@RequiresPermissions("web:faqFeedbackRecord:add")
	String add(){
	    return "web/faqFeedbackRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("web:faqFeedbackRecord:edit")
	String edit(@PathVariable("id") String id,Model model){
		FaqFeedbackRecordDO faqFeedbackRecord = faqFeedbackRecordService.get(id);
		model.addAttribute("faqFeedbackRecord", faqFeedbackRecord);
	    return "web/faqFeedbackRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:faqFeedbackRecord:add")
	public R save( FaqFeedbackRecordDO faqFeedbackRecord){
		if(faqFeedbackRecordService.save(faqFeedbackRecord)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:faqFeedbackRecord:edit")
	public R update( FaqFeedbackRecordDO faqFeedbackRecord){
		faqFeedbackRecordService.update(faqFeedbackRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("web:faqFeedbackRecord:remove")
	public R remove( String id){
		if(faqFeedbackRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:faqFeedbackRecord:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		faqFeedbackRecordService.batchRemove(ids);
		return R.ok();
	}
	
}
