package com.cms.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cms.bean.GridDataModel;
import com.cms.bean.GridSaveModel;
import com.cms.model.sys.SysRole;
import com.cms.model.sys.SysUser;
import com.cms.util.biz.PageFactory;

import my.dao.pool.DBManager;
import my.util.MD5Util;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cms")
public class SysParameterController extends BaseController{
	
	@RequestMapping("parameter/list")
	public String desktop(Model m) {
		String menuid=param("menuid","");
		m.addAttribute("menuid", menuid);
		return "cms/parameter/list";
	}
	
}
