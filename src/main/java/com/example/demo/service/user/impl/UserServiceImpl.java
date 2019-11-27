package com.example.demo.service.user.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.role.SysUserRoleMapper;
import com.example.demo.mapper.user.SysUserInfoMapper;
import com.example.demo.model.SysUserInfo;
import com.example.demo.model.SysUserRole;
import com.example.demo.service.user.UserService;
import com.example.demo.util.GenerateCode;
import com.example.demo.util.PwdUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	SysUserInfoMapper sysUserInfoMapper;
	
	@Autowired 
	SysUserRoleMapper sysUserRoleMapper;
	@Override
	public PageInfo<SysUserInfo> page(SysUserInfo info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		return new PageInfo<SysUserInfo>(sysUserInfoMapper.query(info));
	}
	@Override
	public int addUser(JSONObject params) {
		SysUserInfo info=new SysUserInfo();
		info.setId(GenerateCode.genIdByIncrease());
		info.setUserUuid(UUID.randomUUID().toString());
		info.setPassword(PwdUtils.genUserPwd(params.getString("password")));
		info.setUsername(params.getString("username"));
		info.setTelephone(params.getString("telephone"));
		String[] strings = new Gson().fromJson(params.getString("roleUuids"),String[].class); 
		info.setRoleUuids(strings);
		info.setIsDelete("0");
		SysUserInfo sysUserInfo=sysUserInfoMapper.get(info);
		if(sysUserInfo==null) {
		int code=sysUserInfoMapper.insert(info);
		if(code>0){
			for (String uuid : info.getRoleUuids()) {
				SysUserRole role=new SysUserRole();
				role.setId(GenerateCode.genIdByIncrease());
				role.setRoleUuid(uuid);
				role.setUserUuid(info.getUserUuid());
				role.setCreationTime(new Date());
				role.setUpdateTime(new Date());
				role.setIsDelete("0");
				sysUserRoleMapper.insert(role);
			}
		}
		return 0;
		}else {
			return 1;
		}
	}
	@Override
	public Integer del(SysUserInfo info) {
		try {
			sysUserInfoMapper.deleteByPrimaryKey(info.getUserUuid());
			sysUserRoleMapper.deleteByPrimaryKey(info.getUserUuid());
		} catch (Exception e) {
			return 1;
		}
		return 0;
	}

}
