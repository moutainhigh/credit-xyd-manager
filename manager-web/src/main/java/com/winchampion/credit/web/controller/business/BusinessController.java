package com.winchampion.credit.web.controller.business;

import com.winchampion.credit.business.domain.ContentDO;
import com.winchampion.credit.business.service.ContentService;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.common.util.PageUtils;
import com.winchampion.credit.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weichen
 */
@RequestMapping("/business")
@Controller
public class BusinessController {
	@Autowired
    ContentService bContentService;

	@GetMapping()
	String business() {
		return "business/index/main";
	}

	@ResponseBody
	@GetMapping("/open/list")
	public PageUtils openList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/open/post/{cid}")
	String post(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		model.addAttribute("gtmModified", DateUtils.format(bContentDO.getGtmModified()));
		return "business/index/post";
	}
	@GetMapping("/open/page/{categories}")
	String about(@PathVariable("categories") String categories, Model model) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("categories", categories);
		ContentDO bContentDO =null;
		if(bContentService.list(map).size()>0){
			 bContentDO = bContentService.list(map).get(0);
		}
		model.addAttribute("bContent", bContentDO);
		return "business/index/post";
	}
}
