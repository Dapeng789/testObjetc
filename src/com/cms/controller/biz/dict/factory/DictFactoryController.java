package com.cms.controller.biz.dict.factory;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.form.DictFactoryForm;
import com.cms.model.biz.dict.factory.DictFactory;
import com.cms.service.biz.dict.DictFactoryService;

import my.web.BaseController;
import net.sf.json.JSONObject;

/**
 * @author 作者：wlm
 *
 * @version 创建时间：2019年4月15日 上午9:45:23
 * 
 * @descriptions 类说明：生产厂家 CONTROLLER
 */
@Controller
@RequestMapping("/cms/biz")
public class DictFactoryController extends BaseController {

	@Autowired
	private DictFactoryService service;

	@ModelAttribute("form")
	public DictFactoryForm getForm() {
		return new DictFactoryForm();
	}

	/**
	 * 
	 * @author 作者：wlm
	 *
	 * @version 创建时间：2019年4月16日 上午8:52:45
	 * 
	 * @descriptions 描述：生产厂家首页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("dict/factorySource")
	public String desktop(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/dict/factory/factory";
	}

	/**
	 * 
	 * @author 作者：wlm
	 *
	 * @version 创建时间：2019年4月16日 上午8:52:18
	 * 
	 * @descriptions 描述：保存生产厂家
	 * @param json
	 * @return
	 */
	@RequestMapping("dict/saveFactory")
	@ResponseBody
	public JSONObject saveFactory(final @RequestParam("json") String json) {
	 
		return JSONObject.fromObject(service.save(json));
	}

	@RequestMapping("dict/getFactory")
	@ResponseBody
	public GridDataModel<DictFactory> getFactory() {
		String factorycode = param("factorycode", "");
		String factoryname = param("factoryname", "");
		return service.getFactory(factorycode, factoryname);
	}
}
