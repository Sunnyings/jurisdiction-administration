package com.example.demo.mapper.role;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.mapper.BaseMapper;
import com.example.demo.model.SysRoleInfo;
@Mapper
public interface SysRoleInfoMapper extends BaseMapper<SysRoleInfo>{
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysRoleInfo record);

    SysRoleInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleInfo record);

    int updateByPrimaryKey(SysRoleInfo record);
    
    List<SysRoleInfo> getRoleList();
    
}