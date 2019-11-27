package com.example.demo.mapper.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.demo.mapper.BaseMapper;
import com.example.demo.model.SysMenuInfo;
import com.example.demo.model.SysResourcesInfo;
import com.example.demo.model.SysUserInfo;
@Mapper
public interface SysMenuInfoMapper extends BaseMapper<SysMenuInfo> {
	
	 List<String> getRole(@Param("userUuid") String userUuid);
	
	 List<SysResourcesInfo> selectSysResourcesInfo();
	 
	 SysUserInfo findByUsername(@Param("username")String username);
	
	 List<SysMenuInfo> getAllTopMenu();
	 
	Integer getMenuSeq(@Param("pid")String pid);

	 List<SysMenuInfo> getAllChildMenu();

	 List<SysMenuInfo> getTopMenu(@Param("userUuid")String userUuid);

	 List<SysMenuInfo> getChildMenu(@Param("userUuid")String userUuid);
	 
	 Integer delMenu(@Param("url")String url);
	 
}
