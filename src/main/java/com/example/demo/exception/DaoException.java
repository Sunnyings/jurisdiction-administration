package com.example.demo.exception;

import org.springframework.dao.DataAccessException;

public class DaoException extends DataAccessException {

	/*** serial id */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 空构造
	 */
	public DaoException(){
		super("DaoException 异常");
	}
	
	/**
	 * 
	 * 自定义错误日志
	 * @param e
	 */
	public DaoException(String e){
		super(e);
	}
	

	/**
	 * 两者皆抛
	 * @param er
	 * @param e
	 */
	public DaoException(String er,Throwable e){
		super(er, e);
	}
}
