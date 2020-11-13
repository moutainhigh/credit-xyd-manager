package com.winchampion.credit.web.controller.business;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.winchampion.credit.business.domain.CreditProductDO;
import com.winchampion.credit.business.domain.CreditProductInfoDO;
import com.winchampion.credit.business.domain.InsDO;
import com.winchampion.credit.business.domain.ProductDO;
import com.winchampion.credit.business.domain.ProductOperateLogDO;
import com.winchampion.credit.business.service.CreditProductInfoService;
import com.winchampion.credit.business.service.CreditProductService;
import com.winchampion.credit.business.service.InsService;
import com.winchampion.credit.business.service.ProductOperateLogService;
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
 * 信用产品
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-03-01 18:18:36
 */
 
@Controller
@RequestMapping("/business/creditProduct")
public class CreditProductController {
	//前缀
	private String prefix = "business/product" ;
		
	@Autowired
	private CreditProductService productService;
	@Autowired
	private DictService dictService;
	@Autowired
	private CreditProductInfoService infoService;
	@Autowired
	private ProductOperateLogService logService;
	@Autowired
	private InsService insService;
	/**
	 * 产品列表页
	 * @return
	 */
	@GetMapping("/product")
	@RequiresPermissions("business:product:creditProduct")
	String Product(){
		return prefix + "/creditProductList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:product:creditProduct")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CreditProductDO> productList = productService.list(query);
		int total = productService.count(query);
		PageUtils pageUtils = new PageUtils(productList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("business:product:creditProduct")
	String add(Model model){
		model.addAttribute("product", new CreditProductDO());
		Map<String, Object> map = new HashMap<>();
		//查询产品详情介绍
		model.addAttribute("infoList", new ArrayList<CreditProductInfoDO>());
		//信用产品所属板块
		map.put("type", "product_credit_palte");
		List<DictDO> palteList = dictService.list(map);
		model.addAttribute("palteList", palteList);
		//信用机构
		map.put("insType", Constant.INS_TYPE_CREDIT);
		map.put("delFlag", Constant.DEL_FLAG_NORMAL);
		List<InsDO> insList = insService.list(map);
		model.addAttribute("insList", insList);
		
	    return prefix+"/creditProductAdd";
	}

	@GetMapping("/recycleDes")
	@RequiresPermissions("business:product:creditProduct")
	String recycleDes(Model model){
	    return prefix + "/recycleDes";
	}
	
	@ResponseBody
	@PostMapping("/yesRecycleDes")
	@RequiresPermissions("business:product:creditProduct")
	String yesRecycleDes(CreditProductDO product,Model model){
		//信用产品回收
		productService.update(product);
		//新增日志
		logService.addLog(product.getProductNo(),Constant.PRODUCT_CREDIT_RECYCLE,product.getRecycleDes(),Constant.PRODUCT_CREDIT);
		return prefix + "/creditProductList";
	}
	
	@GetMapping("/details/{id}/{type}")
	@RequiresPermissions("business:product:creditProduct")
	String details(@PathVariable("id") String id,@PathVariable("type")String type,Model model){
		CreditProductDO product = productService.get(id);
		model.addAttribute("product", product);
		Map<String, Object> map = new HashMap<>();
		//查询产品详情介绍
		map.put("creditProductId", product.getId());
		List<CreditProductInfoDO> infoList = infoService.list(map);
		model.addAttribute("infoList", infoList);
		//信用产品所属板块
		map.put("type", "product_credit_palte");
		List<DictDO> palteList = dictService.list(map);
		model.addAttribute("palteList", palteList);
		if("1".equals(type)) {//详情页
			return prefix + "/creditProductDetails";
		}else if("3".equals(type)||"4".equals(type)){
			//是否含发布按钮
			model.addAttribute("ifIssue", "4".equals(type)?"true":"false");
			return prefix + "/creditProductPreview";
		}else {//编辑页
			//信用机构
			map.put("insType", Constant.INS_TYPE_CREDIT);
			map.put("delFlag", Constant.DEL_FLAG_NORMAL);
			List<InsDO> insList = insService.list(map);
			model.addAttribute("insList", insList);
			return prefix + "/creditProductEdit";
		}
	}
	
	@ResponseBody
	@GetMapping("/logList")
	@RequiresPermissions("business:product:creditProduct")
	public PageUtils logList(@RequestParam Map<String, Object> params){
		//查询变更日志列表数据
		params.put("productType", Constant.PRODUCT_CREDIT);
        Query query = new Query(params);
		List<ProductOperateLogDO> logList = logService.list(query);
		int total = logService.count(query);
		PageUtils pageUtils = new PageUtils(logList, total);
		return pageUtils;
	}
	
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:product:creditProduct")
	String edit(@PathVariable("id") String id,Model model){
		model.addAttribute("id", id);
	    return prefix + "/edit";
	}
	
	/**
	 * 新增/修改
	 */
	@ResponseBody
	@PostMapping("/addProduct")
	@RequiresPermissions("business:product:creditProduct")
	public String addProduct(@RequestParam("productstr")String productstr, @RequestParam("infoStr")String infoStr){
		CreditProductDO product = GsonUtil.gson.fromJson(productstr, new TypeToken<CreditProductDO>(){}.getType());
		String userId = UserUtils.getUserId();
		String createDate = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		product.setUpdateBy(userId);
		product.setUpdateDate(createDate);
		//String url = "";
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
				logService.addLog(product.getProductNo(),Constant.PRODUCT_CHANGE_NEW,product.getRecycleDes(),Constant.PRODUCT_CREDIT);
			}
			//url = prefix+"/creditProductList";
		}else {//修改
			//修改
			if(productService.update(product)>0) {
				//删除产品详情
				infoService.remove(product.getId());
				//记录日志
				logService.addLog(product.getProductNo(),Constant.PRODUCT_CREDIT_EDIT,product.getRecycleDes(),Constant.PRODUCT_CREDIT);
			}
			//url = "redirect:" + "/business/creditProduct/details/"+product.getId()+"/1";
		}
		if (!StringUtils.isEmpty(infoStr)) {
			//String infoStr = product.getInfoStr().replaceAll("&quot;", "\\\"");
			List<CreditProductInfoDO> infoList = GsonUtil.gson.fromJson(infoStr, new TypeToken<List<CreditProductInfoDO>>(){}.getType());
			if (CollectionUtils.isNotEmpty(infoList)) {
				for (CreditProductInfoDO productInfoDO : infoList) {
					productInfoDO.setId(IdGen.uuid());
					productInfoDO.setCreditProductId(product.getId());
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
	@RequiresPermissions("business:product:creditProduct")
	public R save(CreditProductDO product){
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
	@RequiresPermissions("business:product:creditProduct")
	public R update(CreditProductDO product){
		productService.update(product);
		//新增日志
		logService.addLog(product.getProductNo(),Constant.PRODUCT_CREDIT_EDIT,product.getRecycleDes(),Constant.PRODUCT_CREDIT);
		return R.ok();
	}
	
	/**
	 * 发布
	 */
	@ResponseBody
	@PostMapping( "/issue")
	@RequiresPermissions("business:product:creditProduct")
	public R issue(String productNo,String id){
		CreditProductDO product = new CreditProductDO();
		product.setProductNo(productNo);
		product.setIsRelease(Constant.PRODUCT_YES_ISSUE);;
		product.setUpdateBy(UserUtils.getUserId());
		product.setUpdateDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		if(productService.update(product)>0){
			logService.addLog(product.getProductNo(),Constant.PRODUCT_CREDIT_ISSUE,product.getRecycleDes(),Constant.PRODUCT_CREDIT);
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:product:creditProduct")
	public R remove(String productNo){
		CreditProductDO product = new CreditProductDO();
		product.setProductNo(productNo);
		product.setDelFlag(Constant.DEL_FLAG_DELETE);
		if(productService.update(product)>0){
			logService.addLog(product.getProductNo(),Constant.PRODUCT_CREDIT_DEL,product.getRecycleDes(),Constant.PRODUCT_CREDIT);
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("business:product:creditProduct")
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
	@RequiresPermissions("business:product:creditProduct")
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
	
	/*@ResponseBody
	@PostMapping("/updateSort")
	@RequiresPermissions("business:product:defaultSort")
	public R updateSort(@RequestParam("sortStr")String sortStr){
		sortStr = sortStr.replaceAll("&quot;", "\\\"");
		if(!StringUtils.isEmpty(sortStr)){
			List<CreditProductDO> list = GsonUtil.gson.fromJson(sortStr, new TypeToken<List<CreditProductDO>>(){}.getType());
			if(CollectionUtils.isNotEmpty(list)) {
				for (ProductDO productDO : list) {
					productService.update(productDO);
				}
			}
		}
		return R.ok();
	}*/
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
	
}
