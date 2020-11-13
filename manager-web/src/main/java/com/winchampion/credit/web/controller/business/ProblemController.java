package com.winchampion.credit.web.controller.business;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.util.CollectionUtil;
import org.apache.commons.collections.CollectionUtils;
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

import com.winchampion.credit.business.domain.InsDO;
import com.winchampion.credit.business.domain.ProblemDO;
import com.winchampion.credit.business.domain.ProductDO;
import com.winchampion.credit.business.domain.ProductInfoDO;
import com.winchampion.credit.business.service.ProblemService;
import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.common.util.IdGen;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;
import com.winchampion.credit.user.domain.DictDO;
import com.winchampion.credit.user.service.DictService;
import com.winchampion.credit.user.utils.UserUtils;


/**
 * 金融产品智能匹配问题
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-02 13:09:23
 */
 
@Controller
@RequestMapping("/business/financial/problem")
public class ProblemController {
	//前缀
	private String prefix = "business/problem" ;
	
	@Autowired
	private ProblemService problemService;
	@Autowired
	private DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("business:problem:financialProblem")
	String Problem(){
	    return prefix+"/problemList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:problem:financialProblem")
	public PageUtils list(@RequestParam Map<String, Object> params){
		params.put("delFlag", Constant.DEL_FLAG_NORMAL);
		//查询列表数据
        Query query = new Query(params);
		List<ProblemDO> problemList = problemService.list(query);
		int total = problemService.count(query);
		PageUtils pageUtils = new PageUtils(problemList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/allList")
	@RequiresPermissions("business:problem:financialProblem")
	public List<ProblemDO> allList(){
		Map<String, Object> params = new HashMap<>();
		params.put("delFlag", Constant.DEL_FLAG_NORMAL);
		//查询列表数据
		List<ProblemDO> problemPsList = problemService.list(params);
		if(CollectionUtils.isNotEmpty(problemPsList)) {
			for (ProblemDO problemDO : problemPsList) {
				problemDO.setProblemPsList(Arrays.asList(problemDO.getProblemOptions().split(", ")));
			}
		}
		return problemPsList;
	}
	
	@GetMapping("/details/{id}/{type}")
	@RequiresPermissions("business:problem:financialProblem")
	String details(@PathVariable("id") String id,@PathVariable("type")String type,Model model){
		ProblemDO problemDO = problemService.get(id);
		model.addAttribute("problem", problemDO);
		//选项字典
		Map<String, Object> map = new HashMap<>();
		map.put("type", "capacity_option");
		List<DictDO> capacityList = dictService.list(map);
		model.addAttribute("capacityList", capacityList);
		model.addAttribute("type", type);
		return prefix + "/problemDetails";
	}
	
	@GetMapping("/add")
	@RequiresPermissions("business:problem:financialProblem")
	String add(Model model){
		ProblemDO problemDO = new ProblemDO();
		model.addAttribute("problem", problemDO);
		//选项字典
		Map<String, Object> map = new HashMap<>();
		map.put("type", "capacity_option");
		List<DictDO> capacityList = dictService.list(map);
		model.addAttribute("capacityList", capacityList);
		return prefix + "/problemAdd";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:problem:financialProblem")
	String edit(@PathVariable("id") String id,Model model){
		ProblemDO problem = problemService.get(id);
		model.addAttribute("problem", problem);
	    return "web/problem/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:problem:financialProblem")
	public R save( ProblemDO problem){
		problem.setId(IdGen.uuid());
		String userId = UserUtils.getUserId();
		String date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		problem.setCreateBy(userId);
		problem.setCreateDate(date);
		problem.setUpdateBy(userId);
		problem.setUpdateDate(date);
		problem.setDelFlag(Constant.DEL_FLAG_NORMAL);
		if(problemService.save(problem)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@PostMapping("/update")
	@RequiresPermissions("business:problem:financialProblem")
	public R update( ProblemDO problem){
		problem.setUpdateBy(UserUtils.getUserId());
		problem.setUpdateDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		problemService.update(problem);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:problem:financialProblem")
	public R remove( String id){
		//if(problemService.remove(id)>0){
		ProblemDO problem = new ProblemDO();
		problem.setId(id);
		problem.setUpdateBy(UserUtils.getUserId());
		problem.setUpdateDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		problem.setDelFlag(Constant.DEL_FLAG_DELETE);
		if(problemService.update(problem)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:problem:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		problemService.batchRemove(ids);
		return R.ok();
	}
	
	@ResponseBody
	@GetMapping("/getProblemCode")
	@RequiresPermissions("business:problem:financialProblem")
	public R getProblemCode(String problemCode){
		Map<String, Object> map = new HashMap<>();
		map.put("delFlag", Constant.DEL_FLAG_NORMAL);
		map.put("problemCode", problemCode);
		List<ProblemDO> list = problemService.list(map);
		if(CollectionUtils.isNotEmpty(list)) {
			return R.error();
		}
		return R.ok();
	}
	
}
