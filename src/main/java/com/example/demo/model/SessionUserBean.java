package com.example.demo.model;

import java.util.Date;

public class SessionUserBean {

	private String id;
	
	private String account;
	
	private String realName;
	
	private String lastIp;
	
	private Date lastTime;
	
	private String lastCity;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getLastCity() {
		return lastCity;
	}

	public void setLastCity(String lastCity) {
		this.lastCity = lastCity;
	}
	
	
}
