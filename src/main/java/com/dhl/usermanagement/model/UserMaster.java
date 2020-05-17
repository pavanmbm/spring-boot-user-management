package com.dhl.usermanagement.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMaster implements UserDetails{

	private String userId;
	private String password;
	private List<Role> authorities;
	
	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	 public String getPassword() {
		return password;
	}
	 
	 public String getUserId() {
		return userId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
