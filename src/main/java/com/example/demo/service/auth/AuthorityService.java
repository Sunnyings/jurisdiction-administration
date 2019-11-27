package com.example.demo.service.auth;

import com.example.demo.model.SysResourcesInfo;
import com.github.pagehelper.PageInfo;

public interface AuthorityService {

	PageInfo<SysResourcesInfo> page(SysResourcesInfo info);
	
	Integer add(SysResourcesInfo info);
	
	PageInfo<SysResourcesInfo> getResouList(String roleUuid);
	
	Integer del(SysResourcesInfo info);
}
