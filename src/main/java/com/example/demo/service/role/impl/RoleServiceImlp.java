package com.example.demo.service.role.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.role.SysRoleInfoMapper;
import com.example.demo.mapper.roleResources.SysRoleResourcesMapper;
import com.example.demo.model.SysRoleInfo;
import com.example.demo.model.SysRoleResources;
import com.example.demo.service.role.RoleService;
import com.example.demo.util.GenerateCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class RoleServiceImlp implements RoleService{
	@Autowired
	SysRoleInfoMapper sysRoleInfoMapper;
	
	@Autowired
	SysRoleResourcesMapper sysRoleResourcesMapper;
	@Override
	public PageInfo<SysRoleInfo> page(SysRoleInfo info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		return new PageInfo<SysRoleInfo>(sysRoleInfoMapper.query(info));
	}

	@Override
	public Integer add(SysRoleInfo info) {
		info.setId(GenerateCode.genIdByIncrease());
		info.setRoleUuid(UUID.randomUUID().toString());
		info.setCreationTime(new Date());
		info.setUpdateTime(new Date());
		info.setIsDeleted(false);
		return sysRoleInfoMapper.insert(info);
	}

	@Override
	public Integer give(List<String> resourcesInfos, String roleUuid) {
		sysRoleResourcesMapper.logicDel(roleUuid,new Date(),1);
			for (String resourcesUuid : resourcesInfos) {
				SysRoleResources srr=new SysRoleResources();
				srr.setId(GenerateCode.genIdByIncrease());
				srr.setRoleUuid(roleUuid);
				srr.setResourcesUuid(resourcesUuid);
				srr.setCreationTime(new Date());
				srr.setUpdateTime(new Date());
				srr.setIsDeleted(false);
				sysRoleResourcesMapper.insert(srr);
		}
		return 0;
	}

	@Override
	public List<SysRoleInfo> getRoleList() {
		return sysRoleInfoMapper.getRoleList();
	}
}
