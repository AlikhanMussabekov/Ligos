package ru.cs.ifmo.ligos.db.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.repositories.RoleRepository;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;
import ru.cs.ifmo.ligos.dto.ApiResponse;
import ru.cs.ifmo.ligos.exception.CustomException;
import ru.cs.ifmo.ligos.security.jwt.JwtAuthenticationResponse;
import ru.cs.ifmo.ligos.security.jwt.JwtTokenProvider;
import ru.cs.ifmo.ligos.security.oauth2.UserPrincipal;

import java.net.URI;
import java.util.Optional;

@Service
@SuppressWarnings({"Duplicates", "unchecked"})
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

	public ResponseEntity<?> signin(String email, String password) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	public ResponseEntity<?> signup(UsersEntity user) {
		if(repository.existsByEmail(user.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		UsersEntity result = repository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/users/{id}")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));

	}

	public ResponseEntity<?> getCurrentUser(String email){
		return ResponseEntity.ok(repository.findByEmail(email)
				.orElseThrow(() -> new CustomException("User not found",HttpStatus.BAD_REQUEST )));
	}

}
