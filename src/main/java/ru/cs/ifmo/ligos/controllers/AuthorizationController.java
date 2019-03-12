package ru.cs.ifmo.ligos.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.services.UserService;
import ru.cs.ifmo.ligos.dto.UserDataDTO;

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

	@PostMapping("/signin")
	@ApiOperation(value = "${UserController.signin}")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 422, message = "Invalid username/password supplied")})
	public String login(//
						@ApiParam("Email") @RequestParam String email, //
						@ApiParam("Password") @RequestParam String password) {
		return userService.signin(email, password);
	}

	@PostMapping("/signup")
	@ApiOperation(value = "${UserController.signup}")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Username is already in use"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
		return userService.signup(modelMapper.map(user, UsersEntity.class));
	}

}
