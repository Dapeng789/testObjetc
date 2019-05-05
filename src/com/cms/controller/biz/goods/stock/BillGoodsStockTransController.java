package com.cms.controller.biz.goods.stock;

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
 *@descriptions 类说明：请添加类描述
 */
@Controller
@RequestMapping("/cms/goods/stock")
public class BillGoodsStockTransController extends BaseController{
	@RequestMapping("stock_trans")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock/stock_trans";
	}
}
