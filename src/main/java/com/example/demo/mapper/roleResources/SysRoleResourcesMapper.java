package com.example.demo.mapper.roleResources;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.demo.mapper.BaseMapper;
import com.example.demo.model.SysRoleResources;

public interface SysRoleResourcesMapper extends BaseMapper<SysRoleResources>{
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleResources record);

    int insertSelective(SysRoleResources record);

    Integer delRoleResources(@Param("resourcesUuid")String resourcesUuid);
    
    SysRoleResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleResources record);

    int updateByPrimaryKey(SysRoleResources record);
    
    int  logicDel(@Param("roleUuid")String roleUuid,@Param("updateTime")Date updateTime,@Param("isDelete")Integer isDelete);
    
}