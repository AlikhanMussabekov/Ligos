package ru.cs.ifmo.ligos.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.cs.ifmo.ligos.db.repositories.MatchesRepository;
import ru.cs.ifmo.ligos.db.services.MatchService;
import ru.cs.ifmo.ligos.dto.GoalDTO;
import ru.cs.ifmo.ligos.dto.MatchDTO;
import ru.cs.ifmo.ligos.dto.UserMatchDTO;

@RestController
@RequestMapping("/match")
public class MatchController {

	private final MatchService matchService;

	@Autowired
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}


	@GetMapping("/{id}")
	@Procedure("application/json")
	@ApiOperation(value = "${MatchController.getMatchById}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong")})
	public ResponseEntity<?> getMatchById(@PathVariable Long id){
		return matchService.getMatchById(id);
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ORGANIZATION')")
	@Procedure("application/json")
	@ApiOperation(value = "${MatchController.addMatch}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> addMatch(Authentication auth,
									  MatchDTO matchDTO){
		return matchService.addMatch(auth,matchDTO);
	}

	@PostMapping("/addUserMatchInfo")
	@PreAuthorize("hasRole('ROLE_ORGANIZATION')")
	@Procedure("application/json")
	@ApiOperation(value = "${MatchController.addUserMatchInfo}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> addUserMatchInfo(UserMatchDTO userMatchDTO){
		return matchService.addUserMatchInfo(userMatchDTO);
	}

	@PostMapping("/addGoal")
	@PreAuthorize("hasRole('ROLE_ORGANIZATION')")
	@Procedure("application/json")
	@ApiOperation(value = "${MatchController.addGoal}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> addUserMatchInfo(GoalDTO goalDTO){
		return matchService.addGoal(goalDTO);
	}
}
