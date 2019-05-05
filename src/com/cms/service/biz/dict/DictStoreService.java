package com.cms.service.biz.dict;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictStore;
import com.cms.util.biz.PageFactoryEasyUI;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月15日 下午5:11:58 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Service
public class DictStoreService {
	public List<DictStore> findAll(){
		return DictStore.INSTANCE.query("");
	}
	
	public GridDataModel<DictStore> getGridData(String storename){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(!storename.equals("")){
			filter.append(" and storename like ?");
			params.add("%"+storename+"%");
		}
		
		GridDataModel<DictStore> gridDataModel=PageFactoryEasyUI.newPage(
				DictStore.class, filter.toString()," order by storeid ",params.toArray());
		return gridDataModel;
	}
}
