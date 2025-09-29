package com.example.rbac.entity;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public List<? extends GrantedAuthority> getRoles() {
	    return roles.stream()
	            .map(SimpleGrantedAuthority::new)
	            .toList();
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
