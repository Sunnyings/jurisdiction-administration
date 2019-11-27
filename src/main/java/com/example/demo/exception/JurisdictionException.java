package com.example.demo.exception;

public class JurisdictionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 空构造
	 */
	public JurisdictionException() {
		super("HlException 异常");
	}

	/**
	 * 
	 * 自定义错误日志
	 * 
	 * @param e
	 */
	public JurisdictionException(String e) {
		super(e);
	}

	/**
	 * 只抛错误信息
	 * 
	 * @param e
	 */
	public JurisdictionException(Throwable e) {
		super(e);
	}

	/**
	 * 两者皆抛
	 * 
	 * @param er
	 * @param e
	 */
	public JurisdictionException(String er, Throwable e) {
		super(er, e);
	}
}
