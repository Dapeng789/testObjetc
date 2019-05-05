package com.cms.controller.biz.dict;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.form.DictClassForm;
import com.cms.model.biz.dict.DictClassDev;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.CRUDCardService;

import my.dao.pool.DBManager;
import my.dao.utils.Record;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms/biz")
public class DictClassDevController extends BaseController{
	
	
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public DictClassForm getForm(){
		return new DictClassForm();
	}
	
	
	@RequestMapping("dict/classDev")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/dict/dict_class_dev";
	}
	
	
	@ResponseBody
	@RequestMapping("dict/classDev/treegridData")
	public JSONArray treegridData(Model m) {
		List<DictClassDev> list1=DictClassDev.INSTANCE.query(" (pid = '' or pid is null) order by id");//一级
		JSONArray ja1=new JSONArray();//一级菜单JSONArray
		for (DictClassDev class1 : list1) {
			String pid=class1.getId();
			JSONObject jo1=JSONObject.fromObject(class1);
			List<DictClassDev> list2=DictClassDev.INSTANCE.query(" pid = ? ",pid);//二级
			if(list2.size()==0){
				ja1.add(jo1);
				continue;
			};
			JSONArray ja2=new JSONArray();//二级菜单JSONArray
			for (DictClassDev class2 : list2) {
				String pid2=class2.getId();
				JSONObject jo2=JSONObject.fromObject(class2);
				List<DictClassDev> list3=DictClassDev.INSTANCE.query(" pid = ? ",pid2);//三级
				if(list3.size()==0){
					ja2.add(jo2);
					continue;
				};
				JSONArray ja3=new JSONArray();//三级菜单JSONArray
				for (DictClassDev calss3 : list3) {
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
	@RequestMapping("dict/classDev/del")
	public JSONObject delete() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String id = param("id", "");
		//判断如果有子项，则不能删除
		List<DictClassDev> list=DictClassDev.INSTANCE.query(" pid=? ", id);
		if(list.size()>0){
			result.put("success", false);
			result.put("msg", "该组件有子项，不能删除！");
		}else{
			try {
				//删除
				DictClassDev dictClass=DictClassDev.INSTANCE.queryOne("id=?", id);
				dictClass.delete();
				//commit
				DBManager.commitAll();
				result.put("success", true);
				result.put("msg", "删除成功！");
			} catch (Exception e) {
				DBManager.rollbackAll();
				result.put("success", false);
				result.put("msg", "失败！"+e.getMessage());
				e.printStackTrace();
			}
		}
		return JSONObject.fromObject(result);
	}
	
	@ResponseBody
	@RequestMapping("dict/classDev/save")
	public JSONObject save(@ModelAttribute("form") DictClassForm form) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		String pid=form.getPid()==null?"":form.getPid();
		if (pid.length()>=6){
			result.put("msg", "操作失败！"+"不能再生成子项");
			result.put("success", false);
			return JSONObject.fromObject(result);
		}
		String id=form.getId();
		
		try {
			if (!(null==id||id.equals(""))){
				//更新
				DictClassDev dictClass=DictClassDev.INSTANCE.queryOne(" id=? ", id);
				BeanUtils.copyProperties(dictClass, form);
				dictClass.update();
			}else{
				//新增
				DictClassDev dictClass=new DictClassDev();
				BeanUtils.copyProperties(dictClass, form);
				String where=pid==""?" and (pid is null or pid='' )  ":" and pid = '"+pid+"' ";
				Record record=DictClassDev.INSTANCE.viewHelper().queryOne("select max(id) as id from Dict_Class_dev where 1=1 " + where  );
				String idMax=record.get("id")==null?pid+"00":record.get("id").toString();//包含父级编码的最大编码
				idMax=idMax.substring(pid.length()+1);//去掉父级编码加一
				String ids=pid+String.format("%02d",Integer.valueOf(idMax)+1);//补零
				dictClass.setId(ids);
				dictClass.insert();
			}
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("msg", "操作失败！"+e.getMessage());
			result.put("success", false);
			return JSONObject.fromObject(result);
		}
		DBManager.commitAll();
		result.put("msg", "操作成功！");
		result.put("success", true);
		
		return JSONObject.fromObject(result);
	}
}
