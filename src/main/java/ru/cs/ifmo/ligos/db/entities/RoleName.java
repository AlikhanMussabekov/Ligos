package ru.cs.ifmo.ligos.db.entities;

import org.springframework.security.core.GrantedAuthority;

public enum RoleName implements GrantedAuthority {
	ROLE_ADMIN, ROLE_USER, ROLE_ORGANIZATION;

	public String getAuthority() {
		return name();
	}

}