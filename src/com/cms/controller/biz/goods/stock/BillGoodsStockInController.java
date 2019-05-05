package com.cms.controller.biz.goods.stock;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.cms.form.GoodsBillInDocForm;
import com.cms.model.biz.goods.GoodsBillIn;
import com.cms.model.biz.goods.GoodsBillInDtl;
import com.cms.model.sys.SysUser;

import com.cms.service.biz.dict.DictGoodsPkgService;
import com.cms.service.biz.dict.DictStoreService;
import com.cms.service.biz.goods.GoodsBillStockInService;

import my.dao.pool.DBManager;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月15日 上午11:41:50 
 * 
 *@descriptions 类说明：入库单
 */
@Controller
@RequestMapping("/cms/goods/stock")
public class BillGoodsStockInController  extends BaseController{
	@Autowired
	private GoodsBillStockInService billGoodsStockInService;
	@Autowired
	private DictGoodsPkgService dictGoodsPkgService;
	
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public GoodsBillInDocForm getForm(){
		return new GoodsBillInDocForm();
	}
	
	@RequestMapping("stock_in")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock/stock_in";
	}
	
	@ResponseBody
	@RequestMapping("stock_in/gridDataDtl")
	public GridDataModel<GoodsBillInDtl> gridDataDtlByDocid() {
		String docid = param("docid", "");
		GridDataModel<GoodsBillInDtl> gridDataModel=billGoodsStockInService.gridDataDtlByDocid(docid);
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("stock_in/gridDataDocBycredate")
	public GridDataModel<GoodsBillIn> gridDataDocBycredate() {
		String credate = param("credate_history", "");
		GridDataModel<GoodsBillIn> gridDataModel=billGoodsStockInService.gridDataDocBycredate(credate);
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("stock_in/gridDataDocByid")
	public JSONObject gridDataDocByid() {
		String docid = param("docid", "");
		GoodsBillIn billGoodsIn=billGoodsStockInService.getBillGoodsInByDocid(docid);
		return JSONObject.fromObject(billGoodsIn);
	}
	/**
	 * 保存
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_in/save")
	public JSONObject save(@ModelAttribute("form") GoodsBillInDocForm form,
			HttpSession session) {
		SysUser user=(SysUser) session.getAttribute("user");
		HashMap<String, Object> result = new HashMap<String, Object>();
		String dataDtl=param("p1","");
		GridSaveModel model = JSON.parseObject(dataDtl, GridSaveModel.class);
		
		if(form.getDocid().equals("")){
			//*********新增(总单和细单)*********
			/* 总单 */
			GoodsBillIn billGoodsIn=new GoodsBillIn();
			try {
				BeanUtils.copyProperties(billGoodsIn, form);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			billGoodsIn.setCreaterid(user.getUserid());
			/* 细单 */
			List<GoodsBillInDtl> insert = model.inserts(GoodsBillInDtl.class);
			/* 保存(插入) */
			if(billGoodsStockInService.addCGRK(billGoodsIn,insert)>0){
				JSONObject jo=JSONObject.fromObject(billGoodsStockInService.getBillGoodsInByDocid(billGoodsIn.getDocid()));
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
			GoodsBillIn billGoodsIn=billGoodsStockInService.getBillGoodsInByDocid(form.getDocid());
			billGoodsIn.setStoreid(form.getStoreid());
			billGoodsIn.setSupplyid(form.getSupplyid());
			billGoodsIn.setMemo(form.getMemo());
			/* 细单 */
			List<GoodsBillInDtl> delete = model.deletes(GoodsBillInDtl.class);
			List<GoodsBillInDtl> update = model.updates(GoodsBillInDtl.class);
			List<GoodsBillInDtl> insert = model.inserts(GoodsBillInDtl.class);
			/* 保存(更新) */
			if(billGoodsStockInService.modifyCGRK(billGoodsIn, update,delete,insert)>0){
				JSONObject jo=JSONObject.fromObject(billGoodsStockInService.getBillGoodsInByDocid(billGoodsIn.getDocid()));
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
	/**
	 *  设置主单状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_in/setStatus")
	public JSONObject setStatus() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		String status=param("status","");//原status
		String value=param("value","");
		
		if (billGoodsStockInService.setBillGoodsInDocStatus(docid, value)>0){
			JSONObject jo=JSONObject.fromObject(billGoodsStockInService.getBillGoodsInByDocid(docid));
			result.put("docData",jo);
			result.put("msg", "操作成功!");
			result.put("success", true);
		}else{
			result.put("msg", "操作失败!");
			result.put("success", false);
		}
		
		return JSONObject.fromObject(result);
	}
	/**
	 *  入库操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_in/instock")
	public JSONObject instock() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		GoodsBillIn billGoodsIn=billGoodsStockInService.getBillGoodsInByDocid(docid);
		String status=billGoodsIn.getStatus();
		//状态:N临时C已审核D已处理F作废
		if(status.equals("N")){
			result.put("msg", "未审核");
			result.put("success", false);
		}else if(status.equals("D")){
			result.put("msg", "已入库");
			result.put("success", false);
		}else if(status.equals("F")){
			result.put("msg", "已作废");
			result.put("success", false);
		}else if(status.equals("C")){//已审核
			if (billGoodsStockInService.billGoodsInstock(docid)>0){
				result.put("msg", "入库成功!");
				result.put("success", true);
			}else{
				result.put("msg", "操作失败!");
				result.put("success", false);
			}
		}
		return JSONObject.fromObject(result);
		
	}
	
	

	
	
}
