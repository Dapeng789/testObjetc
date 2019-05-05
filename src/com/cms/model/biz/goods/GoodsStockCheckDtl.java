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



@Table("GOODS_STOCK_CHECK_DTL")
@View("GOODS_STOCK_CHECK_DTL_V")
@PK({ "DTLID" })
public class GoodsStockCheckDtl extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsStockCheckDtl INSTANCE = new GoodsStockCheckDtl();
	
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
	
	@Column(value = "posid", type = ColumnType.STRING)
	private String posid ;
	
	@Column(value = "lotid", type = ColumnType.STRING)
	private String lotid ;
	
	@Column(value = "qty", type = ColumnType.NUMBER)
	private BigDecimal qty ;
	
	@Column(value = "realqty", type = ColumnType.NUMBER)
	private BigDecimal realqty ;
	
	@Column(value = "diffqty", type = ColumnType.NUMBER)
	private BigDecimal diffqty ;
	
	@Column(value = "status", type = ColumnType.STRING)
	private String status ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@Column(value = "datasource", type = ColumnType.STRING)
	private String datasource ;
	

	@ReadOnly
	@Column(value = "goodsname", type = ColumnType.STRING)
	private String goodsname ;

	@ReadOnly
	@Column(value = "customname", type = ColumnType.STRING)
	private String customname ;

	@ReadOnly
	@Column(value = "spell", type = ColumnType.STRING)
	private String spell ;

	@ReadOnly
	@Column(value = "classid", type = ColumnType.STRING)
	private String classid ;

	@ReadOnly
	@Column(value = "classname", type = ColumnType.STRING)
	private String classname ;

	@ReadOnly
	@Column(value = "unitid", type = ColumnType.STRING)
	private String unitid ;

	@ReadOnly
	@Column(value = "unitname", type = ColumnType.STRING)
	private String unitname ;
	

	@ReadOnly
	@Column(value = "factoryname", type = ColumnType.STRING)
	private String factoryname ;

	@ReadOnly
	@Column(value = "prodareaname", type = ColumnType.STRING)
	private String prodareaname ;

	@ReadOnly
	@Column(value = "spec", type = ColumnType.STRING)
	private String spec ;

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

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getCustomname() {
		return customname;
	}

	public void setCustomname(String customname) {
		this.customname = customname;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
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

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getProdareaname() {
		return prodareaname;
	}

	public void setProdareaname(String prodareaname) {
		this.prodareaname = prodareaname;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
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

	public String getPosid (){
		return posid;
	}
		 
	public void setPosid (String posid){
		this.posid=posid;
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

	public BigDecimal getRealqty (){
		return realqty;
	}
		 
	public void setRealqty (BigDecimal realqty){
		this.realqty=realqty;
	}

	public BigDecimal getDiffqty (){
		return diffqty;
	}
		 
	public void setDiffqty (BigDecimal diffqty){
		this.diffqty=diffqty;
	}

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

	public String getDatasource (){
		return datasource;
	}
		 
	public void setDatasource (String datasource){
		this.datasource=datasource;
	}

}
