package ru.cs.ifmo.ligos.db.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.cs.ifmo.ligos.db.entities.Role;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import ru.cs.ifmo.ligos.exception.CustomException;
import ru.cs.ifmo.ligos.security.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.cs.ifmo.ligos.db.entities.Role.ROLE_CLIENT;

@Service
public class UserService {

	private final Logger logger = LogManager.getLogger(UserService.class);

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public UserService(UserRepository repository, PasswordEncoder passwordEncoder,
					   JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
	}

	public UsersEntity getUserByEmail(String email){
		return repository.findByEmail(email);
	}

	public void save(UsersEntity user){
		repository.save(user);
	}

	public String signin(String email, String password) {
		try {

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
			authenticationManager.authenticate(token);
			logger.debug( "[SIGNIN] " + email + " SUCCESS");
			return jwtTokenProvider.createToken(email, Collections.singletonList(ROLE_CLIENT));
		} catch (AuthenticationException e) {
			logger.debug( "[SIGNIN] " + email + " FAILURE");
			throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public String signup(UsersEntity user) {
		if (!repository.existsByEmail(user.getEmail())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			logger.debug( "[SIGNUP] " + user.getEmail() + " SUCCESS");

			repository.save(user);
			return jwtTokenProvider.createToken(user.getEmail(), Collections.singletonList(ROLE_CLIENT));
		} else {
			logger.debug( "[SIGNUP] " + user.getEmail() + " FAILURE");
			throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public void delete(String email){
		repository.deleteByEmail(email);
	}

	public UsersEntity whoami(HttpServletRequest req) {
		return repository.findByEmail(jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(req)));
	}

	public String refresh(String email) {
		return jwtTokenProvider.createToken(email, Collections.singletonList(ROLE_CLIENT));
	}

}
