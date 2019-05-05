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



@Table("GOODS_BILL_IN_DTL")
@View("GOODS_BILL_IN_DTL_V")
@PK({ "DTLID" })
public class GoodsBillInDtl extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsBillInDtl INSTANCE = new GoodsBillInDtl();
	
	@Name
	@Column(value = "dtlid", type = ColumnType.STRING)
	private String dtlid ;
	
	@Column(value = "docid", type = ColumnType.STRING)
	private String docid ;
	
	@Column(value = "rowno", type = ColumnType.STRING)
	private String rowno ;
	
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid ;
	
	@Column(value = "goodsdtlid", type = ColumnType.STRING)
	private String goodsdtlid ;
	
	@Column(value = "lotid", type = ColumnType.STRING)
	private String lotid ;
	
	@Column(value = "qty", type = ColumnType.NUMBER)
	private BigDecimal qty ;
	
	@ReadOnly
	@Column(value = "stockqty", type = ColumnType.NUMBER)
	private BigDecimal stockqty ;
	
	@ReadOnly
	@Column(value = "stockid", type = ColumnType.NUMBER)
	private String stockid ;
	
	@Column(value = "purprice", type = ColumnType.NUMBER)
	private BigDecimal purprice ;
	
	@Column(value = "purcost", type = ColumnType.NUMBER)
	private BigDecimal purcost ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@Column(value = "orderdtlid", type = ColumnType.STRING)
	private String orderdtlid ;
	
	@ReadOnly
	@Column(value = "batchno", type = ColumnType.STRING)
	private String batchno ;
	
	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "prodate", type = ColumnType.DATE)
	private String prodate ;
	
	@ReadOnly
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "expiredate", type = ColumnType.DATE)
	private String expiredate ;

	@ReadOnly
	@Column(value = "goodsname", type = ColumnType.DATE)
	private String goodsname ;
	
	@ReadOnly
	@Column(value = "spell", type = ColumnType.DATE)
	private String spell ;
	
	@ReadOnly
	@Column(value = "spec", type = ColumnType.DATE)
	private String spec ;
	
	@ReadOnly
	@Column(value = "unitid", type = ColumnType.DATE)
	private String unitid ;
	
	@ReadOnly
	@Column(value = "unitname", type = ColumnType.DATE)
	private String unitname ;

	
	
	
	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}

	public BigDecimal getStockqty() {
		return stockqty;
	}

	public void setStockqty(BigDecimal stockqty) {
		this.stockqty = stockqty;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getProdate() {
		return prodate;
	}

	public void setProdate(String prodate) {
		this.prodate = prodate;
	}

	public String getExpiredate() {
		return expiredate;
	}

	public void setExpiredate(String expiredate) {
		this.expiredate = expiredate;
	}

	public String getDtlid (){
		return dtlid;
	}
		 
	public void setDtlid (String dtlid){
		this.dtlid=dtlid;
	}

	public String getDocid (){
		return docid;
	}
		 
	public void setDocid (String docid){
		this.docid=docid;
	}

	public String getRowno (){
		return rowno;
	}
		 
	public void setRowno (String rowno){
		this.rowno=rowno;
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

	public String getLotid (){
		return lotid;
	}
		 
	public void setLotid (String lotid){
		this.lotid=lotid;
	}

	public BigDecimal getQty (){
		return qty;
	}
		 
	public void setQty (BigDecimal qty){
		this.qty=qty;
	}

	public BigDecimal getPurprice (){
		return purprice;
	}
		 
	public void setPurprice (BigDecimal purprice){
		this.purprice=purprice;
	}

	public BigDecimal getPurcost (){
		return purcost;
	}
		 
	public void setPurcost (BigDecimal purcost){
		this.purcost=purcost;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

	public String getOrderdtlid (){
		return orderdtlid;
	}
		 
	public void setOrderdtlid (String orderdtlid){
		this.orderdtlid=orderdtlid;
	}

}
