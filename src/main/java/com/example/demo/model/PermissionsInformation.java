package com.example.demo.model;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;

public class PermissionsInformation extends BaseModel{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 5193837231558675004L;

	private ArrayList<GrantedAuthority> authoritieList;
	    
	    private String[] url;
	    private String roleCode;
		public ArrayList<GrantedAuthority> getAuthoritieList() {
			return authoritieList;
		}

		public String getRoleCode() {
			return roleCode;
		}

		public void setRoleCode(String roleCode) {
			this.roleCode = roleCode;
		}

		public void setAuthoritieList(ArrayList<GrantedAuthority> authoritieList) {
			this.authoritieList = authoritieList;
		}

		public String[] getUrl() {
			return url;
		}

		public void setUrl(String[] url) {
			this.url = url;
		}
}
