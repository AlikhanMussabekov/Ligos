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
import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;
import ru.cs.ifmo.ligos.db.entities.TrainerEntity;
import ru.cs.ifmo.ligos.db.repositories.OrganizationRepository;
import ru.cs.ifmo.ligos.db.repositories.RoleRepository;
import ru.cs.ifmo.ligos.db.repositories.TrainerRepository;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;
import ru.cs.ifmo.ligos.dto.ApiResponse;
import ru.cs.ifmo.ligos.exception.CustomException;
import ru.cs.ifmo.ligos.security.jwt.JwtAuthenticationResponse;
import ru.cs.ifmo.ligos.security.jwt.JwtTokenProvider;

import java.net.URI;
import java.util.Optional;

@Service
@SuppressWarnings({"Duplicates", "unchecked"})
public class OrganizationService {

	private final Logger logger = LogManager.getLogger(UserService.class);

	private final OrganizationRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;
	private final TrainerRepository trainerRepository;
	private final UserRepository userRepository;

	@Autowired
	public OrganizationService(OrganizationRepository repository,
							   PasswordEncoder passwordEncoder,
							   JwtTokenProvider jwtTokenProvider,
							   AuthenticationManager authenticationManager,
							   TrainerRepository trainerRepository,
							   UserRepository userRepository) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
		this.trainerRepository = trainerRepository;
		this.userRepository = userRepository;
	}

	public OrganizationEntity getUserByEmail(String email){
		return repository.findByEmail(email).get();
	}

	public void save(OrganizationEntity organization){
		repository.save(organization);
	}

	public ResponseEntity<?> signin(String email, String password) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	public ResponseEntity<?> signup(OrganizationEntity organization) {
		if(repository.existsByEmail(organization.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		organization.setPassword(passwordEncoder.encode(organization.getPassword()));

		OrganizationEntity result = repository.save(organization);

		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/organization/{id}")
				.buildAndExpand(result.getEmail()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));

	}

	public ResponseEntity<?> addTrainer(Authentication auth,
										Long userId){

		Optional<OrganizationEntity> organization = repository.findByEmail(auth.getName());

		if (organization.isPresent()){

			Optional<TrainerEntity> trainer = trainerRepository.findByUser(userRepository.findById(userId).orElseThrow(
					() ->
							new CustomException("Incorrect user id", HttpStatus.NOT_FOUND)
			));

			if (trainer.isPresent()){

				if (trainer.get().getOrganization().equals(organization.get())){
					return ResponseEntity.ok(new ApiResponse(true, "already"));
				}else{
					throw new CustomException("Trainer is in other organization", HttpStatus.BAD_REQUEST);
				}

			}else{

				TrainerEntity newTrainer = new TrainerEntity();
				newTrainer.setUser(userRepository.findById(userId).orElseThrow(
						() ->
								new CustomException("Incorrect user id", HttpStatus.NOT_FOUND)));
				newTrainer.setOrganization(organization.get());
				trainerRepository.save(newTrainer);
				return ResponseEntity.ok(new ApiResponse(true, "Trainer added"));
			}

		}else{
			throw new CustomException("Auth error", HttpStatus.UNAUTHORIZED);
		}

	}

}
