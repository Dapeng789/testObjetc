package com.cms.service.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.form.RoleForm;
import com.cms.model.sys.SysMenu;
import com.cms.model.sys.SysRole;
import com.cms.model.sys.SysRoleMenu;
import com.cms.model.sys.SysUser;

import my.dao.pool.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SysRoleMenuService {
	/**
	 * 返回权限列表(全部)
	 * @return
	 */
	public JSONObject findAllRoles(){
		List<SysRole> list=new ArrayList<SysRole>();
		list=SysRole.INSTANCE.query("order by roleid");
		JSONArray jsonData=new JSONArray();
		for (SysRole sysRole : list) {
			JSONObject jo = new JSONObject() ;
			jo.put("roleid", sysRole.getRoleid());
			jo.put("rolename", sysRole.getRolename());
			jsonData.add(jo);
		}
		JSONObject jsonobj = new JSONObject() ;
		jsonobj.put("total",list.size()) ;  
		jsonobj.put("rows",jsonData) ; 
		return jsonobj;
	}
	/**
	 * 返回权限列表(全部)
	 * @return
	 */
	public List<SysRole> findAllRolesList(){
		return SysRole.INSTANCE.query("");
	}
	/**
	 * 保存权限,新增权限和更新权限公用此方法
	 * @param roleid
	 * @return
	 */
	public HashMap<String, Object> update(RoleForm form){
		HashMap<String, Object> result = new HashMap<String, Object>();
		Integer roleid=form.getRoleid(); 
		try {
			if (!(null==roleid||roleid.equals(""))){
				//更新
				SysRole role=form.getEntityUpdate(); 
				role.update();
			}else{
				//新增
				roleid=SysRole.INSTANCE.getNextRoleId();
				SysRole role=form.getEntityAdd();
				role.setRoleid(roleid);
				//插入sys_role（总单）
				SysRole role1=new SysRole(role.getRoleid(), role.getRolename());
				role1.insert();
				//插入sys_role_menu（明细）
				List<SysMenu> list=SysMenu.INSTANCE.query("");//插入所有菜单并初始化
				for (SysMenu sysMenu : list) {
					SysRoleMenu sysRoleMenu=new SysRoleMenu();
					sysRoleMenu.setRoleid(roleid); 
					sysRoleMenu.setMenuid(sysMenu.getId());
					sysRoleMenu.setEnabled(0);
					sysRoleMenu.insert();
				}
			}
			DBManager.commitAll();
			result.put("success", true);
			result.put("msg","保存成功!");
		} catch (NumberFormatException e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg","保存失败!");
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param roleid
	 * @return
	 */
	public Map<String,Object> delete(String roleid){
		Map<String,Object> result = new HashMap<String,Object>();
		String errorMsg="删除失败!";
		List<SysUser> sysUsers=SysUser.INSTANCE.query("roleid=?", roleid);
		if (sysUsers.size()>0){
			errorMsg+="权限已被使用,不能删除!";
			result.put("success", false);
			result.put("msg", errorMsg);
		}
		else if(roleid=="-1"){
			errorMsg+="管理员权限不能删除!";
			result.put("success", false);
			result.put("msg", errorMsg);
//		}else if(Integer.valueOf(roleid) <user.getRoleid()){
//			errorMsg+="你的权限低于要删除的权限,不能删除!";
		}
		else{
			try {
				//先删除sys_role_menu（明细）
				List<SysRoleMenu> list=SysRoleMenu.INSTANCE.query("roleid=?", roleid);
				for (SysRoleMenu sysRoleMenu : list) {
					sysRoleMenu.delete();
				}
				//再删除sys_role（总单）
				SysRole sysRole=SysRole.INSTANCE.queryOne("roleid=?", roleid);
				sysRole.delete();
				
				DBManager.commitAll();
				result.put("success", true);
				result.put("msg", "删除成功!");
			} catch (Exception e) {
				DBManager.rollbackAll();
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", errorMsg);
			}
		}
		
		return result;
	}
	
	
	/**
	 * 获取当前roleid的roleMenu
	 * @param roleid
	 * @return
	 */
	
	public JSONArray getRoleMenuById(String roleid){
		/*
		 * 在刷新rolemenu之前,核对是否与sysMenu中的菜单一致
		 * 因为菜单如果删除,会同时删除sysRoleMenu中的数据,但是增加菜单则不会增加sysRoleMenu中的数据
		 * 所以一般情况下,不一致的情况sysRoleMenu只会比sysMenu中多,而不会比sysMenu中少
		 */
		List<SysMenu> sysMenus=SysMenu.INSTANCE.query("");
		for (SysMenu sysMenu : sysMenus) {
			SysRoleMenu sysRoleMenu=SysRoleMenu.INSTANCE.queryOne("menuid=? and roleid=?", sysMenu.getId(),roleid);
			if (sysRoleMenu==null){
				try {
					SysRoleMenu srm=new SysRoleMenu();
					srm.setRoleid(Integer.valueOf(roleid));
					srm.setMenuid(sysMenu.getId());
					srm.setEnabled(0);
					srm.insert();
					DBManager.commitAll();
				} catch (Exception e) {
					DBManager.rollbackAll();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//刷新rolemenu数据
		JSONArray jam1=new JSONArray();
		List<SysRoleMenu> m1=SysRoleMenu.INSTANCE.query("roleid=? and (pid = '' or pid is null)", roleid);//一级菜单
		for (int i = 0; i < m1.size(); i++) {
			JSONArray jam2=new JSONArray();
			List<SysRoleMenu> m2=SysRoleMenu.INSTANCE.query("roleid=? and pid=?",roleid, m1.get(i).getMenuid());//二级菜单
			for (int j = 0; j < m2.size(); j++) {
				JSONArray jam3=new JSONArray();
				List<SysRoleMenu> m3=SysRoleMenu.INSTANCE.query("roleid=? and pid=?",roleid, m2.get(j).getMenuid());//三级菜单
				for (int k = 0; k < m3.size(); k++) {
					JSONArray jab=new JSONArray();
					List<SysRoleMenu> mb=SysRoleMenu.INSTANCE.query("roleid=? and pid=?",roleid, m3.get(k).getMenuid());//四级按钮
					for (SysRoleMenu sysRoleMenu : mb) {
						JSONObject job=JSONObject.fromObject(sysRoleMenu);
						job.put("checked",sysRoleMenu.getEnabled()==1?true:false);
						jab.add(job);
					}
					JSONObject jo3=JSONObject.fromObject(m3.get(k));
					jo3.put("checked", m3.get(k).getEnabled()==1?true:false);
					jo3.put("children", JSONArray.fromObject(jab));
					jam3.add(jo3);
				}
				JSONObject jo2=JSONObject.fromObject(m2.get(j));
				jo2.put("checked", m2.get(j).getEnabled()==1?true:false);
				jo2.put("children", JSONArray.fromObject(jam3));
				jam2.add(jo2);
			}
			JSONObject jo1=JSONObject.fromObject(m1.get(i));
			jo1.put("checked", m1.get(i).getEnabled()==1?true:false);
			jo1.put("children", JSONArray.fromObject(jam2));
			jam1.add(jo1);
		}
		return jam1;
	}
	/**
	 * 更新当前roleid的roleMenu
	 * @return
	 */
	public Map<String,Object> updateRoleMenu(String menuids,String roleid){
		Map<String,Object> result = new HashMap<String,Object>();
		String errorMsg="更新权限失败！";
		int row=0;
		List<String> list = Arrays.asList(menuids.split(","));
		
		//初始化，将roleid先所有enabled设置成0
		List<SysRoleMenu> list_srm=SysRoleMenu.INSTANCE.query("roleid=?", roleid);
		for (SysRoleMenu srm : list_srm) {
			srm.setEnabled(0);
			row=srm.update();
			if(row<=0){
				result.put("success", false);
				result.put("errorMsg", errorMsg+"初始化roleMenu失败!");
				return result;
			}
		}
		
		//根据选择设置enabled
		for (int i = 0; i < list.size(); i++) {
			String menuid=list.get(i);
			//如果新增菜单,在权限菜单中没有的,需要添加进来,并初始化
//			if (roleMenuService.getOneRoleMenu(menuid, Integer.valueOf(roleid))==null) {
//				roleMenuService.insertRoleMenu(menuid, Integer.valueOf(roleid));
//			}
			SysRoleMenu srm=SysRoleMenu.INSTANCE.queryOne("roleid=? and menuid=?", roleid,menuid);
			if (srm==null){
				srm.setMenuid(menuid);
				srm.setRoleid(Integer.valueOf(roleid));
				srm.setEnabled(0);
				srm.insert();
			}
			//更新标志
//			row=roleMenuService.updateRoleMenu(Integer.valueOf(roleid), menuid, 1);
			SysRoleMenu sysRoleMenu=SysRoleMenu.INSTANCE.queryOne("roleid=? and menuid=?", roleid,menuid);
			sysRoleMenu.setEnabled(1);
			row=sysRoleMenu.update();
			if(row<=0){
				result.put("success", false);
				result.put("errorMsg", errorMsg+"更新roleMenu失败!");
				return result;
			}
		}
		DBManager.commitAll();
		result.put("success", true);
		return result;
	}
}
