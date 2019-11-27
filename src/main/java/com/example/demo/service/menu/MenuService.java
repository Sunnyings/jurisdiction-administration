package com.example.demo.service.menu;

import com.example.demo.model.SysMenuInfo;
import com.github.pagehelper.PageInfo;

public interface MenuService {

	PageInfo<SysMenuInfo> page(SysMenuInfo info);
	
	int add(SysMenuInfo info);
}
