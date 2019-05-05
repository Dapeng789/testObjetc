package com.cms.service.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.dict.DictProdarea;
import com.cms.util.biz.PageFactory;

@Service
public class CRUDRowsService <T>  {
	
	/*public GridDataModel<T>  gridData(Model m) {
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();

		GridDataModel<T> gridDataModel=PageFactory.newPage(T.class, 
				filter.toString(), " order by sourcecode ", params.toArray());
		return gridDataModel;
	}*/
}
