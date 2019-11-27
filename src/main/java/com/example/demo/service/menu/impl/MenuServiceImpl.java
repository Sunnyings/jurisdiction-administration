package com.example.demo.service.menu.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.auth.AuthorityMapper;
import com.example.demo.mapper.menu.SysMenuInfoMapper;
import com.example.demo.model.SysMenuInfo;
import com.example.demo.model.SysResourcesInfo;
import com.example.demo.service.menu.MenuService;
import com.example.demo.util.GenerateCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	SysMenuInfoMapper sysMenuInfoMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	@Override
	public PageInfo<SysMenuInfo> page(SysMenuInfo info) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		return new PageInfo<SysMenuInfo>(sysMenuInfoMapper.query(info));
	}
	@Override
	public int add(SysMenuInfo info) {
		int code=1;
		info.setCreationTime(new Date());
		info.setUpdateTime(new Date());
		info.setMenuUuid(UUID.randomUUID().toString());
		info.setId(GenerateCode.genIdByIncrease().toString());
		info.setIsDelete("0");
		if(info.getUrl()!=null&&info.getPid()!=null) {
			Integer item=sysMenuInfoMapper.getMenuSeq(info.getPid());
			if (item==null) {
				info.setSeq(1);
			}else {
				info.setSeq(item+1);	
			}
			sysMenuInfoMapper.insert(info);//插入二级菜单
			SysResourcesInfo resourcesInfo=new SysResourcesInfo();
			resourcesInfo.setId(GenerateCode.genIdByIncrease());
			resourcesInfo.setResourcesUuid(UUID.randomUUID().toString());
			resourcesInfo.setCreationTime(new Date());
			resourcesInfo.setUpdateTime(new Date());
			resourcesInfo.setResourcesName(info.getMenuName());
			resourcesInfo.setResourcesUrl(info.getUrl());
			resourcesInfo.setIsDelete(0);
			authorityMapper.insert(resourcesInfo);//添加菜单权限资源
			code=0;
		}else {
			info.setPid("-1");
			Integer item=sysMenuInfoMapper.getMenuSeq(info.getPid());
			info.setSeq(item+1);
			sysMenuInfoMapper.insert(info);
			code=0;
		}
		return code;
	}

	
	
}
