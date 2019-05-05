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



@Table("GOODS_STOCK_IO")
@View("GOODS_STOCK_IO_V")
@PK({ "IOID" })
public class GoodsStockIo extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsStockIo INSTANCE = new GoodsStockIo();
	
	@Name
	@Column(value = "ioid", type = ColumnType.STRING)
	private String ioid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "iodate", type = ColumnType.DATE)
	private String iodate ;
	
	@Column(value = "billdtlid", type = ColumnType.STRING)
	private String billdtlid ;
	
	@Column(value = "billtype", type = ColumnType.STRING)
	private String billtype ;
	
	@Column(value = "billtable", type = ColumnType.STRING)
	private String billtable ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@Column(value = "stockid", type = ColumnType.STRING)
	private String stockid ;
	
	@Column(value = "storeid", type = ColumnType.STRING)
	private String storeid ;
	
	@Column(value = "lotid", type = ColumnType.STRING)
	private String lotid ;
	
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid ;
	
	@Column(value = "goodsdtlid", type = ColumnType.STRING)
	private String goodsdtlid ;
	
	@Column(value = "qty", type = ColumnType.NUMBER)
	private BigDecimal qty ;
	


	public String getIoid (){
		return ioid;
	}
		 
	public void setIoid (String ioid){
		this.ioid=ioid;
	}

	public String getIodate (){
		return iodate;
	}
		 
	public void setIodate (String iodate){
		this.iodate=iodate;
	}

	public String getBilldtlid (){
		return billdtlid;
	}
		 
	public void setBilldtlid (String billdtlid){
		this.billdtlid=billdtlid;
	}

	public String getBilltype (){
		return billtype;
	}
		 
	public void setBilltype (String billtype){
		this.billtype=billtype;
	}

	public String getBilltable (){
		return billtable;
	}
		 
	public void setBilltable (String billtable){
		this.billtable=billtable;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

	public String getStockid (){
		return stockid;
	}
		 
	public void setStockid (String stockid){
		this.stockid=stockid;
	}

	public String getStoreid (){
		return storeid;
	}
		 
	public void setStoreid (String storeid){
		this.storeid=storeid;
	}

	public String getLotid (){
		return lotid;
	}
		 
	public void setLotid (String lotid){
		this.lotid=lotid;
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

	public BigDecimal getQty (){
		return qty;
	}
		 
	public void setQty (BigDecimal qty){
		this.qty=qty;
	}

}
