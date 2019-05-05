package com.cms.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.cms.form.DictClassForm;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IBaseController<T> {
	
	//返回页面
	public String list(Model m) ;
	
	public JSONArray treegridData();
	
	public JSONArray gridData();
	
	//根据ID删除单个记录
	public JSONObject delete() ;
	
	//卡片式  新增和修改
	public JSONObject save(@ModelAttribute("form") DictClassForm form);
	
	//行编式  保存,包括:新增,删除,修改
	public JSONObject save(final @RequestParam("json") String json);
	
	
}
