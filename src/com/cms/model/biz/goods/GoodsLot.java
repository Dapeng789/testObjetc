/**
** create by code gen .
**/
package com.cms.model.biz.goods;

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



@Table("GOODS_LOT")
@View("GOODS_LOT_V")
@PK({ "LOTID" })
public class GoodsLot extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsLot INSTANCE = new GoodsLot();
	
	@Name
	@Column(value = "lotid", type = ColumnType.STRING)
	private String lotid ;
	
	@Column(value = "billdtlid", type = ColumnType.STRING)
	private String billdtlid ;
	
	@Column(value = "billtable", type = ColumnType.STRING)
	private String billtable ;
	
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid ;
	
	@Column(value = "goodsdtlid", type = ColumnType.STRING)
	private String goodsdtlid ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "createdate", type = ColumnType.DATE)
	private String createdate ;
	
	@Column(value = "batchno", type = ColumnType.STRING)
	private String batchno ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "prodate", type = ColumnType.DATE)
	private String prodate ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "expiredate", type = ColumnType.DATE)
	private String expiredate ;
	


	public String getLotid (){
		return lotid;
	}
		 
	public void setLotid (String lotid){
		this.lotid=lotid;
	}

	public String getBilldtlid (){
		return billdtlid;
	}
		 
	public void setBilldtlid (String billdtlid){
		this.billdtlid=billdtlid;
	}

	public String getBilltable (){
		return billtable;
	}
		 
	public void setBilltable (String billtable){
		this.billtable=billtable;
	}

	public String getGoodsid (){
		return goodsid;
	}
		 
	public void setGoodsid (String goodsid){
		this.goodsid=goodsid;
	}

	public String getGoodsdtlid (){
		return goodsdtlid;
	}
		 
	public void setGoodsdtlid (String goodsdtlid){
		this.goodsdtlid=goodsdtlid;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

	public String getCreatedate (){
		return createdate;
	}
		 
	public void setCreatedate (String createdate){
		this.createdate=createdate;
	}

	

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getProdate (){
		return prodate;
	}
		 
	public void setProdate (String prodate){
		this.prodate=prodate;
	}

	public String getExpiredate (){
		return expiredate;
	}
		 
	public void setExpiredate (String expiredate){
		this.expiredate=expiredate;
	}

}
