package com.example.demo.service.sys.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.JurisdictionException;
import com.example.demo.mapper.user.SysUserInfoMapper;
import com.example.demo.model.SysUserInfo;
import com.example.demo.service.sys.SysService;
import com.example.demo.util.PwdUtils;
@Service
public class SysServiceImpl implements SysService{
	@Autowired
	SysUserInfoMapper sysUserInfoMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public Boolean updatePassword(String userUuid, String oldPass, String newPass) throws JurisdictionException {
		// TODO Auto-generated method stub
		SysUserInfo info=sysUserInfoMapper.selectByPrimaryKey(userUuid);//查询登陆用户信息
		if (info != null) {
			if (!bCryptPasswordEncoder.matches(oldPass,info.getPassword())) {//校验原密码如数据库所存密码是否一致
				throw new JurisdictionException("原始密码错误!");
			} else {
				//校验成功
				info.setPassword(PwdUtils.genUserPwd(oldPass));
				info.setUpdateDate(new Date());
				return sysUserInfoMapper.update(info)>0?true:false;
			}
		} else {
			throw new JurisdictionException("用户不存在!");
		}
	}
}
