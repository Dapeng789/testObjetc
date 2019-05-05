package com.cms.controller.biz.goods.check;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.model.sys.SysUser;

import my.web.BaseController;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月21日 下午10:13:31 
 * 
 *@descriptions 类说明：盘点审核
 */
@Controller
@RequestMapping("/cms/goods/check")
public class StockCheckCheckController extends BaseController{
	@RequestMapping("stock_check_check")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/check/stock_check_check";
	}
}
