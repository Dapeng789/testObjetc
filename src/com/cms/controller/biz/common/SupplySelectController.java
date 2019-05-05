package com.cms.controller.biz.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.supply.DictSupply;
import com.cms.service.biz.dict.DictSupplyService;

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
public class SupplySelectController extends BaseController{
	@Autowired
	private DictSupplyService dictSupplyService;
	@RequestMapping("supplySelect")
	public String supplySelect(Model m) {
		return "cms/biz/common/supplySelect";
	}
	@ResponseBody
	@RequestMapping("supplySelect/datagrid")
	public GridDataModel<DictSupply> datagrid() {
		String supplyname=param("supplynameselect","");
		String supplyspell=param("supplyspellselect","");
		GridDataModel<DictSupply> gridDataModel=dictSupplyService.getGridData(supplyname,supplyspell);
		return gridDataModel;
	}
}
