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

@Deprecated // updata by wlm 2019年4月23日13:58:54 由com.cms.model.biz.dict.supply.DictSupply 替换
@Table("DICT_SUPPLY")
@View("DICT_SUPPLY_V")
@PK({ "SUPPLYID" })
public class DictSupply extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final DictSupply INSTANCE = new DictSupply();

	@Name
	@Column(value = "supplyid", type = ColumnType.STRING)
	private String supplyid;

	@Column(value = "supplyname", type = ColumnType.STRING)
	private String supplyname;

	@Column(value = "alias", type = ColumnType.STRING)
	private String alias;

	@Column(value = "spell", type = ColumnType.STRING)
	private String spell;

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
