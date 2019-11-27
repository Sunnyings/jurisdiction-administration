package com.example.demo.mapper.role;

import org.apache.ibatis.annotations.Param;

import com.example.demo.mapper.BaseMapper;
import com.example.demo.model.SysUserRole;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole>{
    int deleteByPrimaryKey(@Param("userUuid")String userUuid);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}