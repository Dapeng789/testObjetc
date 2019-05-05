package com.cms.form;

import com.cms.model.biz.dict.DictClassGoods;
import com.cms.model.sys.SysRole;

import my.dao.annotation.Column;
import my.dao.annotation.Name;
import my.dao.mapping.ColumnType;

public class DictClassForm {
	private String id ;
	
	private String pid ;
	
	private String name ;
	
	private String spell ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}
	
	public DictClassGoods getEntityUpdate(){
		DictClassGoods dictClass=new DictClassGoods(id, pid, name, spell);
		return dictClass;
	}
	public DictClassGoods getEntityAdd(){
		DictClassGoods dictClass=new DictClassGoods(pid, name, spell);
		return dictClass;
	}
	
}
