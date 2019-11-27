package com.example.demo.service.sys;

import com.example.demo.exception.JurisdictionException;

public interface SysService {

	public Boolean updatePassword(String userUuid, String oldPass, String newPass) throws JurisdictionException;
}
