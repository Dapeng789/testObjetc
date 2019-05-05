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



@Table("GOODS_BILL_APPLY")
@View("GOODS_BILL_APPLY_V")
@PK({ "DOCID" })
public class GoodsBillApply extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsBillApply INSTANCE = new GoodsBillApply();
	
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
	
	@Column(value = "deptid", type = ColumnType.STRING)
	private String deptid ;
	
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
	
	@Column(value = "senderid", type = ColumnType.STRING)
	private String senderid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "senddate", type = ColumnType.DATE)
	private String senddate ;
	
	@Column(value = "acceptid", type = ColumnType.STRING)
	private String acceptid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "acceptdate", type = ColumnType.DATE)
	private String acceptdate ;
	
	@Column(value = "outid", type = ColumnType.STRING)
	private String outid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "outdate", type = ColumnType.DATE)
	private String outdate ;
	
	@Column(value = "inid", type = ColumnType.STRING)
	private String inid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "indate", type = ColumnType.DATE)
	private String indate ;
	
	@Column(value = "status", type = ColumnType.STRING)
	private String status ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	


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

	public String getDeptid (){
		return deptid;
	}
		 
	public void setDeptid (String deptid){
		this.deptid=deptid;
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

	public String getSenderid (){
		return senderid;
	}
		 
	public void setSenderid (String senderid){
		this.senderid=senderid;
	}

	public String getSenddate (){
		return senddate;
	}
		 
	public void setSenddate (String senddate){
		this.senddate=senddate;
	}

	public String getAcceptid (){
		return acceptid;
	}
		 
	public void setAcceptid (String acceptid){
		this.acceptid=acceptid;
	}

	public String getAcceptdate (){
		return acceptdate;
	}
		 
	public void setAcceptdate (String acceptdate){
		this.acceptdate=acceptdate;
	}

	public String getOutid (){
		return outid;
	}
		 
	public void setOutid (String outid){
		this.outid=outid;
	}

	public String getOutdate (){
		return outdate;
	}
		 
	public void setOutdate (String outdate){
		this.outdate=outdate;
	}

	public String getInid (){
		return inid;
	}
		 
	public void setInid (String inid){
		this.inid=inid;
	}

	public String getIndate (){
		return indate;
	}
		 
	public void setIndate (String indate){
		this.indate=indate;
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
