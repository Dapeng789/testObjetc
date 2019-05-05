package com.cms.pubsrv;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridSaveModel;
import com.cms.model.sys.SysUser;

/**
 * @author 作者：wlm
 *
 * @version 创建时间：2019年4月16日 上午9:03:52
 * 
 * @descriptions 类说明：公用的SERVICE
 */
public class DBModel2Jdbc {
	public static HashMap<String, Object> Save(String json, Class<?> class1) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List insert = model.inserts(class1);
		List delete = model.deletes(class1);
		List update = model.updates(class1);
		return null;
	}

	public static void main(String[] args) {
		DBModel2Jdbc.Save("insert:{'userid':'s'}", SysUser.class);
	}
}
