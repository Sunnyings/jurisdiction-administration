package com.example.demo.service.auth.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.auth.AuthorityMapper;
import com.example.demo.mapper.menu.SysMenuInfoMapper;
import com.example.demo.mapper.roleResources.SysRoleResourcesMapper;
import com.example.demo.model.SysResourcesInfo;
import com.example.demo.service.auth.AuthorityService;
import com.example.demo.util.GenerateCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class AuthorityServiceImpl implements AuthorityService {

	private  final Logger logger=LoggerFactory.getLogger(Logger.class);
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	@Autowired
	private SysRoleResourcesMapper sysRoleResourcesMapper;
	@Autowired
	private SysMenuInfoMapper sysMenuInfoMapper;
	
	@Override
	public PageInfo<SysResourcesInfo> page(SysResourcesInfo info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		return new PageInfo<SysResourcesInfo>(authorityMapper.query(info));
	}
	@Override
	public PageInfo<SysResourcesInfo> getResouList(String roleUuid) {
		return new PageInfo<SysResourcesInfo>(authorityMapper.getResouList(roleUuid));
	}
	

	@Override
	public Integer add(SysResourcesInfo info) {
		info.setId(GenerateCode.genIdByIncrease());
		info.setResourcesUuid(UUID.randomUUID().toString());
		info.setCreationTime(new Date());
		info.setUpdateTime(new Date());
		info.setIsDelete(0);
		return authorityMapper.insert(info);
	}
	@Override
	public Integer del(SysResourcesInfo info) {
		try {
			authorityMapper.del(info);//逻辑删除资源
			sysRoleResourcesMapper.delRoleResources(info.getResourcesUuid());//逻辑删除角色与资源关系
			sysMenuInfoMapper.delMenu(info.getResourcesUrl());//删除相同url的菜单
		} catch (Exception e) {
		return 1;
		}
		return 0;
		
	}

	

}
