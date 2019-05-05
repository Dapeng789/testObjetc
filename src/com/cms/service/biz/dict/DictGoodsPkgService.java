package com.cms.service.biz.dict;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictGoodsPkg;
import com.cms.util.biz.PageFactoryEasyUI;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月16日 上午11:26:52 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Service
public class DictGoodsPkgService {
	public List<DictGoodsPkg> findAll(){
		return DictGoodsPkg.INSTANCE.query("");
	}
	public DictGoodsPkg findById(String goodsid,String goodsdtlid){
		DictGoodsPkg dictGoodsPkg=DictGoodsPkg.INSTANCE.queryOne(" goodsid=? and goodsdtlid=? ", goodsid,goodsdtlid);
		return dictGoodsPkg;
	}
	public GridDataModel<DictGoodsPkg> getGridData(String goodsname,String spell){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(!goodsname.equals("")){
			filter.append(" and goodsname like ?");
			params.add("%"+goodsname+"%");
		}
		
		if(!spell.equals("")){
			filter.append(" and spell like ?");
			params.add("%"+spell.toUpperCase()+"%");
		}
		
		GridDataModel<DictGoodsPkg> gridDataModel=PageFactoryEasyUI.newPage(
				DictGoodsPkg.class, filter.toString()," order by goodsid,sortno ",params.toArray());
		return gridDataModel;
	}
	
	
}
