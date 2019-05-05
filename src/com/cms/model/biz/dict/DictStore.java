/**
** create by code gen .
**/
package com.cms.model.biz.dict;

import java.util.Date;
import java.math.BigDecimal;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;
import my.base.BasePO;



@Table("DICT_STORE")
@View("DICT_STORE_V")
@PK({ "STOREID" })
public class DictStore extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final DictStore INSTANCE = new DictStore();
	
	@Name
	@Column(value = "storeid", type = ColumnType.STRING)
	private String storeid ;
	
	@Column(value = "storename", type = ColumnType.STRING)
	private String storename ;
	


	public String getStoreid (){
		return storeid;
	}
		 
	public void setStoreid (String storeid){
		this.storeid=storeid;
	}

	public String getStorename (){
		return storename;
	}
		 
	public void setStorename (String storename){
		this.storename=storename;
	}

}
