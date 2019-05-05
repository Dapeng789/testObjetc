package com.cms.controller.biz.goods.stock;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.bean.GridDataModel;
import com.cms.form.GoodsBillApplyDocForm;
import com.cms.model.biz.goods.GoodsBillApply;
import com.cms.model.biz.goods.GoodsBillApplyDtl;
import com.cms.model.sys.SysUser;

import com.cms.service.biz.goods.GoodsBillStock2ApplyService;

import my.web.BaseController;
import net.sf.json.JSONObject;
/**
 * 
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月15日 上午11:41:50 
 * 
 *@descriptions 类说明：科室出库单
 */
@Controller
@RequestMapping("/cms/goods/stock")
public class BillGoodsStockOutController  extends BaseController{
	@Autowired
	 private GoodsBillStock2ApplyService goodsbillStock2ApplyService;
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public GoodsBillApplyDocForm getForm(){
		return new GoodsBillApplyDocForm();
	}
	
	@RequestMapping("stock_out")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/stock/stock_out";
	}
	
	@ResponseBody
	@RequestMapping("stock_out/gridDataDtl")
	public GridDataModel<GoodsBillApplyDtl> gridDataDtlByDocid() {
		String docid = param("docid", "");
		GridDataModel<GoodsBillApplyDtl> gridDataModel=goodsbillStock2ApplyService.gridDataDtlByDocid(docid);
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("stock_out/gridDataDocBycredate")
	public GridDataModel<GoodsBillApply> gridDataDocBycredate() {
		String credate = param("credate_history", "");
		GridDataModel<GoodsBillApply> gridDataModel=goodsbillStock2ApplyService.gridDataDocBycredate(credate);
		return gridDataModel;
	}
	@ResponseBody
	@RequestMapping("stock_out/gridDataDocByid")
	public JSONObject gridDataDocByid() {
		String docid = param("docid", "");
		GoodsBillApply goodsBillApply=goodsbillStock2ApplyService.getBillGoodsInByDocid(docid);
		return JSONObject.fromObject(goodsBillApply);
	}
	/**
	 *  设置主单状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_out/setStatus")
	public JSONObject setStatus() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String docid=param("docid","");
		String status=param("status","");//原status
		String value=param("value","");
		
		if (goodsbillStock2ApplyService.setBillGoodsApplyDocStatus(docid, value)>0){
			JSONObject jo=JSONObject.fromObject(goodsbillStock2ApplyService.getBillGoodsInByDocid(docid));
			result.put("docData",jo);
			result.put("msg", "操作成功!");
			result.put("success", true);
		}else{
			result.put("msg", "操作失败!");
			result.put("success", false);
		}
		
		return JSONObject.fromObject(result);
	}
}
