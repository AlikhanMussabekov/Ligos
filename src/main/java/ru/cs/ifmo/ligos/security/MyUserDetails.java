package ru.cs.ifmo.ligos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.cs.ifmo.ligos.db.entities.Role;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;

import java.util.Collections;

@Service
public class MyUserDetails implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public MyUserDetails(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final UsersEntity user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User '" + email + "' not found");
		}

		return org.springframework.security.core.userdetails.User//
				.withUsername(email)//
				.password(user.getPassword())//
				.authorities(Collections.singletonList(Role.ROLE_CLIENT))//
				.accountExpired(false)//
				.accountLocked(false)//
				.credentialsExpired(false)//
				.disabled(false)//
				.build();
	}

}