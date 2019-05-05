package com.cms.controller.sys;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.form.RoleForm;
import com.cms.form.RoleMenuForm;
import com.cms.model.sys.SysMenu;
import com.cms.model.sys.SysRole;
import com.cms.model.sys.SysRoleMenu;
import com.cms.model.sys.SysUser;
import com.cms.service.sys.SysRoleMenuService;

import my.dao.pool.DBManager;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class SysRoleMenuController extends BaseController{
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public RoleForm getForm(){
		return new RoleForm();
	}
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("formRoleMenu")
	public List<RoleMenuForm> getFormRoleMenu(){
		List<RoleMenuForm> list =new ArrayList<RoleMenuForm>();
		return list;
	}
	
	@RequestMapping("rolemenu/list")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/rolemenu/list";
	}
	
	/**
	 * 返回权限列表(全部)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rolemenu/findRoles")
	public JSONObject findRoles(){
		JSONObject jsonobj = sysRoleMenuService.findAllRoles() ;
		return jsonobj;
	}
	
	/**
	 * 保存权限,新增权限和更新权限公用此方法
	 * @param roleid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rolemenu/saveRole")
	public JSONObject update(@ModelAttribute("form") RoleForm form){
		HashMap<String, Object> result = new HashMap<String, Object>();
		result=sysRoleMenuService.update(form);
		return JSONObject.fromObject(result);
	}	

	/**
	 * 删除
	 * @param roleid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rolemenu/delete")
	public JSONObject delete(HttpSession session){
		String roleid=param("roleid", "");
		Map<String,Object> result =sysRoleMenuService.delete(roleid);
		return JSONObject.fromObject(result);
	}
	/**
	 * 获取当前roleid的roleMenu
	 * @param roleid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rolemenu/getRoleMenu")
	public JSONArray getRoleMenu(){
		String roleid=param("roleid", "");
		JSONArray jam1=sysRoleMenuService.getRoleMenuById(roleid);
		return JSONArray.fromObject(jam1.toString().replace("iconcls", "iconCls"));
	}
	/**
	 * 更新当前roleid的roleMenu
	 * @return
	 */
	@ResponseBody
	@RequestMapping("rolemenu/updateRoleMenu")
	public JSONObject updateRoleMenu(HttpSession session){
		Map<String,Object> result = new HashMap<String,Object>();
		String menuids=param("menuids", "");
		String roleid=param("roleid", "");
		//当前登录用户
//		SysUser user=(SysUser) session.getAttribute("user");
//		if(user.getRoleid()>Integer.valueOf(roleid)){
//			result.put("success", false);
//			result.put("errorMsg", "低权限级别不能更改高级别权限!");
//			return JSONObject.fromObject(result);
//		}
		result = sysRoleMenuService.updateRoleMenu(menuids, roleid);
		return JSONObject.fromObject(result);
	}
	
}
