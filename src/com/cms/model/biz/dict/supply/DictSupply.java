/**
** create by code gen .
**/
package com.cms.model.biz.dict.supply;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.base.BasePO;

@Table("DICT_SUPPLY")
@View("DICT_SUPPLY_V")
@PK({ "SUPPLYID" })
public class DictSupply extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final DictSupply INSTANCE = new DictSupply();

	@Column(value = "supplyid", type = ColumnType.STRING)
	private String supplyid;

	@Column(value = "supplyname", type = ColumnType.STRING)
	private String supplyname;

	@Column(value = "supplyno", type = ColumnType.STRING)
	private String supplyno;

	@Column(value = "supplyopcode", type = ColumnType.STRING)
	private String supplyopcode;

	@Column(value = "gpsflag", type = ColumnType.STRING)
	private String gpsflag;

	@Column(value = "corpcode", type = ColumnType.STRING)
	private String corpcode;

	@Column(value = "taxnumber", type = ColumnType.STRING)
	private String taxnumber;

	@Column(value = "legalperson", type = ColumnType.STRING)
	private String legalperson;

	@Column(value = "gspcategoryid", type = ColumnType.STRING)
	private String gspcategoryid;

	@Column(value = "registadd", type = ColumnType.STRING)
	private String registadd;

	@Column(value = "address", type = ColumnType.STRING)
	private String address;

	@Column(value = "memo", type = ColumnType.STRING)
	private String memo;

	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "credate", type = ColumnType.DATE)
	private String credate;

	@Column(value = "inputmanid", type = ColumnType.STRING)
	private String inputmanid;

	@Column(value = "inputmanname", type = ColumnType.STRING)
	private String inputmanname;

	@Column(value = "usestatus", type = ColumnType.STRING)
	private String usestatus;

	@Column(value = "alias", type = ColumnType.STRING)
	private String alias;

	@Column(value = "spell", type = ColumnType.STRING)
	private String spell;

	@ReadOnly
	@Column(value = "text", type = ColumnType.STRING)
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSupplyid() {
		return supplyid;
	}

	public void setSupplyid(String supplyid) {
		this.supplyid = supplyid;
	}

	public String getSupplyname() {
		return supplyname;
	}

	public void setSupplyname(String supplyname) {
		this.supplyname = supplyname;
	}

	public String getSupplyno() {
		return supplyno;
	}

	public void setSupplyno(String supplyno) {
		this.supplyno = supplyno;
	}

	public String getSupplyopcode() {
		return supplyopcode;
	}

	public void setSupplyopcode(String supplyopcode) {
		this.supplyopcode = supplyopcode;
	}

	public String getGpsflag() {
		return gpsflag;
	}

	public void setGpsflag(String gpsflag) {
		this.gpsflag = gpsflag;
	}

	public String getCorpcode() {
		return corpcode;
	}

	public void setCorpcode(String corpcode) {
		this.corpcode = corpcode;
	}

	public String getTaxnumber() {
		return taxnumber;
	}

	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}

	public String getLegalperson() {
		return legalperson;
	}

	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}

	public String getGspcategoryid() {
		return gspcategoryid;
	}

	public void setGspcategoryid(String gspcategoryid) {
		this.gspcategoryid = gspcategoryid;
	}

	public String getRegistadd() {
		return registadd;
	}

	public void setRegistadd(String registadd) {
		this.registadd = registadd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCredate() {
		return credate;
	}

	public void setCredate(String credate) {
		this.credate = credate;
	}

	public String getInputmanid() {
		return inputmanid;
	}

	public void setInputmanid(String inputmanid) {
		this.inputmanid = inputmanid;
	}

	public String getInputmanname() {
		return inputmanname;
	}

	public void setInputmanname(String inputmanname) {
		this.inputmanname = inputmanname;
	}

	public String getUsestatus() {
		return usestatus;
	}

	public void setUsestatus(String usestatus) {
		this.usestatus = usestatus;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

}
