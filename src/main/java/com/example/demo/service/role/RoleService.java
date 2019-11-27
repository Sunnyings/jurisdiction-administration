package com.example.demo.service.role;

import java.util.List;

import com.example.demo.model.SysRoleInfo;
import com.github.pagehelper.PageInfo;

public interface RoleService {
	PageInfo<SysRoleInfo> page(SysRoleInfo info);
	
	Integer add(SysRoleInfo info);
	
	Integer give(List<String> resourcesInfos,String roleUuid);
	
	List<SysRoleInfo> getRoleList();
}
