package org.sid.authservice.entities;

import lombok.Data;

@Data
public class RoleForm {
    private String username;
    private String role;
    
    public RoleForm(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}
    public RoleForm() {
		super();
	}
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
    
    
}
