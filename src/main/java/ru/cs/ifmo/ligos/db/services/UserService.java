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
import ru.cs.ifmo.ligos.security.jwt.JwtAuthenticationResponse;
import ru.cs.ifmo.ligos.security.jwt.JwtTokenProvider;

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
	private final RoleRepository roleRepository;

	@Autowired
	public UserService(UserRepository repository, PasswordEncoder passwordEncoder,
					   JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager,
					   RoleRepository roleRepository) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
		this.roleRepository = roleRepository;
	}

	public Optional<UsersEntity> getUserByEmail(String email){
		return repository.findByEmail(email);
	}

	public void save(UsersEntity user){
		repository.save(user);
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
/*

		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new CustomException("User Role not set.", HttpStatus.BAD_REQUEST));

		//user.setRoles(Collections.singleton(userRole));
*/

		UsersEntity result = repository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getEmail()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));

	}

}
