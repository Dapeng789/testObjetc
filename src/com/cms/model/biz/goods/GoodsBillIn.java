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



@Table("GOODS_BILL_IN")
@View("GOODS_BILL_IN_V")
@PK({ "DOCID" })
public class GoodsBillIn extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsBillIn INSTANCE = new GoodsBillIn();
	
	@Name
	@Column(value = "docid", type = ColumnType.STRING)
	private String docid ;
	
	@Column(value = "docno", type = ColumnType.STRING)
	private String docno ;
	
	@Column(value = "billtype", type = ColumnType.STRING)
	private String billtype ;
	
	@Column(value = "billname", type = ColumnType.STRING)
	private String billname ;
	
	@Column(value = "storeid", type = ColumnType.STRING)
	private String storeid ;
	
	@Column(value = "supplyid", type = ColumnType.STRING)
	private String supplyid ;
	
	@Column(value = "cost", type = ColumnType.NUMBER)
	private BigDecimal cost ;
	
	@Column(value = "createrid", type = ColumnType.STRING)
	private String createrid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "createdate", type = ColumnType.DATE)
	private String createdate ;
	
	@Column(value = "checkerid", type = ColumnType.STRING)
	private String checkerid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "checkdate", type = ColumnType.DATE)
	private String checkdate ;
	
	@Column(value = "opid", type = ColumnType.STRING)
	private String opid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "opdate", type = ColumnType.DATE)
	private String opdate ;
	
	@Column(value = "status", type = ColumnType.STRING)
	private String status ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@ReadOnly
	@Column(value = "supplyname", type = ColumnType.STRING)
	private String supplyname ;

	
	
	public String getSupplyname() {
		return supplyname;
	}

	public void setSupplyname(String supplyname) {
		this.supplyname = supplyname;
	}

	public String getDocid (){
		return docid;
	}
		 
	public void setDocid (String docid){
		this.docid=docid;
	}

	public String getDocno (){
		return docno;
	}
		 
	public void setDocno (String docno){
		this.docno=docno;
	}

	public String getBilltype (){
		return billtype;
	}
		 
	public void setBilltype (String billtype){
		this.billtype=billtype;
	}

	public String getBillname (){
		return billname;
	}
		 
	public void setBillname (String billname){
		this.billname=billname;
	}

	public String getStoreid (){
		return storeid;
	}
		 
	public void setStoreid (String storeid){
		this.storeid=storeid;
	}

	public String getSupplyid (){
		return supplyid;
	}
		 
	public void setSupplyid (String supplyid){
		this.supplyid=supplyid;
	}

	public BigDecimal getCost (){
		return cost;
	}
		 
	public void setCost (BigDecimal cost){
		this.cost=cost;
	}

	public String getCreaterid (){
		return createrid;
	}
		 
	public void setCreaterid (String createrid){
		this.createrid=createrid;
	}

	public String getCreatedate (){
		return createdate;
	}
		 
	public void setCreatedate (String createdate){
		this.createdate=createdate;
	}

	public String getCheckerid (){
		return checkerid;
	}
		 
	public void setCheckerid (String checkerid){
		this.checkerid=checkerid;
	}

	public String getCheckdate (){
		return checkdate;
	}
		 
	public void setCheckdate (String checkdate){
		this.checkdate=checkdate;
	}

	public String getOpid (){
		return opid;
	}
		 
	public void setOpid (String opid){
		this.opid=opid;
	}

	public String getOpdate (){
		return opdate;
	}
		 
	public void setOpdate (String opdate){
		this.opdate=opdate;
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

}
