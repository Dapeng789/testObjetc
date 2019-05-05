package com.cms.pubsrv;

import java.util.HashMap;

/**
 * @author 作者：wlm
 *
 * @version 创建时间：2019年4月16日 上午9:39:47
 * 
 * @descriptions 类说明 公用返回類
 */
public class WarnMessage {
	/**
	 * 
	 * @author 作者：wlm
	 *
	 * @version 创建时间：2019年4月16日 上午9:44:54
	 * 
	 * @descriptions 描述：提示信息类
	 * @param value 提示的值
	 * @param flag  是否成功标志 1：成功 2：失敗不需要传值
	 * @return
	 */
	public static HashMap<String, Object> show(String value, String... flag) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (flag.length > 0) {
			result.put("msg", value);
			result.put("success", true);
		} else {
			result.put("msg", value);
			result.put("success", false);
		}
		return result;
	}

	/**
	 * 
	 * /**
	 * 
	 * @author 作者：wanglimin
	 *
	 * @version 创建时间：2019年4月23日 下午3:01:21
	 * 
	 * @descriptions 类说明：操作返回提示
	 *
	 * @param flag 1 成功 0 失败
	 * @param msg  如果为0,填写错误原因，为1 则为空
	 * @return
	 */
	public static HashMap<String, Object> show(int flag, String msg) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (flag == 1) {
			result.put("msg", "操作成功");
			result.put("success", true);
		} else {
			result.put("msg", "操作失败：" + msg);
			result.put("success", false);
		}
		return result;
	}
}
