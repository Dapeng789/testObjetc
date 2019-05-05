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



@Table("GOODS_STOCK_SUM_V")
@View("GOODS_STOCK_SUM_V")
@PK({  })
public class GoodsStockSumV extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsStockSumV INSTANCE = new GoodsStockSumV();
	
	
	@Column(value = "storeid", type = ColumnType.STRING)
	private String storeid ;
	
	@Column(value = "storename", type = ColumnType.STRING)
	private String storename ;
	
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid ;
	
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname ;
	
	@Column(value = "customname", type = ColumnType.STRING)
	private String customname ;
	
	@Column(value = "classid", type = ColumnType.STRING)
	private String classid ;
	
	@Column(value = "classname", type = ColumnType.STRING)
	private String classname ;
	
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@Column(value = "goodsstatus", type = ColumnType.STRING)
	private String goodsstatus ;
	
	@Column(value = "factoryid", type = ColumnType.NUMBER)
	private BigDecimal factoryid ;
	
	@Column(value = "factoryname", type = ColumnType.STRING)
	private String factoryname ;
	
	@Column(value = "prodareaid", type = ColumnType.NUMBER)
	private BigDecimal prodareaid ;
	
	@Column(value = "prodareaname", type = ColumnType.STRING)
	private String prodareaname ;
	
	@Column(value = "goodsdtlid", type = ColumnType.STRING)
	private String goodsdtlid ;
	
	@Column(value = "unitid", type = ColumnType.NUMBER)
	private BigDecimal unitid ;
	
	@Column(value = "unitname", type = ColumnType.STRING)
	private String unitname ;
	
	@Column(value = "spec", type = ColumnType.STRING)
	private String spec ;
	
	@Column(value = "ratio", type = ColumnType.NUMBER)
	private BigDecimal ratio ;
	
	@Column(value = "posid", type = ColumnType.STRING)
	private String posid ;
	
	@Column(value = "qty", type = ColumnType.NUMBER)
	private BigDecimal qty ;
	
	@Column(value = "status", type = ColumnType.STRING)
	private String status ;
	



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

	public String getGoodsid (){
		return goodsid;
	}
		 
	public void setGoodsid (String goodsid){
		this.goodsid=goodsid;
	}

	public String getGoodsname (){
		return goodsname;
	}
		 
	public void setGoodsname (String goodsname){
		this.goodsname=goodsname;
	}

	public String getCustomname (){
		return customname;
	}
		 
	public void setCustomname (String customname){
		this.customname=customname;
	}

	public String getClassid (){
		return classid;
	}
		 
	public void setClassid (String classid){
		this.classid=classid;
	}

	public String getClassname (){
		return classname;
	}
		 
	public void setClassname (String classname){
		this.classname=classname;
	}

	public String getSpell (){
		return spell;
	}
		 
	public void setSpell (String spell){
		this.spell=spell;
	}

	public String getGoodsstatus (){
		return goodsstatus;
	}
		 
	public void setGoodsstatus (String goodsstatus){
		this.goodsstatus=goodsstatus;
	}

	public BigDecimal getFactoryid (){
		return factoryid;
	}
		 
	public void setFactoryid (BigDecimal factoryid){
		this.factoryid=factoryid;
	}

	public String getFactoryname (){
		return factoryname;
	}
		 
	public void setFactoryname (String factoryname){
		this.factoryname=factoryname;
	}

	public BigDecimal getProdareaid (){
		return prodareaid;
	}
		 
	public void setProdareaid (BigDecimal prodareaid){
		this.prodareaid=prodareaid;
	}

	public String getProdareaname (){
		return prodareaname;
	}
		 
	public void setProdareaname (String prodareaname){
		this.prodareaname=prodareaname;
	}

	public String getGoodsdtlid (){
		return goodsdtlid;
	}
		 
	public void setGoodsdtlid (String goodsdtlid){
		this.goodsdtlid=goodsdtlid;
	}

	public BigDecimal getUnitid (){
		return unitid;
	}
		 
	public void setUnitid (BigDecimal unitid){
		this.unitid=unitid;
	}

	public String getUnitname (){
		return unitname;
	}
		 
	public void setUnitname (String unitname){
		this.unitname=unitname;
	}

	public String getSpec (){
		return spec;
	}
		 
	public void setSpec (String spec){
		this.spec=spec;
	}

	public BigDecimal getRatio (){
		return ratio;
	}
		 
	public void setRatio (BigDecimal ratio){
		this.ratio=ratio;
	}

	public String getPosid (){
		return posid;
	}
		 
	public void setPosid (String posid){
		this.posid=posid;
	}

	public BigDecimal getQty (){
		return qty;
	}
		 
	public void setQty (BigDecimal qty){
		this.qty=qty;
	}

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
	}

}
