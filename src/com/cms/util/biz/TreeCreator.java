package com.cms.util.biz;

import java.util.ArrayList;
import java.util.List;

import com.cms.model.biz.dict.DictClassGoods;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TreeCreator {
		
		//每个root单个处理
		public JSONArray buildTree(){
			JSONArray ja=new JSONArray();
			//获取根目录所有节点
			List<DictClassGoods> roots=DictClassGoods.INSTANCE.query("pid='00'");
			for (DictClassGoods root : roots) {
				ja.add(buildNode(JSONObject.fromObject(root)));
			}
			return ja;
		}
	
		//递归构建node单个节及其children
		private JSONObject buildNode(JSONObject node){
			//List<Class>不能扩展,所以定义一个元素是JSONObject的数组
			List<JSONObject> ja=new ArrayList<>();
			List<DictClassGoods> children = DictClassGoods.INSTANCE.query("pid=?", node.get("id"));
			for (DictClassGoods sysMenu : children) {
				ja.add(JSONObject.fromObject(sysMenu));
			}
			if (!ja.isEmpty()) {
				for (int i = 0; i < ja.size(); i++) {
					buildNode(ja.get(i));
				}
				node.put("children", ja);
			} 
			return node;
		}
		
		public static void main(String[] args) {
			TreeCreator a=new TreeCreator();
			JSONArray ja =a.buildTree();
			System.out.println(ja);
		}
		
	}
