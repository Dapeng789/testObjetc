package com.cms.controller.biz.dict.supply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.form.DictSupplyForm;
import com.cms.model.biz.dict.supply.DictSupply;
import com.cms.service.biz.dict.DictSupplyService;

import my.web.BaseController;
import net.sf.json.JSONObject;

/**
 * 
 * @author 作者：wanglimin
 *
 * @version 创建时间：2019年4月23日 上午11:07:39
 * 
 * @descriptions 类说明：供应商维护控制器
 */
@Controller
@RequestMapping("/cms/biz")
public class DictSupplyController extends BaseController {

	@Autowired
	private DictSupplyService service;

	@ModelAttribute("form")
	public DictSupplyForm getForm() {
		return new DictSupplyForm();
	}

	@RequestMapping("dict/supplySource")
	public String desktop(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/dict/supply/supply";
	}

	/**
	 * @author 作者：wanglimin
	 *
	 * @version 创建时间：2019年4月23日 下午3:08:33
	 * 
	 * @descriptions 类说明：查询供应商信息
	 *
	 * @return
	 */
	@RequestMapping("dict/getSupply")
	@ResponseBody
	public GridDataModel<DictSupply> getSupply() {
		String supplyname = param("supplyname", "");
		String spell = param("supplyopcode", "");
		return service.getGridData(supplyname, spell);
	}

	/**
	 * 
	 * /**
	 * 
	 * @author 作者：wanglimin
	 *
	 * @version 创建时间：2019年4月23日 下午3:08:13
	 * 
	 * @descriptions 类说明：保存供应商控制器
	 *
	 * @param json
	 * @return
	 */
	@RequestMapping("dict/saveSupply")
	@ResponseBody
	public JSONObject saveSupply(final @RequestParam("json") String json) {

		return JSONObject.fromObject(service.save(json));
	}
}
