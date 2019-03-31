package ru.cs.ifmo.ligos.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.cs.ifmo.ligos.db.entities.Position;
import ru.cs.ifmo.ligos.db.entities.TeamEntity;
import ru.cs.ifmo.ligos.db.services.TeamService;
import ru.cs.ifmo.ligos.dto.TeamDTO;
import ru.cs.ifmo.ligos.dto.TeamUserDTO;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("team")
public class TeamController {

	private final TeamService teamService;

	@Autowired
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@GetMapping("/all")
	@Procedure("application/json")
	@ApiOperation(value = "${TeamController.getAllTeams}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> getAllTeams(){
		return teamService.getAllTeams();
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_USER')")
	@Procedure("application/json")
	@ApiOperation(value = "${TeamController.createTeam}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> createTeam(Authentication auth,
										@ApiParam("Team body") TeamDTO teamDTO){
		return teamService.createTeam(auth,teamDTO);
	}

	@PutMapping("/{teamId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	@Procedure("application/json")
	@ApiOperation(value = "${TeamController.updateTeam}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> createTeam(Authentication auth,
										@ApiParam("Team id") @PathParam("teamId") Long teamId,
										@ApiParam("Team body") TeamDTO teamDTO){
		return teamService.updateTeamInfo(auth,teamId,teamDTO);
	}


	@PostMapping("/{teamId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	@Procedure("application/json")
	@ApiOperation(value = "${TeamController.addUserToTeam}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> addUserToTeam(Authentication auth,
										   @ApiParam("Team id") @PathParam("teamId") Long teamId,
										   @ApiParam("User id") TeamUserDTO teamUserDTO){
		return teamService.addUserToTeam(auth, teamUserDTO);
	}


	@DeleteMapping("/{teamId/player}")
	@PreAuthorize("hasRole('ROLE_USER')")
	@Procedure("application/json")
	@ApiOperation(value = "${TeamController.removeUserFromTeam}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> removeUserFromTeam(Authentication auth,
												@ApiParam("Team id") @PathParam("teamId") Long teamId,
												@ApiParam("User id") @RequestParam("userId") Long userId){
		return teamService.removeUserFromTeam(auth,userId);
	}


	@PutMapping("/{teamId/player}")
	@PreAuthorize("hasRole('ROLE_USER')")
	@Procedure("application/json")
	@ApiOperation(value = "${TeamController.removeUserFromTeam}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> updateUserPosition(Authentication auth,
												@ApiParam("Team id") @PathParam("teamId") Long teamId,
												@ApiParam("User id") @RequestParam("userId") Long userId,
												@ApiParam("New position") @RequestParam("newPosition") Position position){
		return teamService.updateUserPosition(auth,teamId,position);
	}

}
