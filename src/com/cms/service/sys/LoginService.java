package com.cms.service.sys;

import org.springframework.stereotype.Service;

import com.cms.model.sys.SysUser;

@Service
public class LoginService {
	
	public SysUser getUserByName(String username){
		return SysUser.INSTANCE.queryOne("username=?", username);
	}
}
