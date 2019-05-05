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



@Table("GOODS_BATCHNO")
@View("GOODS_BATCHNO_V")
@PK({ "BATCHNO" })
public class GoodsBatchno extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoodsBatchno INSTANCE = new GoodsBatchno();
	
	@Column(value = "batchno", type = ColumnType.STRING)
	private String batchno ;
	
	@Column(value = "goodsid", type = ColumnType.STRING)
	private String goodsid ;
	
	@Column(value = "goodsdtlid", type = ColumnType.STRING)
	private String goodsdtlid ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "prodate", type = ColumnType.DATE)
	private String prodate ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "expiredate", type = ColumnType.DATE)
	private String expiredate ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "createdate", type = ColumnType.DATE)
	private String createdate ;
	
	@Column(value = "userid", type = ColumnType.STRING)
	private String userid ;
	


	

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
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

	public String getProdate (){
		return prodate;
	}
		 
	public void setProdate (String prodate){
		this.prodate=prodate;
	}

	public String getExpiredate (){
		return expiredate;
	}
		 
	public void setExpiredate (String expiredate){
		this.expiredate=expiredate;
	}

	public String getCreatedate (){
		return createdate;
	}
		 
	public void setCreatedate (String createdate){
		this.createdate=createdate;
	}

	public String getUserid (){
		return userid;
	}
		 
	public void setUserid (String userid){
		this.userid=userid;
	}

}
