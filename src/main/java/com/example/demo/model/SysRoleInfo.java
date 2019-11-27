package com.example.demo.model;


import java.util.Date;

public class SysRoleInfo extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5128057815717751606L;

	private Long id;

    private String roleUuid;

    private String roleRname;

    private String roleCode;

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

    public String getRoleRname() {
        return roleRname;
    }

    public void setRoleRname(String roleRname) {
        this.roleRname = roleRname == null ? null : roleRname.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
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