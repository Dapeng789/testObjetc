package com.cms.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.model.sys.SysRole;
import com.cms.model.sys.SysUser;
import com.cms.service.sys.SysRoleMenuService;
import com.cms.service.sys.SysUserService;
import com.cms.util.biz.PageFactoryEasyUI;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class SysUserController extends BaseController{
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@RequestMapping("user/list")
	public String desktop(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/user/list";
	}
	
	@ResponseBody
	@RequestMapping("user/gridData")
	public GridDataModel<SysUser> gridData() {
		String realname = param("realname", "");
		String roleid = param("roleid", "");
		GridDataModel<SysUser> gridDataModel=sysUserService.GetGridData(realname, roleid);
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("user/gridSave")
	public JSONObject gridSave(final @RequestParam("json") String json) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result=sysUserService.save(json);
		return JSONObject.fromObject(result);	
		
	}
	
	@ResponseBody
	@RequestMapping("user/getRoleList")
	public JSONArray getRoleList() {
		List<SysRole> list=sysRoleMenuService.findAllRolesList();
		JSONArray jsonData=JSONArray.fromObject(list);
		return jsonData;
	}
}
