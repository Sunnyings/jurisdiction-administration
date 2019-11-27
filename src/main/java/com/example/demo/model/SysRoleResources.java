package com.example.demo.model;

import java.util.Date;

public class SysRoleResources extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6917768003275149097L;

	private Long id;

    private String roleUuid;

    private String resourcesUuid;

    private Date creationTime;

    private Date updateTime;

    private String isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleUuid() {
        return roleUuid;
    }

    public void setRoleUuid(String roleUuid) {
        this.roleUuid = roleUuid == null ? null : roleUuid.trim();
    }

    public String getResourcesUuid() {
        return resourcesUuid;
    }

    public void setResourcesUuid(String resourcesUuid) {
        this.resourcesUuid = resourcesUuid == null ? null : resourcesUuid.trim();
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
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}