package com.cms.controller.biz.goods.stock;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.GoodsStock;
import com.cms.model.biz.goods.GoodsStockSumV;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.goods.GoodsStockService;

import my.web.BaseController;
/**
 * 
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月15日 上午11:41:50 
 * 
 *@descriptions 类说明：一级库库存查询
 */
@Controller
@RequestMapping("/cms/goods/stock")
public class BillGoodsStockQueryController extends BaseController{

	@Autowired
	private GoodsStockService goodsStockService;
	@RequestMapping("stock_query")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock/stock_query";
	}
	
	@ResponseBody
	@RequestMapping("stock_query/gridData1")
	public GridDataModel<GoodsStockSumV> gridData1() {
		//String docid = param("docid", "");
		GridDataModel<GoodsStockSumV> gridDataModel=goodsStockService.getGridDataSum(); 
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("stock_query/gridData2")
	public GridDataModel<GoodsStock> gridData2() {
		//String docid = param("docid", "");
		GridDataModel<GoodsStock> gridDataModel=goodsStockService.getGridDataDtl();
		return gridDataModel;
	}
	
	
	
	
	
	
	
	
}