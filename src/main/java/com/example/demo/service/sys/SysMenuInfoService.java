package com.example.demo.service.sys;

import java.util.List;

import com.example.demo.model.SysMenuInfo;

public interface SysMenuInfoService {
	List<SysMenuInfo> getAllTopMenu();
	
	List<SysMenuInfo> getAllChildMenu();
	
	List<SysMenuInfo> getTopMenu(String userUuid);
	
	List<SysMenuInfo> getChildMenu(String userUuid);
}
