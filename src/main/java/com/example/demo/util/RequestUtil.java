package com.example.demo.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static void setSearchMap(HttpServletRequest request,String... searchs){
		for (String search:searchs) {
			request.setAttribute(search, request.getParameter(search));
		}
	}
}
