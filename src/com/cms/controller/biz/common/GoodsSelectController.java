package com.cms.controller.biz.common;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictGoods;
import com.cms.model.biz.dict.DictGoodsPkg;
import com.cms.model.biz.goods.GoodsBillInDtl;
import com.cms.service.biz.dict.DictGoodsPkgService;

import my.web.BaseController;
import net.sf.json.JSONObject;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月16日 上午9:55:20 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Controller
@RequestMapping("/cms/common")
public class GoodsSelectController extends BaseController{
	@Autowired
	private DictGoodsPkgService dictGoodsPkgService;
	@RequestMapping("goodsSelect")
	public String goodsSelect(Model m) {
		return "cms/biz/common/goodsSelect";
	}
	@ResponseBody
	@RequestMapping("goodsSelect/datagrid")
	public GridDataModel<DictGoodsPkg> datagrid() {
		String goodsname=param("goodsnameselect","");
		String goodsspell=param("goodsspellselect","");
		GridDataModel<DictGoodsPkg> gridDataModel=dictGoodsPkgService.getGridData(goodsname,goodsspell);
		return gridDataModel;
	}
	
	
	/**
	 * 获取单个goods
	 * @return
	 */
	@ResponseBody
	@RequestMapping("goodsSelect/getGoodsSelect")
	public JSONObject getGoodsSelect() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String goodsid=param("goodsid","");
		String goodsdtlid=param("goodsdtlid","");
		result.put("data", JSONObject.fromObject(dictGoodsPkgService.findById(goodsid, goodsdtlid)));
		result.put("msg", "操作成功！");
		result.put("success", true);
		return JSONObject.fromObject(result);
	}
	
}
