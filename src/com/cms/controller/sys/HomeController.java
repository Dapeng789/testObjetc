package com.cms.controller.sys;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.model.sys.SysMenu;
import com.cms.model.sys.SysUser;
import com.cms.model.sys.SysUserMenuRoleV;
import com.cms.service.sys.HomeService;

import my.dao.pool.DBManager;
import my.util.MD5Util;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class HomeController  extends BaseController {
	
	@Autowired
	private HomeService homeService;
	
	@RequestMapping("/desktop")
	public String desktop() {
		return "cms/desktop";
	}
	
	
	@RequestMapping("/home")
	public String home(Model m,HttpSession session) {
		//当前登录用户
		SysUser user=(SysUser) session.getAttribute("user");
		//菜单树
		JSONArray ja1=homeService.home();
		m.addAttribute("user", user);
		ja1=JSONArray.fromObject(ja1.toString().replace("iconcls", "iconCls"));
		m.addAttribute("m1",ja1);//1级菜单
		return "cms/home";
	}


	/*根据1级菜单id，获取2级和3级菜单数据*/
	@ResponseBody
	@RequestMapping("/home/tree23")
	public JSONArray tree23(HttpSession session) {
		//当前登录用户
		SysUser user=(SysUser) session.getAttribute("user");
		String userId=user.getUserid();
		String id=param("id","");
		JSONArray jsonArray=new JSONArray();
		jsonArray=homeService.getTree23(userId, id);
		jsonArray=JSONArray.fromObject(jsonArray.toString().replace("iconcls", "iconCls"));
		return jsonArray; 
	}

	/**
	 * 修改个人密码
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/home/modifypassword")
	public JSONObject modifypassword(HttpSession session) {
		String oldpassword=param("oldpassword","");
		String newpassword=param("newpassword","");
		String newpassword2=param("newpassword2","");
		HashMap<String, Object> result = new HashMap<String, Object>();
		//当前登录用户
		SysUser user=(SysUser) session.getAttribute("user");
		result=homeService.modifypassword(oldpassword, newpassword, newpassword2, user);
		return JSONObject.fromObject(result);
	}
	/**
	 * 检查密码是否过于简单
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/home/validpassword")
	public JSONObject validpassword(HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		//当前登录用户
		SysUser user=(SysUser) session.getAttribute("user");
		if (user.getPassword().equals(MD5Util.string2MD5("123"))) {
			result.put("success", true);
			result.put("msg","请修改当前初始密码!");
		} else {
			result.put("success",false);
			result.put("msg","none");
		}
		return JSONObject.fromObject(result);
	}
}
