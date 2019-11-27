package com.example.demo.util;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.manage.Constants;
import com.example.demo.model.SessionUserBean;



public class SessionUtil {

	/**
	 * 获取当前登录用户
	 * @param request
	 * @return
	 */
	public static SessionUserBean getSessionUser(HttpServletRequest request){
		
		return (SessionUserBean) request.getSession().getAttribute(Constants.USER_INFO_SESSION_KEY);
	}
}
