package com.example.demo.model;

import java.util.Date;

public class SysUserInfo extends PermissionsInformation{
		/**
	 * 
	 */
	private static final long serialVersionUID = 8515872344847812024L;
	    private Long id;
	    private String userUuid;   //用户UUID
	    private String username;    //用户名
	    private String userNickName; //用户昵称
	    private String password;    //用户密码
	    private String email;       //用户邮箱
	    private String telephone;   //电话号码
	    private String role;        //用户角色
	    private String image;       //用户头像
	    private String lastIp;     //上次登录IP
	    private Date lastTime;   //上次登录时间
	    private String[] roleUuids;
	    private String isDelete;
	    
		public String getIsDelete() {
			return isDelete;
		}

		public void setIsDelete(String isDelete) {
			this.isDelete = isDelete;
		}

		public String[] getRoleUuids() {
			return roleUuids;
		}

		public void setRoleUuids(String[] roleUuids) {
			this.roleUuids = roleUuids;
		}

		public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getUserUuid() {
	        return userUuid;
	    }

	    public void setUserUuid(String userUuid) {
	        this.userUuid = userUuid;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

		public String getUserNickName() {
			return userNickName;
		}

		public void setUserNickName(String userNickName) {
			this.userNickName = userNickName;
		}

		public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getTelephone() {
	        return telephone;
	    }

	    public void setTelephone(String telephone) {
	        this.telephone = telephone;
	    }

	    public String getRole() {
	        return role;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }

	    public String getImage() {
	        return image;
	    }

	    public void setImage(String image) {
	        this.image = image;
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

	    @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", userUuid='" + userUuid + '\'' +
	                ", username='" + username + '\'' +
	                ", password='" + password + '\'' +
	                ", email='" + email + '\'' +
	                ", telephone='" + telephone + '\'' +
	                ", role='" + role + '\'' +
	                ", image='" + image + '\'' +
	                ", lastIp='" + lastIp + '\'' +
	                ", lastTime='" + lastTime + '\'' +
	                '}';
	    }
}
