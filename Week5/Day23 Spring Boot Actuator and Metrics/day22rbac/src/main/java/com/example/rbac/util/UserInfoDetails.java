package com.example.rbac.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.rbac.entity.UserInfo;

public class UserInfoDetails implements UserDetails {

	private String username; // Changed from 'name' to 'email' for clarity
	private String password;
	private List<GrantedAuthority> authorities;

	public UserInfoDetails(UserInfo userInfo) {
		this.username = userInfo.getEmail(); // Use email as username
		this.password = userInfo.getPassword();
//		this.authorities = userInfo.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//		this.authorities = userInfo.getRoles().stream().map(SimpleGrantedAuthority::new).toList();
//		this.authorities = userInfo.getRoles();
		this.authorities = new ArrayList<>(userInfo.getRoles());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}
}
