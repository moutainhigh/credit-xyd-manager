package com.winchampion.credit.web.controller.business;

import java.util.ArrayList;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.reflect.TypeToken;
import com.winchampion.credit.business.domain.ProblemDO;
import com.winchampion.credit.business.domain.ProductDO;
import com.winchampion.credit.business.domain.ProductInfoDO;
import com.winchampion.credit.business.domain.ProductProblemStrategyDO;
import com.winchampion.credit.business.service.ProblemService;
import com.winchampion.credit.business.service.ProductProblemStrategyService;
import com.winchampion.credit.business.service.ProductService;
import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.common.util.GsonUtil;
import com.winchampion.credit.common.util.IdGen;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;
import com.winchampion.credit.user.utils.UserUtils;

/**
 * 产品及智能问题匹配策略
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-02 19:32:53
 */
 
@Controller
@RequestMapping("/business/financial/problemStrategy")
public class ProductProblemStrategyController {
	//前缀
	private String prefix = "business/problem" ;
	
	@Autowired
	private ProductProblemStrategyService productProblemStrategyService;
	@Autowired
	private ProblemService problemService;
	@Autowired
	private ProductService productService;
	
	@GetMapping()
	@RequiresPermissions("business:problem:financialProblemStrategy")
	String ProductProblemStrategy(){
	    return prefix+"/problemPsList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:problem:financialProblemStrategy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		params.put("delFlag", Constant.DEL_FLAG_NORMAL);
		//查询列表数据
        Query query = new Query(params);
		//List<ProductProblemStrategyDO> productProblemStrategyList = productProblemStrategyService.list(query);
        List<ProductProblemStrategyDO> productProblemStrategyList = productProblemStrategyService.compositeList(query);
		//int total = productProblemStrategyService.count(query);
        int total = productProblemStrategyService.compositeCount(query);
      //查询列表数据
  		/*List<ProblemDO> problemPsList = problemService.list(null);
  		if(CollectionUtils.isNotEmpty(problemPsList)) {
  			for (ProblemDO problemDO : problemPsList) {
  				problemDO.setProblemPsList(Arrays.asList(problemDO.getProblemOptions().split(", ")));
  			}
  		}
  		if(CollectionUtils.isNotEmpty(productProblemStrategyList)) {
  			for (ProductProblemStrategyDO psdo : productProblemStrategyList) {
  				psdo.setProblemList(problemPsList);
			}
  		}*/
  		//把列表格式化到每一条产品中
  		/*if(CollectionUtils.isNotEmpty(problemPsList)&&CollectionUtils.isNotEmpty(productProblemStrategyList)) {
  			String productStr = "";
  			for (ProblemDO problemDO : problemPsList) {//编码所有问题
  				productStr = productStr+problemDO.getId()+":"+problemDO.getProblemOptions()+";";
			}
  			for (ProductProblemStrategyDO psdo : productProblemStrategyList) {
  				psdo.setProblemPs(productStr);
			}
  		}*/
		PageUtils pageUtils = new PageUtils(productProblemStrategyList, total);
		return pageUtils;
	}
	
	
	/**
	 * 根据问题编号+可选项检查取消的可选项在智能匹配策略中是否存在
	 * @param id 问题编号
	 * @param optionsRetain 可选项字符串
	 * @return
	 */
	@ResponseBody
	@PostMapping("/optionsList")
	@RequiresPermissions("business:problem:financialProblemStrategy")
	public R optionsList(String id,String optionsRetain){
		//查询列表数据
       if(optionsRetain!=null) {
    	   List<String> options = Arrays.asList(optionsRetain.split(","));
    	   if(CollectionUtils.isNotEmpty(options)) {
    		 if(productProblemStrategyService.getOptionsList(id, options)>0) {
    			 return R.error();
    		 }else {
    			 return R.ok();
    		 }
    	   }
       }
       return R.ok();
	}
	
	@ResponseBody
	@PostMapping("/getCodeList")
	@RequiresPermissions("business:problem:financialProblemStrategy")
	public R getCodeList(String problemCode){
		Map<String, Object> params = new HashMap<>();
		params.put("problemId", problemCode);
		if(CollectionUtils.isNotEmpty(productProblemStrategyService.list(params))) {
			return R.error();
		}
		return R.ok();
	}
	
	@GetMapping("/add")
	@RequiresPermissions("web:productProblemStrategy:add")
	String add(){
	    return "web/productProblemStrategy/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("web:productProblemStrategy:edit")
	String edit(@PathVariable("id") String id,Model model){
		ProductProblemStrategyDO productProblemStrategy = productProblemStrategyService.get(id);
		model.addAttribute("productProblemStrategy", productProblemStrategy);
	    return "web/productProblemStrategy/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/batchSave")
	@RequiresPermissions("business:problem:financialProblemStrategy")
	public R batchSave(String listStr){
		System.out.println("aaa");
		String infoStr = listStr.replaceAll("&quot;", "\\\"");
		List<ProductProblemStrategyDO> list = GsonUtil.gson.fromJson(infoStr, new TypeToken<List<ProductProblemStrategyDO>>(){}.getType());
		List<ProductProblemStrategyDO> saveList = null;
		if(CollectionUtils.isNotEmpty(list)) {
			ProductDO product = null;
			saveList = new ArrayList<ProductProblemStrategyDO>();
			for (ProductProblemStrategyDO productProblemStrategyDO : list) {
				productProblemStrategyDO.setId(IdGen.uuid());
				product = new ProductDO();
				product.setId(productProblemStrategyDO.getProductId());
				product.setStrategyUpdateBy(UserUtils.getUserId());
				product.setStrategyUpdateDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
				productService.update(product);
				productProblemStrategyService.removeProductId(productProblemStrategyDO.getProductId());
				if(!StringUtils.isEmpty(productProblemStrategyDO.getMatchOption())) {
					saveList.add(productProblemStrategyDO);
				}
			}
			if(CollectionUtils.isNotEmpty(list)) {
				productProblemStrategyService.batchInsert(list);
			}
		}
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:productProblemStrategy:add")
	public R save( ProductProblemStrategyDO productProblemStrategy){
		if(productProblemStrategyService.save(productProblemStrategy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:productProblemStrategy:edit")
	public R update( ProductProblemStrategyDO productProblemStrategy){
		productProblemStrategyService.update(productProblemStrategy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("web:productProblemStrategy:remove")
	public R remove( String id){
		if(productProblemStrategyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:productProblemStrategy:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		productProblemStrategyService.batchRemove(ids);
		return R.ok();
	}
	
}
