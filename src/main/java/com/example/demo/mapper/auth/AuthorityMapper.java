package com.example.demo.mapper.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.mapper.BaseMapper;
import com.example.demo.model.SysResourcesInfo;
@Mapper
public interface AuthorityMapper extends BaseMapper<SysResourcesInfo> {

	List<SysResourcesInfo>getResouList(@Param("roleUuid")String roleUuid);
}
