package com.cms.controller.biz.goods.check;

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
import com.cms.form.GoodsBillInDocForm;
import com.cms.form.DictStoreForm;
import com.cms.model.biz.goods.GoodsBillIn;
import com.cms.model.biz.goods.GoodsBillInDtl;
import com.cms.model.biz.goods.GoodsStockCheck;
import com.cms.model.biz.goods.GoodsStockCheckDtl;
import com.cms.model.biz.goods.GoodsStockCheckSum;
import com.cms.model.sys.SysUser;
import com.cms.service.biz.goods.GoodsBillStockCheckService;

import my.web.BaseController;
import net.sf.json.JSONObject;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月21日 下午10:13:31 
 * 
 *@descriptions 类说明：生成盘点
 */
@Controller
@RequestMapping("/cms/goods/check")
public class StockCheckController extends BaseController{
	@Autowired
	private GoodsBillStockCheckService billGoodsStockCheckService;
	/**
	 * 将表单数据对象存入数据模型
	 * @return
	 */
	@ModelAttribute("form")
	public DictStoreForm getForm(){
		return new DictStoreForm();
	}
	
	@RequestMapping("stock_check")
	public String list(Model m,HttpSession session) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		SysUser user=(SysUser) session.getAttribute("user");
		m.addAttribute("user", user);
		return "cms/biz/goods/check/stock_check";
	}
	
	/**
	 * 开始盘点
	 * @param m
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_check/startCheck")
	public JSONObject startCheck(Model m,HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String storeid=param("storeid","");
		/*
		 * 先判断是否存在未完成的盘点记录,如果存在,直接调用盘点记录
		 */
		GoodsStockCheck billGoodsStockCheck=billGoodsStockCheckService.getUnfinishedDocid(storeid);
		if(billGoodsStockCheck!=null){
			String docid=billGoodsStockCheck.getDocid();
			String docno=billGoodsStockCheck.getDocno();
			result.put("docid", docid);
			result.put("docno", docno);
			result.put("msg", "调取盘点单成功！");
			result.put("success", true);
			return JSONObject.fromObject(result);
		}
		/*
		 * 如果不存在未完成的盘点记录,开始生成盘点单,并且调用盘点记录
		 */
		if(billGoodsStockCheckService.checkStart(storeid)<0){
			result.put("msg", "操作失败！");
			result.put("success", false);
		}else{
			String docid_new=billGoodsStockCheckService.getUnfinishedDocid(storeid).getDocid();
			result.put("docid", docid_new);
			result.put("msg", "生成新盘点单成功！");
			result.put("success", true);
		}
		return JSONObject.fromObject(result);
	}
	/**
	 * 载入当前盘点的sum数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_check/gridDataStockCheckSum")
	public GridDataModel<GoodsStockCheckSum> gridDataStockCheckSum() {
		String docid=param("docid","");
		return billGoodsStockCheckService.gridDataSumByDocid(docid);
	}
	/**
	 * 载入当前盘点的dtl数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_check/gridDataStockCheckDtl")
	public GridDataModel<GoodsStockCheckDtl> gridDataStockCheckDtl(Model m,HttpSession session) {
		String goodsid=param("goodsid","");
		String goodsdtlid=param("goodsdtlid","");
		String docid=param("docid","");
		return billGoodsStockCheckService.gridDataDtlByDocid(goodsid,goodsdtlid,docid);
	}
	
	
	/**
	 * 保存盘点
	 * @param form
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("stock_check/saveCheck")
	public JSONObject saveCheck() {
		HashMap<String, Object> result = new HashMap<String, Object>();

		String dataSum=param("dataSum","");
		GridSaveModel modelSum = new GridSaveModel();
			modelSum = JSON.parseObject(dataSum, GridSaveModel.class);
		
		if(billGoodsStockCheckService.checkSave(modelSum)>0){
			result.put("msg", "操作成功！");
			result.put("success", true);
		}else{
			result.put("msg", "操作失败！");
			result.put("success", true);
		}
		
		return JSONObject.fromObject(result);
	}
}
