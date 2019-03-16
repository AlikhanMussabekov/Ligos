package ru.cs.ifmo.ligos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;
import ru.cs.ifmo.ligos.db.entities.Role;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.repositories.OrganizationRepository;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;

import java.util.Collections;

@Service("userDetails")
@Qualifier("userDetails")
public class MyUserDetails implements UserDetailsService {

	private final UserRepository userRepository;
	private final OrganizationRepository organizationRepository;

	@Autowired
	public MyUserDetails(UserRepository userRepository,
						 OrganizationRepository organizationRepository) {
		this.userRepository = userRepository;
		this.organizationRepository = organizationRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		final UsersEntity user = userRepository.findByEmail(email);
		final OrganizationEntity organization = organizationRepository.findByEmail(email);

		if (user != null){
			return User
					.withUsername(email)
					.password(user.getPassword())
					.authorities(Collections.singletonList(Role.ROLE_CLIENT))
					.accountExpired(false)
					.accountLocked(false)
					.credentialsExpired(false)
					.disabled(false)
					.build();
		} else if (organization != null){
			return User
					.withUsername(email)
					.password(organization.getPassword())
					.authorities(Collections.singletonList(Role.ROLE_ORGANIZATION))
					.accountExpired(false)
					.accountLocked(false)
					.credentialsExpired(false)
					.disabled(false)
					.build();
		} else {
			throw new UsernameNotFoundException("User '" + email + "' not found");
		}
	}

}