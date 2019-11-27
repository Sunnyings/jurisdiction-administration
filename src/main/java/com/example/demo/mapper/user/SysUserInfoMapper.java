package com.example.demo.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.demo.mapper.BaseMapper;
import com.example.demo.model.SysUserInfo;
@Mapper
public interface SysUserInfoMapper extends BaseMapper<SysUserInfo>{
    int deleteByPrimaryKey(@Param("userUuid")String userUuid);

    int insert(SysUserInfo record);

    int insertSelective(SysUserInfo record);

    SysUserInfo selectByPrimaryKey(String userUuid);

    int updateByPrimaryKeySelective(SysUserInfo record);

    int updateByPrimaryKey(SysUserInfo record);
}