package ru.cs.ifmo.ligos.db.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.repositories.OrganizationRepository;
import ru.cs.ifmo.ligos.exception.CustomException;
import ru.cs.ifmo.ligos.security.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static ru.cs.ifmo.ligos.db.entities.Role.ROLE_CLIENT;
import static ru.cs.ifmo.ligos.db.entities.Role.ROLE_ORGANIZATION;

@Service
public class OrganizationService {

	private final Logger logger = LogManager.getLogger(UserService.class);

	private final OrganizationRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public OrganizationService(OrganizationRepository repository,
							   PasswordEncoder passwordEncoder,
							   JwtTokenProvider jwtTokenProvider,
							   AuthenticationManager authenticationManager) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
	}

	public OrganizationEntity getUserByEmail(String email){
		return repository.findByEmail(email);
	}

	public void save(OrganizationEntity organization){
		repository.save(organization);
	}

	public String signin(String email, String password) {
		try {

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
			authenticationManager.authenticate(token);
			logger.debug( "[SIGNIN  - ORGANIZATION] " + email + " SUCCESS");
			return jwtTokenProvider.createToken(email, Collections.singletonList(ROLE_ORGANIZATION));
		} catch (AuthenticationException e) {
			logger.debug( "[SIGNIN  - ORGANIZATION] " + email + " FAILURE");
			throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public String signup(OrganizationEntity organization) {
		if (!repository.existsByEmail(organization.getEmail())) {
			organization.setPassword(passwordEncoder.encode(organization.getPassword()));

			logger.debug( "[SIGNUP - ORGANIZATION] " + organization.getEmail() + " SUCCESS");

			repository.save(organization);
			return jwtTokenProvider.createToken(organization.getEmail(), Collections.singletonList(ROLE_ORGANIZATION));
		} else {
			logger.debug( "[SIGNUP  - ORGANIZATION] " + organization.getEmail() + " FAILURE");
			throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public void delete(String email){
		repository.deleteByEmail(email);
	}

	public OrganizationEntity whoami(HttpServletRequest req) {
		return repository.findByEmail(jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(req)));
	}

	public String refresh(String email) {
		return jwtTokenProvider.createToken(email, Collections.singletonList(ROLE_ORGANIZATION));
	}

}
