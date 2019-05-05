package com.cms.service.sys;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.model.sys.SysMenu;
import com.cms.model.sys.SysUser;
import com.cms.model.sys.SysUserMenuRoleV;

import my.dao.pool.DBManager;
import my.util.MD5Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class HomeService {
	
	public HashMap<String, Object> modifypassword(String oldpassword,String newpassword,String newpassword2,SysUser user){
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (!newpassword.equals(newpassword2)) {
			result.put("success", false);
			result.put("msg","两次密码输入不一致!");
		}else if (!user.getPassword().equals(MD5Util.string2MD5(oldpassword))) {
			result.put("success", false);
			result.put("msg","旧密码输入错误!");
		}else{
			user.updateField("password", MD5Util.string2MD5(newpassword));// 加密
			result.put("success", true);
			result.put("msg","修改成功!");
		}
		DBManager.commitAll();
		return result;
	}
	
	public JSONArray home(){
		JSONArray ja1=new JSONArray();
		List<SysMenu> m1s=SysMenu.INSTANCE.query(" category='1级菜单' ");
		for (SysMenu m1 : m1s) {
			JSONObject jo1=JSONObject.fromObject(m1);
			List<SysMenu> m2s=SysMenu.INSTANCE.query(" pid=? order by id ",m1.getId());
			JSONArray ja2=new JSONArray();
			for (SysMenu m2 : m2s) {
				JSONObject jo2=JSONObject.fromObject(m2);
				List<SysMenu> m3s=SysMenu.INSTANCE.query(" pid=? order by id ",m2.getId());
				if(m3s.size()>0){jo2.put("children", JSONArray.fromObject(m3s));}
				ja2.add(jo2);
			}
			if(ja2.size()>0){jo1.put("children", ja2);}
			ja1.add(jo1);
		}
		return ja1;
	}
	
	public JSONArray getTree23(String userId,String id){
		JSONArray jsonArray=new JSONArray();
		//2级子菜单
		List<SysUserMenuRoleV> menus=SysUserMenuRoleV.INSTANCE.query(" userid=? and  pid=? order by id ", userId,id);
		for (SysUserMenuRoleV sysUserMenuRoleV : menus) {
			//3级子菜单
			List<SysUserMenuRoleV> children=SysUserMenuRoleV.INSTANCE.query(" userid=? and pid=? order by id ", userId,sysUserMenuRoleV.getId());
			JSONObject jo=JSONObject.fromObject(sysUserMenuRoleV);
			jo.put("children", children);
			jo.put("state", "open");
			jsonArray.add(jo);
		}
		return jsonArray;
	}
}
