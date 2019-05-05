package com.cms.service.sys;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.cms.form.SysMenuForm;
import com.cms.model.sys.SysMenu;
import com.cms.model.sys.SysRoleMenu;
import com.cms.model.sys.SysUserButtonRoleV;

import my.dao.pool.DBManager;
import my.dao.utils.Record;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SysMenuService {
	
	
	public JSONArray findAll(){
		JSONArray ja1=new JSONArray();//一级菜单JSONArray
		List<SysMenu> list1=SysMenu.INSTANCE.query("( pid = '' or pid is null ) ");//一级菜单
		for (SysMenu menu1 : list1) {
			String pid=menu1.getId();
			JSONObject jo1=JSONObject.fromObject(menu1);
			List<SysMenu> list2=SysMenu.INSTANCE.query(" pid = ? ",pid);//二级菜单
			if(list2.size()==0){
				ja1.add(jo1);
				continue;
			};
			JSONArray ja2=new JSONArray();//二级菜单JSONArray
			for (SysMenu meun2 : list2) {
				String pid2=meun2.getId();
				JSONObject jo2=JSONObject.fromObject(meun2);
				List<SysMenu> list3=SysMenu.INSTANCE.query(" pid = ? ",pid2);//三级菜单
				if(list3.size()==0){
					ja2.add(jo2);
					continue;
				};
				JSONArray ja3=new JSONArray();//三级菜单JSONArray
				for (SysMenu meun3 : list3) {
					String pid3=meun3.getId();
					JSONObject jo3=JSONObject.fromObject(meun3);
					List<SysMenu> list4=SysMenu.INSTANCE.query(" pid = ? ",pid3);//四级按钮
					if(list4.size()==0){
						ja3.add(jo3);
						continue;
					};
					jo3.put("children", JSONArray.fromObject(list4));//四级按钮JSONObject
					ja3.add(jo3);
				}
				if(ja3.size()>0){jo2.put("children", ja3);}
				ja2.add(jo2);
			}
			if(ja2.size()>0){jo1.put("children", ja2);};
			ja1.add(jo1);
		}
		return ja1;
	}
	
	
	public JSONArray find2Level(){
		JSONArray jap=new JSONArray();//一级菜单JSONArray
		List<SysMenu> listp=SysMenu.INSTANCE.query(" ( pid = '' or pid is null ) ");//一级菜单
		for (SysMenu menup : listp) {
			String pid=menup.getId();
			JSONObject jop=JSONObject.fromObject(menup);
			List<SysMenu> listm=SysMenu.INSTANCE.query(" pid = ? ",pid);//二级菜单
			if(listm.size()==0){
				jap.add(jop);
				continue;
			};
			JSONArray jam=new JSONArray();//二级菜单JSONArray
			for (SysMenu meunm : listm) {
				JSONObject job=JSONObject.fromObject(meunm);
				jam.add(job);
			}
			if(jam.size()>0){jop.put("children", jam);};
			jap.add(jop);
		}
		return jap;
	}
	
	public JSONArray find3Level(){
		JSONArray ja1=new JSONArray();//一级菜单JSONArray
		List<SysMenu> list1=SysMenu.INSTANCE.query(" ( pid = '' or pid is null ) ");//一级菜单
		for (SysMenu menu1 : list1) {
			String pid=menu1.getId();
			JSONObject jo1=JSONObject.fromObject(menu1);
			List<SysMenu> list2=SysMenu.INSTANCE.query(" pid = ? ",pid);//二级菜单
			if(list2.size()==0){
				ja1.add(jo1);
				continue;
			};
			JSONArray ja2=new JSONArray();//二级菜单JSONArray
			for (SysMenu meun2 : list2) {
				String pid2=meun2.getId();
				JSONObject jo2=JSONObject.fromObject(meun2);
				List<SysMenu> list3=SysMenu.INSTANCE.query(" pid = ? ",pid2);//三级菜单
				if(list3.size()==0){
					ja2.add(jo2);
					continue;
				};
				JSONArray ja3=new JSONArray();//三级菜单JSONArray
				for (SysMenu meun3 : list3) {
					JSONObject jo3=JSONObject.fromObject(meun3);
					ja3.add(jo3);
				}
				if(ja3.size()>0){jo2.put("children", ja3);}
				ja2.add(jo2);
			}
			if(ja2.size()>0){jo1.put("children", ja2);};
			ja1.add(jo1);
		}
		return ja1;
	}
	
	public HashMap<String, Object> delete(String id){
		HashMap<String, Object> result = new HashMap<String, Object>();
		//判断如果有子项，则不能删除
		List<SysMenu> list=SysMenu.INSTANCE.query("pid=?", id);
		if(list.size()>0){
			result.put("success", false);
			result.put("msg", "该组件有子项，不能删除！");
		}else{
			try {
				//删除sys_menu
				SysMenu sysMenu=SysMenu.INSTANCE.queryOne("id=?", id);
				sysMenu.delete();
				//同时删除已分配的权限sys_role_menu
				List<SysRoleMenu> sysRoleMenus=SysRoleMenu.INSTANCE.query("menuid=?", id);
				for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
					sysRoleMenu.delete();
				}
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
		return result;
	}
	
	public HashMap<String, Object> save(String id,SysMenuForm form){
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			if (!(null==id||id.equals(""))){
				//更新
				SysMenu sysMenu=SysMenu.INSTANCE.queryOne("id=?", id);
				BeanUtils.copyProperties(sysMenu, form);
				sysMenu.setIconcls(form.getIconCls());
				sysMenu.update();
			}else{
				//新增
				SysMenu sysMenu=new SysMenu();
				BeanUtils.copyProperties(sysMenu, form);
				sysMenu.setIconcls(form.getIconCls());
				/*
				 * 根据pid，计算id，规则是：
				 * pid为空，则为1级菜单
				 * pid长度为3，则为2级菜单
				 * pid长度为6，则为3级菜单
				 * pid长度为9，则为按钮
				*/		
				String pid=(form.getPid()==null)?"":form.getPid();
				Record record=SysMenu.INSTANCE.viewHelper().queryOne("select max(id) as id from sys_menu where pid=? " ,pid);
				String idMax=record.get("id")==null?idMax="000":record.get("id").toString();
				if(pid.length()==0){
					String ids=String.format("%03d",Integer.valueOf(idMax)+1);//补零
					sysMenu.setId(ids);
					sysMenu.setCategory("1级菜单");
				}else if(pid.length()==3){
					String ids=pid+String.format("%03d",Integer.valueOf(idMax.substring(idMax.length()-2, idMax.length()))+1);//补零
					sysMenu.setId(ids);
					sysMenu.setCategory("2级菜单");
				}else if(pid.length()==6){
					String ids=pid+String.format("%03d",Integer.valueOf(idMax.substring(idMax.length()-2, idMax.length()))+1);//补零
					sysMenu.setId(ids);;
					sysMenu.setCategory("3级菜单");
				}else if(pid.length()==9){
					String ids=pid+String.format("%03d",Integer.valueOf(idMax.substring(idMax.length()-2, idMax.length()))+1);//补零
					sysMenu.setId(ids);
					sysMenu.setCategory("按钮");
				}else{
					
				}
				sysMenu.insert();
			}
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("msg", "操作失败！"+e.getMessage());
			result.put("success", false);
			return result;
		}
		DBManager.commitAll();
		result.put("msg", "操作成功！");
		result.put("success", true);
		return result;
	}
	
	public List<SysUserButtonRoleV> getButtons(String userid,String menuid,String divid){
		return SysUserButtonRoleV.INSTANCE.query("userid=? and pid=? and divid=?  order by id ", userid,menuid,divid);
	}
}
