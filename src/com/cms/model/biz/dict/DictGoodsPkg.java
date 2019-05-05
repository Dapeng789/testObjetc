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



@Table("DICT_GOODS_PKG")
@View("DICT_GOODS_PKG_V")
@PK({ "GOODSDTLID" })
public class DictGoodsPkg extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final DictGoodsPkg INSTANCE = new DictGoodsPkg();
	
	@Name
	@Column(value = "goodsdtlid", type = ColumnType.STRING)
	private String goodsdtlid ;
	
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid ;
	
	@Column(value = "sortno", type = ColumnType.STRING)
	private String sortno ;
	
	@Column(value = "unitid", type = ColumnType.STRING)
	private String unitid ;
	
	@Column(value = "ratio", type = ColumnType.NUMBER)
	private BigDecimal ratio ;
	
	@Column(value = "status", type = ColumnType.STRING)
	private String status ;
	
	@Column(value = "spec", type = ColumnType.STRING)
	private String spec ;
	
	@ReadOnly
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname ;
	
	@ReadOnly
	@Column(value = "customname", type = ColumnType.STRING)
	private String customname ;
	
	@ReadOnly
	@Column(value = "classid", type = ColumnType.STRING)
	private String classid ;
	
	@ReadOnly
	@Column(value = "classname", type = ColumnType.STRING)
	private String classname ;
	
	@ReadOnly
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@ReadOnly
	@Column(value = "ishigh", type = ColumnType.STRING)
	private String ishigh ;
	
	@ReadOnly
	@Column(value = "ischarged", type = ColumnType.STRING)
	private String ischarged ;
	
	@ReadOnly
	@Column(value = "isstocked", type = ColumnType.STRING)
	private String isstocked ;
	
	@ReadOnly
	@Column(value = "isaudited", type = ColumnType.STRING)
	private String isaudited ;
	
	@ReadOnly
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@ReadOnly
	@Column(value = "factoryid", type = ColumnType.STRING)
	private String factoryid ;
	
	@ReadOnly
	@Column(value = "prodareaid", type = ColumnType.STRING)
	private String prodareaid ;
	
	@ReadOnly
	@Column(value = "statusdtl", type = ColumnType.STRING)
	private String statusdtl ;
	
	@ReadOnly
	@Column(value = "unitname", type = ColumnType.STRING)
	private String unitname ;
	


	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getGoodsdtlid (){
		return goodsdtlid;
	}
		 
	public void setGoodsdtlid (String goodsdtlid){
		this.goodsdtlid=goodsdtlid;
	}

	public String getGoodsid (){
		return goodsid;
	}
		 
	public void setGoodsid (String goodsid){
		this.goodsid=goodsid;
	}

	public String getSortno (){
		return sortno;
	}
		 
	public void setSortno (String sortno){
		this.sortno=sortno;
	}

	public String getUnitid (){
		return unitid;
	}
		 
	public void setUnitid (String unitid){
		this.unitid=unitid;
	}

	public BigDecimal getRatio (){
		return ratio;
	}
		 
	public void setRatio (BigDecimal ratio){
		this.ratio=ratio;
	}

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
	}

	public String getSpec (){
		return spec;
	}
		 
	public void setSpec (String spec){
		this.spec=spec;
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

	public String getSpell (){
		return spell;
	}
		 
	public void setSpell (String spell){
		this.spell=spell;
	}

	public String getIshigh (){
		return ishigh;
	}
		 
	public void setIshigh (String ishigh){
		this.ishigh=ishigh;
	}

	public String getIscharged (){
		return ischarged;
	}
		 
	public void setIscharged (String ischarged){
		this.ischarged=ischarged;
	}

	public String getIsstocked (){
		return isstocked;
	}
		 
	public void setIsstocked (String isstocked){
		this.isstocked=isstocked;
	}

	public String getIsaudited (){
		return isaudited;
	}
		 
	public void setIsaudited (String isaudited){
		this.isaudited=isaudited;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

	public String getFactoryid (){
		return factoryid;
	}
		 
	public void setFactoryid (String factoryid){
		this.factoryid=factoryid;
	}

	public String getProdareaid (){
		return prodareaid;
	}
		 
	public void setProdareaid (String prodareaid){
		this.prodareaid=prodareaid;
	}

	public String getStatusdtl (){
		return statusdtl;
	}
		 
	public void setStatusdtl (String statusdtl){
		this.statusdtl=statusdtl;
	}

	public String getUnitname (){
		return unitname;
	}
		 
	public void setUnitname (String unitname){
		this.unitname=unitname;
	}

}
