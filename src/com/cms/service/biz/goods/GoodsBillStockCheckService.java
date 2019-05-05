package com.cms.service.biz.goods;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.biz.goods.GoodsBillInDtl;
import com.cms.model.biz.goods.GoodsStockCheck;
import com.cms.model.biz.goods.GoodsStockCheckDtl;
import com.cms.model.biz.goods.GoodsStockCheckSum;
import com.cms.model.biz.goods.GoodsStock;
import com.cms.model.biz.goods.GoodsStockSumV;
import com.cms.model.sys.SysUser;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.SessionHelpUtils;

import my.dao.pool.DBManager;
import my.util.MD5Util;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月24日 下午9:03:25 
 * 
 *@descriptions 类说明：盘点service
 */
@Service
public class GoodsBillStockCheckService {
	
	/**
	 * 查找当前唯一没有盘点完成的总单docid
	 */
	public GoodsStockCheck getUnfinishedDocid(String storeid){
		GoodsStockCheck billGoodsStockCheck=GoodsStockCheck.INSTANCE.queryOne("status<>'D' and storeid=?",storeid);
		if(billGoodsStockCheck==null){
			return null;
		}else{
			return billGoodsStockCheck;
		}
	}
	/**
	 * 根据docid,获取盘点sum的dg数据
	 */
	public GridDataModel<GoodsStockCheckSum> gridDataSumByDocid(String docid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and docid=?");
		params.add(docid);
		GridDataModel<GoodsStockCheckSum> gridDataModel=PageFactoryEasyUI.newPage(GoodsStockCheckSum.class, filter.toString()," order by rowno ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 根据docid,获取盘点dtl的dg数据
	 */
	public GridDataModel<GoodsStockCheckDtl> gridDataDtlByDocid(String goodsid,String goodsdtlid,String docid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and goodsid=? and goodsdtlid=? and docid=?");
		params.add(goodsid);
		params.add(goodsdtlid);
		params.add(docid);
		GridDataModel<GoodsStockCheckDtl> gridDataModel=PageFactoryEasyUI.newPage(GoodsStockCheckDtl.class, filter.toString()," order by rowno ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 盘点开始,由库存生成盘点单
	 */
	public int checkStart(String storeid){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		try {
			/*
			 * 总单
			 */
			GoodsStockCheck billGoodsStockCheck=new GoodsStockCheck();
			String docid=billGoodsStockCheck.newId().toString();
			billGoodsStockCheck.setDocid(docid);
			billGoodsStockCheck.setDocno("KFPD"+sdf2.format(date)+"-"+docid);
			billGoodsStockCheck.setBilltype("KFPD");
			billGoodsStockCheck.setBillname("库房盘点"); 
			billGoodsStockCheck.setStoreid(storeid);
			billGoodsStockCheck.setBegindate(sdf.format(date));
			//billGoodsStockCheck.setEnddate(enddate);
			billGoodsStockCheck.setCreaterid(user.getUserid()); 
			//billGoodsStockCheck.setCheckerid("");
			//billGoodsStockCheck.setCheckdate("");
			billGoodsStockCheck.setMemo(""); 
			billGoodsStockCheck.setStatus("N");//N临时 C已审  D已完成
			billGoodsStockCheck.insert();
			/*
			 * 细单汇总
			 */
			List<GoodsStockSumV> listSum=GoodsStockSumV.INSTANCE.query("storeid=? order by posid,goodsname", storeid);
			for (int i = 0; i < listSum.size(); i++) {
				GoodsStockCheckSum billGoodsStockCheckSum=new GoodsStockCheckSum();
				billGoodsStockCheckSum.setSumid(billGoodsStockCheckSum.newId().toString()); 
				billGoodsStockCheckSum.setDocid(docid); 
				billGoodsStockCheckSum.setRowno(""+i);  
				billGoodsStockCheckSum.setGoodsid(listSum.get(i).getGoodsid());  
				billGoodsStockCheckSum.setGoodsdtlid(listSum.get(i).getGoodsdtlid()); 
				billGoodsStockCheckSum.setPosid(""); 
				billGoodsStockCheckSum.setQty(listSum.get(i).getQty());  
				billGoodsStockCheckSum.setRealqty(listSum.get(i).getQty());  
				billGoodsStockCheckSum.setDiffqty(BigDecimal.valueOf(0)); 
				billGoodsStockCheckSum.setStatus("N"); //Y已盘N未盘
				billGoodsStockCheckSum.setMemo(""); 
				billGoodsStockCheckSum.setDatasource("1"); //数据来源 1库存表 2用户新增录入
				billGoodsStockCheckSum.insert();
			}
			/*
			 * 细单明细
			 */
			List<GoodsStock> listDtl=GoodsStock.INSTANCE.query("storeid=? order by posid,goodsname,lotid", storeid);
			for (int i = 0; i < listDtl.size(); i++) {
				GoodsStockCheckDtl billGoodsStockCheckDtl=new GoodsStockCheckDtl();
				billGoodsStockCheckDtl.setDtlid(billGoodsStockCheckDtl.newId().toString()); 
				billGoodsStockCheckDtl.setDocid(docid); 
				billGoodsStockCheckDtl.setRowno(""+i);  
				billGoodsStockCheckDtl.setGoodsid(listDtl.get(i).getGoodsid());  
				billGoodsStockCheckDtl.setGoodsdtlid(listDtl.get(i).getGoodsdtlid()); 
				billGoodsStockCheckDtl.setPosid(""); 
				billGoodsStockCheckDtl.setLotid(listDtl.get(i).getLotid()); 
				billGoodsStockCheckDtl.setQty(listDtl.get(i).getQty());  
				billGoodsStockCheckDtl.setRealqty(listDtl.get(i).getQty());  
				billGoodsStockCheckDtl.setDiffqty(BigDecimal.valueOf(0)); 
				billGoodsStockCheckDtl.setStatus("N"); //Y已盘N未盘
				billGoodsStockCheckDtl.setMemo(""); 
				billGoodsStockCheckDtl.setDatasource("1"); //数据来源 1库存表 2用户新增录入
				billGoodsStockCheckDtl.insert();
			}
			DBManager.commitAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBManager.rollbackAll();
			return -1;
		}
		
		return 1;
	}
	
	/**
	 * 盘点保存sum表
	 * 总单已经生成,保存过程不需要对总单操作
	 * sum入参为GridSaveModel
	 */
	public int checkSave(GridSaveModel modelSum){
		
		try {
			if(modelSum!=null){
				List<GoodsStockCheckSum> insertSum = modelSum.inserts(GoodsStockCheckSum.class);
				List<GoodsStockCheckSum> deleteSum = modelSum.deletes(GoodsStockCheckSum.class);
				List<GoodsStockCheckSum> updateSum = modelSum.updates(GoodsStockCheckSum.class);
				/* 处理sum表 */
				//其实没有删除
				for (GoodsStockCheckSum comp : deleteSum) {
					comp.delete();
				}
				//更新遵按批次循顺序核销
				for (GoodsStockCheckSum comp : updateSum) {
					comp.update();
					List<GoodsStockCheckDtl> list=GoodsStockCheckDtl.INSTANCE.query(" docid=? and goodsid=? and goodsdtlid=? ", comp.getDocid(),comp.getGoodsid(),comp.getGoodsdtlid());
					for (GoodsStockCheckDtl billGoodsStockCheckDtl : list) {
						System.out.println(billGoodsStockCheckDtl.getLotid());
					}
				}
				for (GoodsStockCheckSum comp : insertSum) {
					comp.setSumid(comp.newId().toString());
					comp.insert();
				}
			}
			
			
			DBManager.commitAll();
		} catch (Exception e) {
			e.printStackTrace();
			DBManager.rollbackAll();
			return -1;
		}
		
		return 1;
	}
}
