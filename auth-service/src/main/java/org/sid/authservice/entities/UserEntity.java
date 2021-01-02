package org.sid.authservice.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleEntity> roleEntity=new ArrayList<>();
    
    public UserEntity() {
		super();
	}
	public UserEntity(Long id, String username, String password, Collection<RoleEntity> roleEntity) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roleEntity = roleEntity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<RoleEntity> getAppRoles() {
		return roleEntity;
	}
	public void setAppRoles(Collection<RoleEntity> roleEntity) {
		this.roleEntity = roleEntity;
	}
    
    
    
    
}
