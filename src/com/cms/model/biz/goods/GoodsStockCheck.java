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



@Table("GOODS_STOCK_CHECK")
@View("GOODS_STOCK_CHECK_V")
@PK({ "DOCID" })
public class GoodsStockCheck extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsStockCheck INSTANCE = new GoodsStockCheck();
	
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
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "begindate", type = ColumnType.DATE)
	private String begindate ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "enddate", type = ColumnType.DATE)
	private String enddate ;
	
	@Column(value = "createrid", type = ColumnType.STRING)
	private String createrid ;
	
	@Column(value = "checkerid", type = ColumnType.STRING)
	private String checkerid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "checkdate", type = ColumnType.DATE)
	private String checkdate ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	
	@Column(value = "status", type = ColumnType.STRING)
	private String status ;
	


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

	public String getBegindate (){
		return begindate;
	}
		 
	public void setBegindate (String begindate){
		this.begindate=begindate;
	}

	public String getEnddate (){
		return enddate;
	}
		 
	public void setEnddate (String enddate){
		this.enddate=enddate;
	}

	public String getCreaterid (){
		return createrid;
	}
		 
	public void setCreaterid (String createrid){
		this.createrid=createrid;
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

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

	public String getStatus (){
		return status;
	}
		 
	public void setStatus (String status){
		this.status=status;
	}

}
