package com.example.demo.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
public class SysResourcesInfo extends PermissionsInformation{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5561077211879718377L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String resourcesUuid;
	private String resourcesName;
	private String resourcesUrl;
	private Date creationTime;
	private Date updateTime;
	@JsonProperty("LAY_CHECKED")  
	private boolean LAY_CHECKED;
	private Integer isDelete;
	@JsonIgnore
	public boolean getLAY_CHECKED() {
		return LAY_CHECKED;
	}
	@JsonIgnore
	public void setLAY_CHECKED(boolean lAY_CHECKED) {
		LAY_CHECKED = lAY_CHECKED;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResourcesUuid() {
		return resourcesUuid;
	}
	public void setResourcesUuid(String resourcesUuid) {
		this.resourcesUuid = resourcesUuid;
	}
	public String getResourcesName() {
		return resourcesName;
	}
	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}
	public String getResourcesUrl() {
		return resourcesUrl;
	}
	public void setResourcesUrl(String resourcesUrl) {
		this.resourcesUrl = resourcesUrl;
	}
	
}
