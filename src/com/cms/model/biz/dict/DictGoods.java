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



@Table("DICT_GOODS")
@View("DICT_GOODS_V")
@PK({ "GOODSID" })
public class DictGoods extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final DictGoods INSTANCE = new DictGoods();
	
	@Name
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid ;
	
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname ;
	
	@Column(value = "commonname", type = ColumnType.STRING)
	private String commonname ;
	
	@Column(value = "classid", type = ColumnType.STRING)
	private String classid ;
	
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;
	
	@Column(value = "ishigh", type = ColumnType.STRING)
	private String ishigh ;
	
	@Column(value = "status", type = ColumnType.STRING)
	private String status ;
	
	@Column(value = "ischarged", type = ColumnType.STRING)
	private String ischarged ;
	
	@Column(value = "isstocked", type = ColumnType.STRING)
	private String isstocked ;
	
	@Column(value = "isaudited", type = ColumnType.STRING)
	private String isaudited ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@Column(value = "product", type = ColumnType.STRING)
	private String product ;
	
	@Column(value = "source", type = ColumnType.STRING)
	private String source ;
	


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

	public String getCommonname (){
		return commonname;
	}
		 
	public void setCommonname (String commonname){
		this.commonname=commonname;
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

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
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

	public String getProduct (){
		return product;
	}
		 
	public void setProduct (String product){
		this.product=product;
	}

	public String getSource (){
		return source;
	}
		 
	public void setSource (String source){
		this.source=source;
	}

	public DictGoods() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DictGoods(String goodsid, String goodsname, String commonname, String classid, String spell, 
			String ishigh, String status, String ischarged, String isstocked, String isaudited,
			String memo, String product, String source) {
		super();
		this.goodsid = goodsid;
		this.goodsname = goodsname;
		this.commonname = commonname;
		this.classid = classid;
		this.spell = spell;
		this.ishigh = ishigh;
		this.status = status;
		this.ischarged = ischarged;
		this.isstocked = isstocked;
		this.isaudited = isaudited;
		this.memo = memo;
		this.product = product;
		this.source = source;
	}

	public DictGoods(String goodsname, String commonname, String classid, String spell, 
			String ishigh, String status, String ischarged, String isstocked, String isaudited, String memo,
			String product, String source) {
		super();
		this.goodsname = goodsname;
		this.commonname = commonname;
		this.classid = classid;
		this.spell = spell;
		this.ishigh = ishigh;
		this.status = status;
		this.ischarged = ischarged;
		this.isstocked = isstocked;
		this.isaudited = isaudited;
		this.memo = memo;
		this.product = product;
		this.source = source;
	}

	
	
}
