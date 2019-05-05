/**
** create by code gen .
**/
package com.cms.model.biz.dict.factory;

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

/**
 * 
 * @author 作者：wlm
 *
 * @version 创建时间：2019年4月15日 上午9:41:59
 * 
 * @descriptions 类说明：生产厂家MODEL
 */
@Table("DICT_FACTORY")
@View("DICT_FACTORY")
@PK("FACTORYID")
public class DictFactory extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final DictFactory INSTANCE = new DictFactory();

	@Column(value = "factoryid", type = ColumnType.STRING)
	private String factoryid;

	@Column(value = "factoryname", type = ColumnType.STRING)
	private String factoryname;

	@Column(value = "factorycode", type = ColumnType.STRING)
	private String factorycode;

	@Column(value = "factoryarea", type = ColumnType.STRING)
	private String factoryarea;

	@Column(value = "corpcode", type = ColumnType.STRING)
	private String corpcode;

	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "credate", type = ColumnType.DATE)
	private String credate;

	@Column(value = "inputmanid", type = ColumnType.STRING)
	private String inputmanid;

	@Column(value = "inputman", type = ColumnType.STRING)
	private String inputman;

	public String getFactoryid() {
		return factoryid;
	}

	public void setFactoryid(String factoryid) {
		this.factoryid = factoryid;
	}

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getFactorycode() {
		return factorycode;
	}

	public void setFactorycode(String factorycode) {
		this.factorycode = factorycode;
	}

	public String getFactoryarea() {
		return factoryarea;
	}

	public void setFactoryarea(String factoryarea) {
		this.factoryarea = factoryarea;
	}

	public String getCorpcode() {
		return corpcode;
	}

	public void setCorpcode(String corpcode) {
		this.corpcode = corpcode;
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

	public String getInputman() {
		return inputman;
	}

	public void setInputman(String inputman) {
		this.inputman = inputman;
	}

}
