package com.example.demo.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.menu.SysMenuInfoMapper;
import com.example.demo.model.SysMenuInfo;
import com.example.demo.service.sys.SysMenuInfoService;

@Service
public class SysMenuInfoServiceImpl implements SysMenuInfoService{
	@Autowired
	private SysMenuInfoMapper sysMenuInfoMapper;

	@Override
	public List<SysMenuInfo> getAllTopMenu() {
		// TODO Auto-generated method stub
		return  sysMenuInfoMapper.getAllTopMenu();
	}

	@Override
	public List<SysMenuInfo> getAllChildMenu() {
		// TODO Auto-generated method stub
		return sysMenuInfoMapper.getAllChildMenu();
	}

	@Override
	public List<SysMenuInfo> getTopMenu(String userUuid) {
		// TODO Auto-generated method stub
		return sysMenuInfoMapper.getTopMenu(userUuid);
	}

	@Override
	public List<SysMenuInfo> getChildMenu(String userUuid) {
		// TODO Auto-generated method stub
		return sysMenuInfoMapper.getChildMenu(userUuid);
	}
}
