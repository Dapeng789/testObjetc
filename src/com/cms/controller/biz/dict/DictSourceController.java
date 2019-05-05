package com.cms.controller.biz.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.form.DictProdareaForm;
import com.cms.model.biz.dict.DictProdarea;
import com.cms.model.sys.SysUser;
import com.cms.util.biz.PageFactory;

import my.dao.pool.DBManager;
import my.util.MD5Util;
import my.web.BaseController;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms/biz")
public class DictSourceController extends BaseController{
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public DictProdareaForm getForm(){
		return new DictProdareaForm();
	}
	
	
	@RequestMapping("dict/dictSource")
	public String dictSource(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/dict/dict_class_source";
	}
	
	
	@ResponseBody
	@RequestMapping("dict/dictSource/gridData")
	public GridDataModel<DictProdarea> gridData(Model m) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();

		GridDataModel<DictProdarea> gridDataModel=PageFactory.newPage(DictProdarea.class, filter.toString()," order by sourceid ",
				params.toArray());
		return gridDataModel;
	}
	
	
	@ResponseBody
	@RequestMapping("dict/dictSource/save")
	public JSONObject save(final @RequestParam("json") String json) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<DictProdarea> insert = model.inserts(DictProdarea.class);
		List<DictProdarea> delete = model.deletes(DictProdarea.class);
		List<DictProdarea> update = model.updates(DictProdarea.class);
		try {
			for (DictProdarea comp : delete) {
				comp.delete();
			}
			for (DictProdarea comp : update) {
				comp.update();
			}
			for (DictProdarea comp : insert) {
				comp.setId(comp.newId()+"");//oracle可以自动生成id 这里不能这么写
				comp.insert();
			}
			DBManager.commitAll();
			result.put("msg", "保存成功");
			result.put("success", true);
			
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "失败!:"+e.getMessage());
		}finally {
			
		}

		return JSONObject.fromObject(result);	
		
	}
}
