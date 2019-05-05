package com.cms.controller.biz.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictStore;
import com.cms.service.biz.dict.DictStoreService;

import my.web.BaseController;
import net.sf.json.JSONArray;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月24日 下午2:51:29 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Controller
@RequestMapping("/cms/common")
public class DictStoreController extends BaseController{
	@Autowired
	private DictStoreService dictStoreService; 
	/**
 	 * 获取仓库列表
 	 * @return
 	 */
	@ResponseBody
	@RequestMapping("store/getDictStore")
	public JSONArray getDictStore() {
		return JSONArray.fromObject(dictStoreService.findAll());
	}
	
	
	@RequestMapping("storeSelect")
	public String goodsSelect(Model m) {
		return "cms/biz/common/storeSelect";
	}
	
	@ResponseBody
	@RequestMapping("storeSelect/datagrid")
	public GridDataModel<DictStore> datagrid() {
		String name=param("storenameselect","");
		GridDataModel<DictStore> gridDataModel=dictStoreService.getGridData(name);
		return gridDataModel;
	}
}
