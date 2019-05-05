package com.cms.service.biz.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.dict.supply.DictSupply;
import com.cms.pubsrv.WarnMessage;
import com.cms.util.biz.PageFactoryEasyUI;

import my.dao.pool.DBManager;

/**
 * @author 作者：qgn
 *
 * @version 创建时间：2019年4月16日 上午11:26:52
 * 
 * @descriptions 类说明：供应商SERVICE
 */
@Service
public class DictSupplyService {
	public List<DictSupply> findAll() {
		return DictSupply.INSTANCE.query("");
	}

	/**
	 * 查询供应商，toolbar.query()
	 * 
	 * @param supplyname 供应商名称
	 * @param spell      供应商编码
	 * @return
	 */

	public GridDataModel<DictSupply> getGridData(String supplyname, String spell) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!supplyname.equals("")) {
			filter.append(" and supplyname like ?");
			params.add("%" + supplyname + "%");
		}

		if (!spell.equals("")) {
			filter.append(" and upper(supplyopcode) like ?");
			params.add("%" + spell.toUpperCase() + "%");
		}

		GridDataModel<DictSupply> gridDataModel = PageFactoryEasyUI.newPage(DictSupply.class, filter.toString(),
				" order by supplyid ", params.toArray());
		return gridDataModel;
	}

	/**
	 * 
	 * @author 作者：wanglimin
	 *
	 * @version 创建时间：2019年4月23日 下午3:04:26
	 * 
	 * @descriptions 类说明：保存供应商信息
	 *
	 * @param json
	 * @return
	 */
	public HashMap<String, Object> save(String json) {
		GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);

		List<DictSupply> insert = model.inserts(DictSupply.class);
		List<DictSupply> deletes = model.deletes(DictSupply.class);
		List<DictSupply> updates = model.updates(DictSupply.class);

		try {
			for (DictSupply comp : insert) {
				comp.setSupplyid(DictSupply.INSTANCE.newId().toString());
				comp.insert();
			}
			for (DictSupply compe : deletes) {
				compe.delete();
			}
			for (DictSupply compe : updates) {
				compe.update();
			}
			DBManager.commitAll();
		} catch (Exception e) {
			// TODO: handle exception
			DBManager.rollbackAll();
			return WarnMessage.show("操作失败：" + e.getMessage());
		}
		return WarnMessage.show("操作成功", "1");
	}

}
