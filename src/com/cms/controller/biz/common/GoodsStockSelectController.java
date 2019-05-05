package com.cms.controller.biz.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictGoods;
import com.cms.model.biz.dict.DictGoodsPkg;
import com.cms.model.biz.goods.GoodsBillInDtl;
import com.cms.model.biz.goods.GoodsStock;
import com.cms.model.biz.goods.GoodsStockSumV;
import com.cms.service.biz.dict.DictGoodsPkgService;
import com.cms.service.biz.goods.GoodsStockService;

import my.web.BaseController;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月16日 上午9:55:20 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Controller
@RequestMapping("/cms/common")
public class GoodsStockSelectController extends BaseController{
	@Autowired
	private GoodsStockService goodsStockService;
	
	
	@RequestMapping("goodsStockSelectDtl")
	public String goodsSelectDtl(Model m) {
		String storeid=param("storeid","");
		m.addAttribute("storeid", storeid);
		return "cms/biz/common/goodsStockSelectDtl";
	}
	
	@RequestMapping("goodsStockSelectSum")
	public String goodsSelectSum(Model m) {
		String storeid=param("storeid","");
		m.addAttribute("storeid", storeid);
		return "cms/biz/common/goodsStockSelectSum";
	}
	
	@ResponseBody
	@RequestMapping("goodsStockSelectDtl/datagrid")
	public GridDataModel<GoodsStock> datagridDtl() {
		String storeid=param("storeid","");
		String goodsname=param("goodsnameselect","");
		String goodsspell=param("goodsspellselect","");
		GridDataModel<GoodsStock> gridDataModel=goodsStockService.getGridDataDtl(storeid,goodsname,goodsspell);
		return gridDataModel;
	}
	
	
	@ResponseBody
	@RequestMapping("goodsStockSelectSum/datagrid")
	public GridDataModel<GoodsStockSumV> datagridSum() {
		String storeid=param("storeid","");
		String goodsname=param("goodsnameselect","");
		String goodsspell=param("goodsspellselect","");
		GridDataModel<GoodsStockSumV> gridDataModel=goodsStockService.getGridDataSum(storeid,goodsname,goodsspell);
		return gridDataModel;
	}
	
	
	
}
