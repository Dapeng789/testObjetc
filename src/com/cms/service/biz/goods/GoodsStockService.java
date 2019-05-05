package com.cms.service.biz.goods;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.GoodsStock;
import com.cms.model.biz.goods.GoodsStockIo;
import com.cms.model.biz.goods.GoodsStockSumV;
import com.cms.util.biz.PageFactoryEasyUI;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月20日 上午8:24:17 
 * 
 *@descriptions 类说明：库存业务service
 */
@Service
public class GoodsStockService {
	
	/**
	 * 一级库存datagrid汇总 
	 */
	public GridDataModel<GoodsStockSumV> getGridDataSum(){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append("1=?");
		params.add("1");
		GridDataModel<GoodsStockSumV> gridDataModel=PageFactoryEasyUI.newPage(GoodsStockSumV.class, filter.toString()," order by goodsid,goodsdtlid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 一级库存datagrid 明细
	 */
	public GridDataModel<GoodsStock> getGridDataDtl(){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append("1=?");
		params.add("1");
		GridDataModel<GoodsStock> gridDataModel=PageFactoryEasyUI.newPage(GoodsStock.class, filter.toString()," order by goodsid,goodsdtlid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 一级库存datagrid 汇总 根据名称，拼音
	 */
	public GridDataModel<GoodsStockSumV> getGridDataSum(String storeid,String goodsname,String goodsspell){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!storeid.equals("")){
			filter.append(" and storeid = ?");
			params.add(storeid);
		}
		if (!goodsname.equals("")){
			filter.append(" and goodsname like ?");
			params.add("%"+goodsname+"%");
		}
		if (!goodsspell.equals("")){
			filter.append(" and spell like ?");
			params.add("%"+goodsspell+"%");
		}
		GridDataModel<GoodsStockSumV> gridDataModel=PageFactoryEasyUI.newPage(GoodsStockSumV.class, filter.toString()," order by goodsid,goodsdtlid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 一级库存datagrid 明细 根据名称，拼音
	 */
	public GridDataModel<GoodsStock> getGridDataDtl(String storeid,String goodsname,String goodsspell){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!storeid.equals("")){
			filter.append(" and storeid = ?");
			params.add(storeid);
		}
		if (!goodsname.equals("")){
			filter.append(" and goodsname like ?");
			params.add("%"+goodsname+"%");
		}
		if (!goodsspell.equals("")){
			filter.append(" and spell like ?");
			params.add("%"+goodsspell+"%");
		}
		GridDataModel<GoodsStock> gridDataModel=PageFactoryEasyUI.newPage(GoodsStock.class, filter.toString()," order by goodsid,goodsdtlid ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 一级库存根据storeid  查找类列表
	 */
	public List<GoodsStock> findList(String storeid){
		return GoodsStock.INSTANCE.query("storeid=?", storeid);
	}
	/**
	 * 一级库存根据storeid , goodsid 查找类列表
	 */
	public List<GoodsStock> findList(String storeid,String goodsid){
		return GoodsStock.INSTANCE.query("storeid=? and goodsid=? ", goodsid);
	}
	/**
	 * 一级库存根据storeid , goodsid, goodsdtlid 查找类列表
	 */
	public List<GoodsStock> findList(String storeid,String goodsid,String goodsdtlid){
		return GoodsStock.INSTANCE.query("storeid=? and goodsid=? and goodsdtlid=?", storeid,goodsid,goodsdtlid);
	}
	/**
	 * 一级库存根据stockid 查找类
	 */
	public GoodsStock findOne(String stockid){
		return GoodsStock.INSTANCE.queryOne("stockid=?", stockid);
	}
	
	
	
	
	/**
	 * 通用一级库存入库操作
	 * 所有正向入库统一调用
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * @param storeid
	 * @param goodsid
	 * @param goodsdtlid
	 * @param lotid
	 * @param posid
	 * @param qty	入库数量必须为正
	 * @param billdtlid
	 * @param billtype
	 * @param billtable
	 * @return 1 成功  -1其他失败 -2入库数量不能小于或等于0
	 */
	public  int goodsInStock(String storeid,
							 String goodsid,
							 String goodsdtlid,
							 String lotid,
							 String posid,
							 BigDecimal qty,
							 String billdtlid,
							 String billtype,
							 String billtable){
		if(qty.compareTo(BigDecimal.ZERO)!=1){ //小于或等于0
			return -2;
		}
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			/*
			 * 入库存
			 */
			GoodsStock goodsStock=new GoodsStock();
			String stockid=goodsStock.newId().toString();
			goodsStock.setStockid(stockid);
			goodsStock.setStoreid(storeid);
			goodsStock.setGoodsid(goodsid);
			goodsStock.setGoodsdtlid(goodsdtlid);
			goodsStock.setLotid(lotid);
			goodsStock.setPosid(posid);
			goodsStock.setQty(qty);
			goodsStock.setStatus("Y");//状态 Y可用 N禁用
			
			goodsStock.insert();
			/*
			 * 库存流水
			 */
			GoodsStockIo goodsStockIo=new GoodsStockIo();
			goodsStockIo.setIoid(goodsStockIo.newId().toString());
			goodsStockIo.setIodate(sdf.format(date));
			goodsStockIo.setBilldtlid(billdtlid);
			goodsStockIo.setBilltype(billtype);
			goodsStockIo.setBilltable(billtable);
			goodsStockIo.setMemo("");
			goodsStockIo.setStockid(stockid);
			goodsStockIo.setStoreid(storeid);
			goodsStockIo.setLotid(lotid);
			goodsStockIo.setGoodsid(goodsid);
			goodsStockIo.setGoodsdtlid(goodsdtlid);
			goodsStockIo.setQty(qty);
			
			goodsStockIo.insert();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	/**
	 * 通用一级库存出库操作
	 * 所有负向出库统一调用
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * 第一种情况:指定库存记录消减 ,需要提供stockid,消减库存的数量qty
	 * @param stockid 要更新的库存条目
	 * @param storeid 以下参数是库存流水需要的单据明细信息
	 * @param goodsid
	 * @param goodsdtlid
	 * @param lotid
	 * @param posid
	 * @param qty	出库数量必须是负数
	 * @param billdtlid
	 * @param billtype
	 * @param billtable
	 * @return  1 成功  -1其他失败 -2出库数量不能大于或等于0
	 */
	public  int goodsOutStock(
							 String stockid ,
							 String storeid,
							 String goodsid,
							 String goodsdtlid,
							 String lotid,
							 String posid,
							 BigDecimal qty,
							 String billdtlid,
							 String billtype,
							 String billtable){
		if(qty.compareTo(BigDecimal.ZERO)!=-1){ //小于或等于0
			return -2;
		}
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			/*
			 * 出库存
			 */
			GoodsStock goodsStock=GoodsStock.INSTANCE.queryOne("stockid=? and status='Y'", stockid);
			goodsStock.setQty(goodsStock.getQty().subtract(qty.abs()));//库存数量相减
			goodsStock.update();
			/*
			 * 库存IO流水
			 */
			GoodsStockIo goodsStockIo=new GoodsStockIo();
			goodsStockIo.setIoid(goodsStockIo.newId().toString());
			goodsStockIo.setIodate(sdf.format(date));
			goodsStockIo.setBilldtlid(billdtlid);
			goodsStockIo.setBilltype(billtype);
			goodsStockIo.setBilltable(billtable);
			goodsStockIo.setMemo("");
			goodsStockIo.setStockid(stockid);
			goodsStockIo.setStoreid(storeid);
			goodsStockIo.setLotid(lotid);
			goodsStockIo.setGoodsid(goodsid);
			goodsStockIo.setGoodsdtlid(goodsdtlid);
			goodsStockIo.setQty(qty);
			
			goodsStockIo.insert();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	/**
	 * 通用一级库存出库操作
	 * 所有负向出库统一调用
	 * 方法内不进行事物的处理,调用者务必提交或回滚 
	 * 第二种情况:未指定库存记录 ,没有提供stockid ,只有消减数量 qty
	 * 			 按照满足条件的记录进行顺序核销
	 * 			 顺序核销的规则暂定按照入库时间从前往后消减
	 * @param storeid 
	 * @param goodsid
	 * @param goodsdtlid
	 * @param lotid
	 * @param posid
	 * @param qty	出库数量必须是正数
	 * @param billdtlid
	 * @param billtype
	 * @param billtable
	 * @return  1 成功  -1其他失败 -2出库数量不能大于或等于0
	 */
	public  int goodsOutStock(
							 String storeid,
							 String goodsid,
							 String goodsdtlid,
							 String lotid,
							 String posid,
							 BigDecimal qty,
							 String billdtlid,
							 String billtype,
							 String billtable){
		if(qty.compareTo(BigDecimal.ZERO)!=-1){ //小于或等于0
			return -2;
		}
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List<GoodsStock> list=GoodsStock.INSTANCE.query("storeid=? and goodsid=? and goodsdtlid=? and status='Y' "
				+ "order by lotid ", storeid,goodsid,goodsdtlid); //按照批次顺序核销 , 此处order by非常重要
		
		//do something....
		return -1;
	}
}























