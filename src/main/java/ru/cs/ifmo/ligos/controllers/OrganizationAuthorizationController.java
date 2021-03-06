package ru.cs.ifmo.ligos.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;
import ru.cs.ifmo.ligos.db.services.OrganizationService;
import ru.cs.ifmo.ligos.dto.OrganizationDataDTO;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/organizations")
public class OrganizationAuthorizationController {

	private final OrganizationService organizationService;
	private final ModelMapper modelMapper;

	@Autowired
	public OrganizationAuthorizationController(OrganizationService organizationService, ModelMapper modelMapper) {
		this.organizationService = organizationService;
		this.modelMapper = modelMapper;
	}


	@GetMapping
	@PreAuthorize("hasRole('ROLE_ORGANIZATION')")
	String getUsers(){
		return "OK";
	}

	@PostMapping(value = "/signin", produces = "application/json")
	@ApiOperation(value = "${OrganizationAuthorizationController.signin}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 422, message = "Invalid email/password supplied")})
	public ResponseEntity<?> login(
						@ApiParam("Email") @RequestParam String email,
						@ApiParam("Password") @RequestParam String password) {
		return organizationService.signin(email, password);
	}

	@PostMapping(value = "/signup", produces = "application/json")
	@Procedure("application/json")
	@ApiOperation(value = "${OrganizationAuthorizationController.signup}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 422, message = "Email is already in use"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> signup(@ApiParam("Signup organization") @RequestBody OrganizationDataDTO user) {
		return organizationService.signup(modelMapper.map(user, OrganizationEntity.class));
	}

}
