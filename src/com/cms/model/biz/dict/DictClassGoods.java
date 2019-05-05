/**
** create by id gen .
**/
package com.cms.model.biz.dict;

import java.util.Date;
import java.math.BigDecimal;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;
import my.base.BasePO;



@Table("DICT_CLASS_GOODS")
@View("DICT_CLASS_GOODS_V")
@PK({ "ID" })
public class DictClassGoods extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final DictClassGoods INSTANCE = new DictClassGoods();
	
	@Name
	@Column(value = "id", type = ColumnType.STRING)
	private String id ;
	
	@Column(value = "pid", type = ColumnType.STRING)
	private String pid ;
	
	@Column(value = "name", type = ColumnType.STRING)
	private String name ;
	
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	


	public String getId (){
		return id;
	}
		 
	public void setId (String id){
		this.id=id;
	}

	public String getPid (){
		return pid;
	}
		 
	public void setPid (String pid){
		this.pid=pid;
	}

	public String getName (){
		return name;
	}
		 
	public void setName (String name){
		this.name=name;
	}

	public String getSpell (){
		return spell;
	}
		 
	public void setSpell (String spell){
		this.spell=spell;
	}

	public DictClassGoods() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DictClassGoods(String id, String pid, String name, String spell) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.spell = spell;
	}

	public DictClassGoods(String pid, String name, String spell) {
		super();
		this.pid = pid;
		this.name = name;
		this.spell = spell;
	}

}
