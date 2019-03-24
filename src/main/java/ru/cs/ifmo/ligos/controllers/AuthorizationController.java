package ru.cs.ifmo.ligos.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.services.UserService;
import ru.cs.ifmo.ligos.dto.UserDataDTO;
import ru.cs.ifmo.ligos.security.CurrentUser;
import ru.cs.ifmo.ligos.security.oauth2.UserPrincipal;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class AuthorizationController {

	private final UserService userService;
	private final ModelMapper modelMapper;

	@Autowired
	public AuthorizationController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	String getUsers(){
		return "OK";
	}

	@PostMapping(value = "/signin", produces = "application/json")
	@ApiOperation(value = "${UserController.signin}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 422, message = "Invalid email/password supplied")})
	public ResponseEntity<?> signin(
						@ApiParam("Email") @RequestParam String email,
						@ApiParam("Password") @RequestParam String password) {
		return userService.signin(email, password);
	}

	@PostMapping(value = "/signup", produces = "application/json")
	@ApiOperation(value = "${UserController.signup}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 422, message = "Username is already in use"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
		return userService.signup(modelMapper.map(user, UsersEntity.class));
	}

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> getCurrentUser(Authentication authentication) {
		return userService.getCurrentUser(authentication.getName());
	}

}
