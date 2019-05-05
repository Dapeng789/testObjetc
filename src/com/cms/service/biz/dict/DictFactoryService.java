package com.cms.service.biz.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.dict.factory.DictFactory;
import com.cms.pubsrv.WarnMessage;
import com.cms.util.biz.PageFactoryEasyUI;

import my.dao.pool.DBManager;

/**
 * @author 作者：wlm
 *
 * @version 创建时间：2019年4月16日 上午8:46:41
 * 
 * @descriptions 类说明：生产厂家Service
 */
@Service
public class DictFactoryService {

	/**
	 * 
	 * @author 作者：wlm
	 *
	 * @version 创建时间：2019年4月16日 上午9:33:03
	 * 
	 * @descriptions 描述：保存生产厂家
	 * @param json
	 * @return
	 */
	public HashMap<String, Object> save(String json) {
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
		List<DictFactory> insert = model.inserts(DictFactory.class);
		List<DictFactory> update = model.updates(DictFactory.class);
		List<DictFactory> delete = model.deletes(DictFactory.class);
		try {

			for (DictFactory comp : insert) {
				comp.setFactoryid(DictFactory.INSTANCE.newId().toString());
				comp.insert();
			}
			for (DictFactory comp : update) {
				comp.update();
			}
			for (DictFactory comp : delete) {
				comp.delete();
			}
			DBManager.commitAll();
		} catch (Exception e) {
			// TODO: handle exception
			DBManager.rollbackAll();
			e.printStackTrace();
			return WarnMessage.show("操作失败：" + e.getMessage());
		}
		return WarnMessage.show("操作成功", "1");
	}

	public GridDataModel<DictFactory> getFactory(String facotrycode, String factoryname) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (facotrycode.length() > 0) {
			filter.append(" and upper(factorycode) like  upper(?) ");
			params.add("%" + facotrycode.toLowerCase() + "%");
		}
		if (factoryname.length() > 0) {
			filter.append(" and  factoryname like ? ");
			params.add("%" + factoryname + "%");
		}
		return PageFactoryEasyUI.newPage(DictFactory.class, filter.toString(), "order by factoryid", params.toArray());
	}
}
