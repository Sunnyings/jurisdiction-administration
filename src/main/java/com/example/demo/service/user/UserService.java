package com.example.demo.service.user;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.SysUserInfo;
import com.github.pagehelper.PageInfo;

public interface UserService {
	
	PageInfo<SysUserInfo> page(SysUserInfo info);
	
	int	addUser(JSONObject params);
	
	Integer del(SysUserInfo info);
}
