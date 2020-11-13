package com.winchampion.credit.web.controller.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.reflect.TypeToken;
import com.winchampion.credit.business.domain.InsDO;
import com.winchampion.credit.business.domain.ProductDO;
import com.winchampion.credit.business.domain.ProductInfoDO;
import com.winchampion.credit.business.domain.ProductOperateLogDO;
import com.winchampion.credit.business.service.InsService;
import com.winchampion.credit.business.service.ProductInfoService;
import com.winchampion.credit.business.service.ProductOperateLogService;
import com.winchampion.credit.business.service.ProductService;
import com.winchampion.credit.business.vo.ProductHeatVo;
import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.common.util.GsonUtil;
import com.winchampion.credit.common.util.IdGen;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import com.winchampion.credit.common.util.R;
import com.winchampion.credit.user.domain.DictDO;
import com.winchampion.credit.user.service.DictService;
import com.winchampion.credit.user.utils.UserUtils;

/**
 * 金融产品表
 * 
 * @author zhangcong
 * @email15121007361@163.com
 * @date 2020-02-27 12:06:01
 */
 
@Controller
@RequestMapping("/business/product")
public class ProductController {
	//前缀
	private String prefix = "business/product" ;
		
	@Autowired
	private ProductService productService;
	@Autowired
	private DictService dictService;
	@Autowired
	private ProductInfoService infoService;
	@Autowired
	private ProductOperateLogService logService;
	@Autowired
	private InsService insService;
	/**
	 * 产品列表页
	 * @return
	 */
	@GetMapping("/product")
	@RequiresPermissions("business:product:financialProduct")
	String Product(){
		return prefix + "/productList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:product:financialProduct")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductDO> productList = productService.list(query);
		int total = productService.count(query);
		PageUtils pageUtils = new PageUtils(productList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("business:product:financialProduct")
	String add(Model model){
		model.addAttribute("product", new ProductDO());
		Map<String, Object> map = new HashMap<>();
		//查询产品详情介绍
		model.addAttribute("infoList", new ArrayList<ProductInfoDO>());
		//还款方式
		map.put("type", "payment_method");
		List<DictDO> paymentMethodList = dictService.list(map);
		model.addAttribute("paymentMethodList", paymentMethodList);
		//担保方式
		map.put("type", "guaranty_style");
		List<DictDO> guarantyStyleList = dictService.list(map);
		model.addAttribute("guarantyStyleList", guarantyStyleList);
		//适用地区
		map.put("type", "apply_area");
		List<DictDO> applyAreaList = dictService.list(map);
		model.addAttribute("applyAreaList", applyAreaList);
		//金融产品所属板块
		map.put("type", "product_financial_palte");
		List<DictDO> palteList = dictService.list(map);
		model.addAttribute("palteList", palteList);
		//产品申请类型
		map.put("type", "product_application_type");
		List<DictDO> applicationList = dictService.list(map);
		model.addAttribute("applicationList", applicationList);
		
		//金融机构
		map.put("insType", Constant.INS_TYPE_FINACIAL);
		map.put("delFlag", Constant.DEL_FLAG_NORMAL);
		List<InsDO> insList = insService.list(map);
		model.addAttribute("insList", insList);
		
	    return prefix+"/productAdd";
	}

	@GetMapping("/recycleDes")
	@RequiresPermissions("business:product:financialProduct")
	String recycleDes(Model model){
	    return prefix + "/recycleDes";
	}
	
	@ResponseBody
	@PostMapping("/yesRecycleDes")
	@RequiresPermissions("business:product:financialProduct")
	String yesRecycleDes(ProductDO product,Model model){
		//金融产品回收
		productService.update(product);
		//新增日志
		logService.addLog(product.getProductNo(),Constant.PRODUCT_CREDIT_RECYCLE,product.getRecycleDes(),Constant.PRODUCT_FINANCIAL);
		return prefix + "/productList";
	}
	/**
	 * 查询产品信息
	 * @param id 产品id
	 * @param type 用来标识产品信息页 1：详情页 2：编辑页 3：预览页面（不含发布）4：预览页面+产品发布
	 * @param model
	 * @return
	 */
	@GetMapping("/details/{id}/{type}")
	@RequiresPermissions("business:product:financialProduct")
	String details(@PathVariable("id") String id,@PathVariable("type")String type,Model model){
		ProductDO product = productService.get(id);
		model.addAttribute("product", product);
		Map<String, Object> map = new HashMap<>();
		//查询产品详情介绍
		map.put("productId", product.getId());
		List<ProductInfoDO> infoList = infoService.list(map);
		model.addAttribute("infoList", infoList);
		//还款方式
		map.put("type", "payment_method");
		List<DictDO> paymentMethodList = dictService.list(map);
		model.addAttribute("paymentMethodList", paymentMethodList);
		//担保方式
		map.put("type", "guaranty_style");
		List<DictDO> guarantyStyleList = dictService.list(map);
		model.addAttribute("guarantyStyleList", guarantyStyleList);
		//适用地区
		map.put("type", "apply_area");
		List<DictDO> applyAreaList = dictService.list(map);
		model.addAttribute("applyAreaList", applyAreaList);
		//金融产品所属板块
		map.put("type", "product_financial_palte");
		List<DictDO> palteList = dictService.list(map);
		model.addAttribute("palteList", palteList);
		//产品申请类型
		map.put("type", "product_application_type");
		List<DictDO> applicationList = dictService.list(map);
		model.addAttribute("applicationList", applicationList);
		if("1".equals(type)) {//详情页
			return prefix + "/productDetails";
		}else if("3".equals(type)||"4".equals(type)){//产品预览页
			//是否含发布按钮
			model.addAttribute("ifIssue", "4".equals(type)?"true":"false");
			return prefix + "/productPreview";
		}else{//编辑页
			//金融机构
			map.put("insType", Constant.INS_TYPE_FINACIAL);
			map.put("delFlag", Constant.DEL_FLAG_NORMAL);
			List<InsDO> insList = insService.list(map);
			model.addAttribute("insList", insList);
			return prefix + "/productEdit";
		}
	}
	
	@ResponseBody
	@GetMapping("/logList")
	@RequiresPermissions("business:product:financialProduct")
	public PageUtils logList(@RequestParam Map<String, Object> params){
		//查询变更日志列表数据
		params.put("productType", Constant.PRODUCT_FINANCIAL);
        Query query = new Query(params);
		List<ProductOperateLogDO> logList = logService.list(query);
		int total = logService.count(query);
		PageUtils pageUtils = new PageUtils(logList, total);
		return pageUtils;
	}
	
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:product:financialProduct")
	String edit(@PathVariable("id") String id,Model model){
		model.addAttribute("id", id);
	    return prefix + "/edit";
	}
	
	/**
	 * 新增/修改
	 */
	@ResponseBody
	@PostMapping("/addProduct")
	@RequiresPermissions("business:product:financialProduct")
	public String addProduct(@RequestParam("productstr")String productstr, @RequestParam("infoStr")String infoStr){
		ProductDO product = GsonUtil.gson.fromJson(productstr, new TypeToken<ProductDO>(){}.getType());
		String userId = UserUtils.getUserId();
		String createDate = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		product.setUpdateBy(userId);
		product.setUpdateDate(createDate);
		//String url = prefix;
		if(StringUtils.isEmpty(product.getProductNo())) {//新增
			//获取最新排序的产品编号
			product.setProductNo(getMaxProductNo());
			product.setId(IdGen.uuid());
			product.setCreateBy(userId);
			product.setCreateDate(createDate);
			product.setIsRelease(Constant.PRODUCT_NOT_ISSUE);
			product.setDelFlag(Constant.DEL_FLAG_NORMAL);
			if(productService.save(product)>0) {
				//记录日志
				logService.addLog(product.getProductNo(),Constant.PRODUCT_CHANGE_NEW,product.getRecycleDes(),Constant.PRODUCT_FINANCIAL);
			}
			//url = url+"/productList";
		}else {//修改
			//修改
			if(productService.update(product)>0) {
				//删除产品详情
				infoService.remove(product.getId());
				//记录日志
				logService.addLog(product.getProductNo(),Constant.PRODUCT_CREDIT_EDIT,product.getRecycleDes(),Constant.PRODUCT_FINANCIAL);
			}
			//url = "redirect:" + "/business/product/details/"+product.getId()+"/1";
		}
		if (!StringUtils.isEmpty(infoStr)) {
			//String infoStr = product.getInfoStr().replaceAll("&quot;", "\\\"");
			//infoStr = infoStr.replaceAll("&quot;", "\\\"");
			List<ProductInfoDO> infoList = GsonUtil.gson.fromJson(infoStr, new TypeToken<List<ProductInfoDO>>(){}.getType());
			if (CollectionUtils.isNotEmpty(infoList)) {
				for (ProductInfoDO productInfoDO : infoList) {
					productInfoDO.setId(IdGen.uuid());
					productInfoDO.setProductId(product.getId());
				}
			}
			product.setInfoList(infoList);
			//批量新增产品详情
			infoService.batchInsert(infoList);
		}
		return product.getId();
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:product:financialProduct")
	public R save(ProductDO product){
		if(productService.save(product)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:product:financialProduct")
	public R update( ProductDO product){
		productService.update(product);
		//新增日志
		logService.addLog(product.getProductNo(),Constant.PRODUCT_CREDIT_EDIT,product.getRecycleDes(),Constant.PRODUCT_FINANCIAL);
		return R.ok();
	}
	
	/**
	 * 发布
	 */
	@ResponseBody
	@PostMapping( "/issue")
	@RequiresPermissions("business:product:financialProduct")
	public R issue(String productNo,String id){
		ProductDO product = new ProductDO();
		product.setProductNo(productNo);
		product.setIsRelease(Constant.PRODUCT_YES_ISSUE);;
		product.setUpdateBy(UserUtils.getUserId());
		product.setUpdateDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		if(productService.update(product)>0){
			logService.addLog(product.getProductNo(),Constant.PRODUCT_CREDIT_ISSUE,product.getRecycleDes(),Constant.PRODUCT_FINANCIAL);
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:product:financialProduct")
	public R remove(String productNo){
		ProductDO product = new ProductDO();
		product.setProductNo(productNo);
		product.setDelFlag(Constant.DEL_FLAG_DELETE);
		if(productService.update(product)>0){
			logService.addLog(product.getProductNo(),Constant.PRODUCT_CREDIT_DEL,product.getRecycleDes(),Constant.PRODUCT_FINANCIAL);
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("business:product:financialProduct")
	public R remove(@RequestParam("ids[]") String[] ids){
		productService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 获取机构信息
	 * @param finEntId
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getInsLogo")
	@RequiresPermissions("business:product:financialProduct")
	public InsDO getInsLogo(@RequestParam String finEntId){
		InsDO ins = insService.get(finEntId);
		return ins;
	}

	/**
	 * 产品缺省排序页
	 * @return
	 */
	@GetMapping("/defaultSort")
	@RequiresPermissions("business:product:defaultSort")
	String defaultSort(){
		return prefix + "/productSort";
	}
	/**
	 * 更改产品缺省优先级排序
	 * @param sortStr
	 * @return
	 */
	@ResponseBody
	@PostMapping("/updateSort")
	@RequiresPermissions("business:product:defaultSort")
	public R updateSort(@RequestParam("sortStr")String sortStr){
		sortStr = sortStr.replaceAll("&quot;", "\\\"");
		if(!StringUtils.isEmpty(sortStr)){
			List<ProductDO> list = GsonUtil.gson.fromJson(sortStr, new TypeToken<List<ProductDO>>(){}.getType());
			if(CollectionUtils.isNotEmpty(list)) {
				for (ProductDO productDO : list) {
					productService.updateSort(productDO);
				}
			}
		}
		return R.ok();
	}
	/**
	 * 获取最新产品编码
	 * @return
	 */
	public String getMaxProductNo() {
		String maxProductNo = productService.maxProductNo();
		if(StringUtils.isEmpty(maxProductNo)) {
			return "A00001";
		}else {
			String st = String.valueOf(Integer.parseInt(maxProductNo.substring(1, maxProductNo.length()))+1);
			int length = st.length();
			for (int i = 0; i < 5-length; i++) {
				st = "0"+st;
			}
			return "A"+st;
		}
	}
	
	/**
	 * 金融产品关注热度页
	 * @return
	 */
	@GetMapping("/attentionHeat")
	@RequiresPermissions("business:product:attentionHeat")
	String attentionHeat(){
		return prefix + "/attentionHeat";
	}
	
	/**
	 * 获取产品关注度列表
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/heatList")
	@RequiresPermissions("business:product:attentionHeat")
	public PageUtils heatList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProductHeatVo> productList = productService.getProductHeatList(query);
		int total = productService.countHeat(query);
		PageUtils pageUtils = new PageUtils(productList, total);
		return pageUtils;
	}
}
