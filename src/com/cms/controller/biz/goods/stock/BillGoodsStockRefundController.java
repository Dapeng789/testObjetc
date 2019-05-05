package com.cms.controller.biz.goods.stock;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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
import com.cms.model.biz.goods.GoodsStock;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.dict.DictGoodsPkgService;
import com.cms.service.biz.dict.DictStoreService;
import com.cms.service.biz.goods.GoodsBillStockInService;
import com.cms.service.biz.goods.GoodsStockService;

import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月21日 下午10:13:31 
 * 
 *@descriptions 类说明：请添加类描述
 */
@Controller
@RequestMapping("/cms/goods/stock")
public class BillGoodsStockRefundController extends BaseController{
	@Autowired
	private GoodsStockService goodsStockService;
	@Autowired
	private DictStoreService dictStoreService; 
	@Autowired
	private DictGoodsPkgService dictGoodsPkgService;
	@Autowired
	private GoodsBillStockInService billGoodsStockService;
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public GoodsBillInDocForm getForm(){
		return new GoodsBillInDocForm();
	}
	
	@RequestMapping("stock_refund")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock/stock_refund";
	}
	
	@ResponseBody
	@RequestMapping("stock_refund/gridDataDtl")
	public GridDataModel<GoodsBillInDtl> gridDataDtlByDocid() {
		String docid = param("docid", "");
		GridDataModel<GoodsBillInDtl> gridDataModel=billGoodsStockService.gridDataDtlByDocid(docid);
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("stock_refund/gridDataDocBycredate")
	public GridDataModel<GoodsBillIn> gridDataDocBycredate() {
		String credate = param("credate_history", "");
		GridDataModel<GoodsBillIn> gridDataModel=billGoodsStockService.gridDataDocBycredate(credate);
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("stock_refund/gridDataDocByid")
	public JSONObject gridDataDocByid() {
		String docid = param("docid", "");
		GoodsBillIn billGoodsIn=billGoodsStockService.getBillGoodsInByDocid(docid);
		return JSONObject.fromObject(billGoodsIn);
	}
	/**
	 * 保存
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_refund/save")
	public JSONObject save(@ModelAttribute("form") GoodsBillInDocForm form) {
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
			/* 细单 */
			List<GoodsBillInDtl> insert = model.inserts(GoodsBillInDtl.class);
			
			/* 保存(插入) */
			int rtn=billGoodsStockService.addCGTH(billGoodsIn,insert);
			if(rtn>0){
				JSONObject jo=JSONObject.fromObject(billGoodsStockService.getBillGoodsInByDocid(billGoodsIn.getDocid()));
				result.put("docData",jo);
				result.put("msg", "操作成功！");
				result.put("success", true);
			}else if(rtn==-2){
				result.put("msg", "操作失败！退货数量必须小于0");
				result.put("success", false);
			}else if(rtn==-3){
				result.put("msg", "操作失败！退货数量不能大于库存数量");
				result.put("success", false);
			}else{
				result.put("msg", "操作失败！");
				result.put("success", false);
			}
			
		}else{
			//*********更新(总单和细单)*********
			/* 总单 */
			GoodsBillIn billGoodsIn=billGoodsStockService.getBillGoodsInByDocid(form.getDocid());
			billGoodsIn.setStoreid(form.getStoreid());
			billGoodsIn.setSupplyid(form.getSupplyid());
			billGoodsIn.setMemo(form.getMemo());
			/* 细单 */
			List<GoodsBillInDtl> delete = model.deletes(GoodsBillInDtl.class);
			List<GoodsBillInDtl> update = model.updates(GoodsBillInDtl.class);
			List<GoodsBillInDtl> insert = model.inserts(GoodsBillInDtl.class);
			/* 保存(更新) */
			if(billGoodsStockService.modifyCGTH(billGoodsIn, update,delete,insert)>0){
				JSONObject jo=JSONObject.fromObject(billGoodsStockService.getBillGoodsInByDocid(billGoodsIn.getDocid()));
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
	@RequestMapping("stock_refund/setStatus")
	public JSONObject setStatus() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		String status=param("status","");//原status
		String value=param("value","");
		
		if (billGoodsStockService.setBillGoodsInDocStatus(docid, value)>0){
			JSONObject jo=JSONObject.fromObject(billGoodsStockService.getBillGoodsInByDocid(docid));
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
	 *  退库操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_refund/outstock")
	public JSONObject outstock() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		GoodsBillIn billGoodsIn=billGoodsStockService.getBillGoodsInByDocid(docid);
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
			if (billGoodsStockService.billGoodsRefundstock(docid)>0){
				result.put("msg", "退库成功!");
				result.put("success", true);
			}else{
				result.put("msg", "操作失败!");
				result.put("success", false);
			}
		}
		return JSONObject.fromObject(result);
		
	}
	
	
	/**
 	 * 获取仓库列表
 	 * @return
 	 */
	@ResponseBody
	@RequestMapping("stock_refund/getDictStore")
	public JSONArray getDictStore() {
		return JSONArray.fromObject(dictStoreService.findAll());
	}
	/**
	 * 获取单个goods
	 * 退货需要根据goodsid,判断库存情况
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_refund/getGoodsStockSelect")
	public JSONObject getGoodsSelect() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String stockid=param("stockid","");
//		String storeid=param("storeid","");
//		String goodsid=param("goodsid","");
//		String goodsdtlid=param("goodsdtlid","");
		
		
		GoodsStock goodsStock=goodsStockService.findOne(stockid);
		JSONObject jo=JSONObject.fromObject(goodsStock);
		jo.put("stockid", stockid);
		jo.put("stockQty", jo.get("qty"));
		jo.put("qty", Double.parseDouble(jo.get("qty").toString())*-1);
		
		result.put("data", jo);
		result.put("msg", "操作成功！");
		result.put("success", true);
		return JSONObject.fromObject(result);
	}
}
