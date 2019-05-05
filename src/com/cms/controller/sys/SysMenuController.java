package com.cms.controller.sys;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.form.SysMenuForm;
import com.cms.model.sys.SysMenu;
import com.cms.model.sys.SysRoleMenu;
import com.cms.model.sys.SysUser;
import com.cms.model.sys.SysUserButtonRoleV;
import com.cms.model.sys.SysUserMenuRoleV;
import com.cms.service.sys.SysMenuService;
import com.cms.util.common.CutSubString;

import my.dao.pool.DBManager;
import my.dao.utils.Record;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class SysMenuController extends BaseController{
	
	@Autowired 
	private SysMenuService sysMenuService;
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public SysMenuForm getForm(){
		return new SysMenuForm();
	}
	
	
	@RequestMapping("sysmenu/list")
	public String desktop(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/sysmenu/list";
	}
	
	@ResponseBody
	@RequestMapping("sysmenu/treegridData")
	public JSONArray treegridData() {
		JSONArray ja1=sysMenuService.findAll();
		JSONArray ja=JSONArray.fromObject(JSONArray.fromObject(ja1).toString().replace("iconcls", "iconCls"));
		return ja;
	}
	
	@ResponseBody
	@RequestMapping("sysmenu/treegridData2level")
	public JSONArray treegridData2level() {
		JSONArray jap=sysMenuService.find2Level();
		return JSONArray.fromObject(JSONArray.fromObject(jap).toString().replace("iconcls", "iconCls"));
	}
	
	@ResponseBody
	@RequestMapping("sysmenu/treegridData3level")
	public JSONArray treegridData3level() {
		JSONArray ja1=sysMenuService.find3Level();
		return JSONArray.fromObject(JSONArray.fromObject(ja1).toString().replace("iconcls", "iconCls"));
	}
	@ResponseBody
	@RequestMapping("sysmenu/gridIconPath")
	public JSONArray gridIconPath(HttpServletRequest request) {
		JSONArray ja=new JSONArray();
		List<String> list= ReadCssFile(request);
		for (String string : list) {
			String icon=CutSubString.subString(string, ".", "{");
			//String icon=CutSubString.subString(string, ".", ":");
			JSONObject json=new JSONObject();
			json.put("id", icon);
			json.put("text", icon);
			json.put("iconCls", icon);
			ja.add(json);
		}
		return ja;
	}
	
	@Test
	  public static List<String> ReadCssFile(HttpServletRequest request) { 
		
		List<String> first_list = new LinkedList<>(); 
	  // final String filename = request.getSession().getServletContext().getRealPath("/")+"css\\icon_fa.css"; 
	    final String filename = request.getSession().getServletContext().getRealPath("/")+"css\\icon.css"; 
	    String str = null; 
	    int i = 0; 
	    try { 
	      LineNumberReader reader = null; 
	      reader = new LineNumberReader(new FileReader(filename)); 
	      while ((str = reader.readLine()) != null) { 
	        if (!str.isEmpty()) { 
	          String values[] = str.split("  "); 
	          first_list.add(values[0]); 
	        } 
	      } 
	    } catch (IOException e) { 
	      e.printStackTrace(); 
	    } 
	    
	    return first_list;
	  } 
	@ResponseBody
	@RequestMapping("sysmenu/del")
	public JSONObject delete() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String id = param("id", "");
		result=sysMenuService.delete(id);
		return JSONObject.fromObject(result);
	}
	
	
	@ResponseBody
	@RequestMapping("sysmenu/save")
	public JSONObject save(@ModelAttribute("form") SysMenuForm form) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String id=form.getId();
		result=sysMenuService.save(id, form);
		return JSONObject.fromObject(result);
	}
	
	@ResponseBody
	@RequestMapping("sysmenu/getButtons")
	public JSONObject getButtons(HttpSession session) {//当前登录用户
		SysUser user=(SysUser) session.getAttribute("user");
		String userid=user.getUserid();
		String menuid=param("menuid","");
		String divid=param("divid","");
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<SysUserButtonRoleV> list=sysMenuService.getButtons(userid, menuid, divid);
		if(list.size()>0){
			result.put("buttons", list);
			result.put("success", true);
		}else{
			result.put("success", false);
			result.put("buttons", list);
		}
		return JSONObject.fromObject(result);
	}
	
}
