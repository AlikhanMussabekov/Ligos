package ru.cs.ifmo.ligos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;
import ru.cs.ifmo.ligos.db.entities.RoleName;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.repositories.OrganizationRepository;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service("userDetails")
@Qualifier("userDetails")
public class MyUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	private final OrganizationRepository organizationRepository;

	@Autowired
	public MyUserDetailsService(UserRepository userRepository,
								OrganizationRepository organizationRepository) {
		this.userRepository = userRepository;
		this.organizationRepository = organizationRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		final Optional<UsersEntity> user = userRepository.findByEmail(email);
		final Optional<OrganizationEntity> organization = organizationRepository.findByEmail(email);

		if (user.isPresent()){
			return User
					.withUsername(email)
					.password(user.get().getPassword())
					.authorities(Collections.singletonList(RoleName.ROLE_USER))
					.accountExpired(false)
					.accountLocked(false)
					.credentialsExpired(false)
					.disabled(false)
					.build();
		} else if (organization.isPresent()){
			return User
					.withUsername(email)
					.password(organization.get().getPassword())
					.authorities(Collections.singletonList(RoleName.ROLE_ORGANIZATION))
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