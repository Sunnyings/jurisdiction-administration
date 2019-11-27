package com.example.demo.model;

import java.util.Date;
public class SysMenuInfo extends BaseModel{
	private static final long serialVersionUID = 1323464749528701937L;
	private String id;
	private String menuUuid;
	private String menuName;
	private String url;
	private String pid;
	private Integer seq;
	private Date creationTime;
	private Date updateTime;
	private String isDelete;
	private String levelMenu;
	
	public String getLevelMenu() {
		return levelMenu;
	}
	public void setLevelMenu(String levelMenu) {
		this.levelMenu = levelMenu;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuUuid() {
		return menuUuid;
	}
	public void setMenuUuid(String menuUuid) {
		this.menuUuid = menuUuid;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}
