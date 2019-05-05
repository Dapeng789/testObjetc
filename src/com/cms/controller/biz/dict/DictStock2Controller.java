package com.cms.controller.biz.dict;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.model.sys.SysUser;

import my.web.BaseController;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月15日 下午4:10:26 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Controller
@RequestMapping("/cms/biz")
public class DictStock2Controller extends BaseController{
	
	@RequestMapping("dict/stock2")
	public String dictSource(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/dict/dict_stock2";
	}
}
