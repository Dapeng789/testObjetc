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



@Table("GOODS_STOCK_POOL")
@View("GOODS_STOCK_POOL_V")
@PK({ "DTLID","TABLENAME" })
public class GoodsStockPool extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsStockPool INSTANCE = new GoodsStockPool();
	
	@Column(value = "tablename", type = ColumnType.STRING)
	private String tablename ;
	
	@Column(value = "dtlid", type = ColumnType.NUMBER)
	private String dtlid ;
	
	@Column(value = "stockid", type = ColumnType.NUMBER)
	private String stockid ;
	
	@Column(value = "goodsid", type = ColumnType.NUMBER)
	private String goodsid ;
	
	@Column(value = "goodsdtlid", type = ColumnType.NUMBER)
	private String goodsdtlid ;
	
	@ReadOnly
	@Column(value = "qty", type = ColumnType.NUMBER)
	private BigDecimal qty ;
	


	public String getTablename (){
		return tablename;
	}
		 
	public void setTablename (String tablename){
		this.tablename=tablename;
	}

	

	public String getDtlid() {
		return dtlid;
	}

	public void setDtlid(String dtlid) {
		this.dtlid = dtlid;
	}

	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsdtlid() {
		return goodsdtlid;
	}

	public void setGoodsdtlid(String goodsdtlid) {
		this.goodsdtlid = goodsdtlid;
	}

	public BigDecimal getQty (){
		return qty;
	}
		 
	public void setQty (BigDecimal qty){
		this.qty=qty;
	}

}
