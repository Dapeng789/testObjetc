package com.cms.controller.biz.goods.stock2;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.form.GoodsBillApplyDocForm;
import com.cms.model.biz.goods.GoodsBillApply;
import com.cms.model.biz.goods.GoodsBillApplyDtl;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.goods.GoodsBillStock2ApplyService;

import my.web.BaseController;
import net.sf.json.JSONObject;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月28日 下午2:52:25 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Controller
@RequestMapping("/cms/goods/stock2")
public class BillGoodsStock2ApplyController  extends BaseController{
	
	@Autowired
	private GoodsBillStock2ApplyService goodsBillStock2ApplyService;
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public GoodsBillApplyDocForm getForm(){
		return new GoodsBillApplyDocForm();
	}
	
	@RequestMapping("stock2_apply")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock2/stock2_apply";
	}
	/**
	 * 获取Dtl的datagrid 通过docid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_apply/gridDataDtl")
	public GridDataModel<GoodsBillApplyDtl> gridDataDtlByDocid() {
		String docid = param("docid", "");
		GridDataModel<GoodsBillApplyDtl> gridDataModel=goodsBillStock2ApplyService.gridDataDtlByDocid(docid);
		return gridDataModel;
	}
	/**
	 * 保存
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock2_apply/save")
	public JSONObject save(@ModelAttribute("form") GoodsBillApplyDocForm form) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String dataDtl=param("p1","");
		GridSaveModel model = JSON.parseObject(dataDtl, GridSaveModel.class);
		
		if(form.getDocid().equals("")){
			//*********新增(总单和细单)*********
			/* 总单 */
			GoodsBillApply billGoodsApply=new GoodsBillApply();
			try {
				BeanUtils.copyProperties(billGoodsApply, form);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			/* 细单 */
			List<GoodsBillApplyDtl> insert = model.inserts(GoodsBillApplyDtl.class);
			/* 保存(插入) */
			if(goodsBillStock2ApplyService.addSQRK(billGoodsApply,insert)>0){
				JSONObject jo=JSONObject.fromObject(goodsBillStock2ApplyService.getBillGoodsInByDocid(billGoodsApply.getDocid()));
				result.put("docData",jo);
				result.put("msg", "操作成功！");
				result.put("success", true);
			}else{
				result.put("msg", "操作失败！"); 
				result.put("success", false);
			}
			
		}else{
			//*********更新(总单和细单)*********
			/* 总单 */
			GoodsBillApply goodsBillApply=goodsBillStock2ApplyService.getBillGoodsInByDocid(form.getDocid());
			goodsBillApply.setStoreid(form.getStoreid());
			goodsBillApply.setDeptid(form.getDeptid());
			goodsBillApply.setMemo(form.getMemo());
			/* 细单 */
			List<GoodsBillApplyDtl> delete = model.deletes(GoodsBillApplyDtl.class);
			List<GoodsBillApplyDtl> update = model.updates(GoodsBillApplyDtl.class);
			List<GoodsBillApplyDtl> insert = model.inserts(GoodsBillApplyDtl.class);
			/* 保存(更新) */
			if(goodsBillStock2ApplyService.modifySQRK(goodsBillApply, update,delete,insert)>0){
				JSONObject jo=JSONObject.fromObject(goodsBillStock2ApplyService.getBillGoodsInByDocid(goodsBillApply.getDocid()));
				result.put("docData",jo);
				result.put("msg", "操作成功！");
				result.put("success", true);
			}else{
				result.put("msg", "操作失败！");
				result.put("success", false);
			}
		}
		return JSONObject.fromObject(result);
	}
}