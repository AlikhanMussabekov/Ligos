package ru.cs.ifmo.ligos.db.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_ADMIN, ROLE_CLIENT, ROLE_ORGANIZATION;

	public String getAuthority() {
		return name();
	}

}