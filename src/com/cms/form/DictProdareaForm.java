package com.cms.form;

import com.cms.model.biz.dict.DictProdarea;


public class DictProdareaForm {
	private String id ;
	
	private String name ;
	
	private String spell ;

	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public DictProdarea getEntityUpdate(){
		DictProdarea dictProdarea=new DictProdarea(id,  name, spell);
		return dictProdarea;
	}
	public DictProdarea getEntityAdd(){
		DictProdarea dictProdarea=new DictProdarea( name, spell);
		return dictProdarea;
	}
	
}
