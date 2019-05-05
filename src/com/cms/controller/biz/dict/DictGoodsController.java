package com.cms.controller.biz.dict;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.form.DictGoodsForm;
import com.cms.model.biz.dict.DictClassGoods;
import com.cms.model.biz.dict.DictGoods;
import com.cms.model.biz.dict.DictGoodsPkg;
import com.cms.model.sys.SysUser;
import com.cms.util.biz.PageFactory;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/cms/biz")
public class DictGoodsController  extends BaseController{
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public DictGoodsForm getForm(){
		return new DictGoodsForm();
	}
	
	
	@RequestMapping("dict/goods")
	public String desktop(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/dict/dict_goods";
	}
	
	@ResponseBody
	@RequestMapping("dict/goods/treegrid1Data")
	public JSONArray treegridData(Model m) {
		List<DictClassGoods> list1=DictClassGoods.INSTANCE.query(" (pid = '' or pid is null) order by id");//一级
		JSONArray ja1=new JSONArray();//一级菜单JSONArray
		for (DictClassGoods class1 : list1) {
			String pid=class1.getId();
			JSONObject jo1=JSONObject.fromObject(class1);
			List<DictClassGoods> list2=DictClassGoods.INSTANCE.query(" pid = ? ",pid);//二级
			if(list2.size()==0){
				ja1.add(jo1);
				continue;
			};
			JSONArray ja2=new JSONArray();//二级菜单JSONArray
			for (DictClassGoods class2 : list2) {
				String pid2=class2.getId();
				JSONObject jo2=JSONObject.fromObject(class2);
				List<DictClassGoods> list3=DictClassGoods.INSTANCE.query(" pid = ? ",pid2);//三级
				if(list3.size()==0){
					ja2.add(jo2);
					continue;
				};
				JSONArray ja3=new JSONArray();//三级菜单JSONArray
				for (DictClassGoods calss3 : list3) {
					JSONObject jo3=JSONObject.fromObject(calss3);
					ja3.add(jo3);
				}
				if(ja3.size()>0){jo2.put("children", ja3);}
				ja2.add(jo2);
			}
			if(ja2.size()>0){jo1.put("children", ja2);};
			ja1.add(jo1);
		}
		JSONArray ja=JSONArray.fromObject(JSONArray.fromObject(ja1).toString().replace("iconcls", "iconCls"));
		return ja;
	}
	
	@ResponseBody
	@RequestMapping("dict/goods/grid2Data")
	public GridDataModel<DictGoods> grid2Data(Model m) {
		String classid = param("classid", "");
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and classid like ?");
		params.add(classid + "%");
		GridDataModel<DictGoods> gridDataModel=PageFactory.newPage(DictGoods.class, filter.toString()," order by goodsid ",
				params.toArray());
		return gridDataModel;
		
	}
	
	@ResponseBody
	@RequestMapping("dict/goods/gridDataPkg")
	public GridDataModel<DictGoodsPkg> gridDataPkg(Model m) {
		String goodsid = param("goodsid", "");
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and goodsid = ? ");
		params.add(goodsid);
		GridDataModel<DictGoodsPkg> gridDataModel=PageFactory.newPage(DictGoodsPkg.class, filter.toString()," order by sortno ",
				params.toArray());
		return gridDataModel;
		
	}
}
