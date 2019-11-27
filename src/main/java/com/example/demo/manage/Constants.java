package com.example.demo.manage;

import java.util.Date;

import com.example.demo.util.DateUtil;

public class Constants {

	public final static String STATEMENT_SESSION_KEY="STATEMENT_SESSION_KEY";
	
	public final static String LOGIN_CODE_SESSION_KEY="LOGIN_CODE";
	
	public final static String ROOT_INFO_SESSION_KEY="ROOT_INFO_SESSION";
	
	public final static String USER_INFO_SESSION_KEY="USER_INFO_SESSION";
	
	public final static String AJAX_HEADER="X-Requested-With";
	
	public final static String AJAX_HEADER_VALUE="XMLHttpRequest";
	
	public final static String AJAX_CHARACTER_ENCODING="UTF-8";
	
	public final static String JSON_CONTENT_TYPE="application/json;charset=utf-8";
	
	public final static Integer DEFAULT_PAGE_SIZE=10;
	
	public final static Integer BOOLEAN_TRUE=1;
	
	public final static Integer BOOLEAN_FALSE=0;
	
	public final static String TREE_ROOT="-1";
	
	public final static int RESULT_CODE_SUCCESS=0;
	
	public final static int RESULT_CODE_FAILURE=1;
	
	//公司
	public final static int ORG_TYPE_C=0;
	
	//部门
	public final static int ORG_TYPE_D=1;
	
	public final static String OPERATOR_STATUS_FRZ="FRZ";
	
	public final static String OPERATOR_STATUS_NORMAL="0";
	
	public enum AIRLINE_STEWARDESS_STATE{
		OFFLINE,ONLINE
	}
	
	public enum PAIR_STATE{
		PROCESSED,UNDISPOSED,PAIRED,UNPAIRED
	}
	
	public static void main(String[] args) {
		Date d1=new Date();
		System.out.println(d1.getTime());
		d1.setTime(d1.getTime()+180*1000);
		System.out.println(d1.getTime());
		System.out.println(DateUtil.format(d1, "yyyy-MM-dd HH:mm:ss"));
	}
}
