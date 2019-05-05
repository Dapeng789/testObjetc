package com.cms.service.biz.goods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.bean.GridDataModel;
import com.cms.model.biz.goods.GoodsBillApply;
import com.cms.model.biz.goods.GoodsBillApplyDtl;
import com.cms.model.sys.SysUser;
import com.cms.util.biz.PageFactoryEasyUI;
import com.cms.util.biz.SessionHelpUtils;

import my.dao.pool.DBManager;
/**
 * @author 作者：qgn
 *
 * @version 创建时间：2019年4月15日 上午9:45:23
 * 
 * @descriptions 类说明：二级库申领单操作
 */
@Service
public class GoodsBillStock2ApplyService {
	/**
	 * 根据id,获取doc的对象数据
	 */
	public GoodsBillApply getBillGoodsInByDocid(String docid){
		GoodsBillApply billGoodsIn=GoodsBillApply.INSTANCE.queryOne("docid=?", docid);
		return billGoodsIn;
	}
	/**
	 * 根据docid,获取dtl的dg数据
	 */
	public GridDataModel<GoodsBillApplyDtl> gridDataDtlByDocid(String docid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and docid=?");
		params.add(docid);
		GridDataModel<GoodsBillApplyDtl> gridDataModel=PageFactoryEasyUI.newPage(GoodsBillApplyDtl.class, filter.toString()," order by rowno ",
				params.toArray());
		return gridDataModel;
	}
	/**
	 * 根据credate,获取doc的dg数据
	 */
	public GridDataModel<GoodsBillApply> gridDataDocBycredate(String credate){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (!credate.equals("")){
			filter.append(" and to_char(createdate,'yyyy-mm-dd')=?");
			params.add(credate);
		}
		GridDataModel<GoodsBillApply> gridDataModel=PageFactoryEasyUI.newPage(GoodsBillApply.class, filter.toString()," order by docid desc ",
				params.toArray());
		return gridDataModel;
	}
	
	/**
	 * 新增单据 二级库申请入库
	 * @param goodsBillApply 总单
	 * @param inserts 细单新增数据
	 * @return  1 正常  
	 * 			-1其他问题
	 */
	public int addSQRK(GoodsBillApply goodsBillApply,List<GoodsBillApplyDtl> inserts){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		try {
			//总单
			String docid=""+goodsBillApply.newId();
			goodsBillApply.setDocid(docid);
			goodsBillApply.setDocno("SQRK"+sdf2.format(date)+"-"+docid);
			goodsBillApply.setBilltype("SQRK");
			goodsBillApply.setBillname("申请入库");
			goodsBillApply.setCreatedate(sdf.format(date));
			goodsBillApply.setCreaterid(user.getUserid());
			goodsBillApply.setStatus("N");//状态:N临时C已审核D已处理F作废
			goodsBillApply.insert();
			//细单
			for(GoodsBillApplyDtl goodsBillApplyDtl:inserts){
				String dtlid=goodsBillApplyDtl.newId().toString();
				goodsBillApplyDtl.setDtlid(dtlid);
				goodsBillApplyDtl.setDocid(docid);
				goodsBillApplyDtl.insert();
			}
			//提交
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
		
	}
	
	/**
	 * 更新单据 二级库申请入库
	 * @param GoodsBillApply 总单
	 * @param updates 细单更新数据
	 * @param deletes 细单删除数据
	 * @return  1 正常   -1其他问题
	 * 			
	 */
	public int modifySQRK(GoodsBillApply billGoodsApply,
			List<GoodsBillApplyDtl> updates,
			List<GoodsBillApplyDtl> deletes,
			List<GoodsBillApplyDtl> inserts){
		try {
			//总单
			billGoodsApply.update();
			//细单
			for(GoodsBillApplyDtl goodsBillApplyDtl:deletes){
				goodsBillApplyDtl.delete();
			}
			for(GoodsBillApplyDtl goodsBillApplyDtl:updates){
				goodsBillApplyDtl.update();
			}
			for(GoodsBillApplyDtl goodsBillApplyDtl:inserts){
				String dtlid=goodsBillApplyDtl.newId().toString();
				goodsBillApplyDtl.setDtlid(dtlid);
				goodsBillApplyDtl.setDocid(billGoodsApply.getDocid());
				goodsBillApplyDtl.insert();
			}
			//提交
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
		
		
	}
	
	/**
	 * 设置主单状态
	 */
	public  int setBillGoodsApplyDocStatus(String docid,String status){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysUser user=(SysUser)SessionHelpUtils.getSession().getAttribute("user");
		GoodsBillApply goodsBillApply=GoodsBillApply.INSTANCE.queryOne("docid=?", docid);
		try {
			if(status.equals("C")){
				goodsBillApply.setCheckerid(user.getUserid());
				goodsBillApply.setCheckdate(sdf.format(date));
			}else if(status.equals("S")){
				goodsBillApply.setSenderid(user.getUserid());
				goodsBillApply.setSenddate(sdf.format(date));
			}else if(status.equals("A")){
				goodsBillApply.setAcceptid(user.getUserid());
				goodsBillApply.setAcceptdate(sdf.format(date));
			}else if(status.equals("O")){
				goodsBillApply.setOutid(user.getUserid());
				goodsBillApply.setOutdate(sdf.format(date));
			}else if(status.equals("D")){
				goodsBillApply.setInid(user.getUserid());
				goodsBillApply.setIndate(sdf.format(date));
			}
			goodsBillApply.setStatus(status);
			goodsBillApply.update();
			DBManager.commitAll();
			return 1;
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			return -1;
		}
	}
}
