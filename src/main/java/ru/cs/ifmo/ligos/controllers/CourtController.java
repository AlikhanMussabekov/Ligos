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
import ru.cs.ifmo.ligos.db.entities.CourtReviewEntity;
import ru.cs.ifmo.ligos.db.repositories.CourtRepository;
import ru.cs.ifmo.ligos.db.repositories.OrganizationRepository;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;
import ru.cs.ifmo.ligos.db.services.CourtService;
import ru.cs.ifmo.ligos.dto.CourtBookDTO;
import ru.cs.ifmo.ligos.dto.CourtPaymentDTO;
import ru.cs.ifmo.ligos.dto.EventDTO;
import ru.cs.ifmo.ligos.dto.SectionDetailsDTO;

import java.util.List;

@RestController
@RequestMapping("/court")
public class CourtController {

	private final UserRepository userRepository;
	private final OrganizationRepository organizationRepository;
	private final CourtService courtService;

	@Autowired
	public CourtController(UserRepository userRepository,
						   OrganizationRepository organizationRepository,
						   CourtService courtService) {
		this.userRepository = userRepository;
		this.organizationRepository = organizationRepository;
		this.courtService = courtService;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getCourts(){
		return courtService.getCourts();
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ORGANIZATION')")
	@Procedure("application/json")
	@ApiOperation(value = "${CourtController.addCourt}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> addCourt(@ApiParam("Court params") EventDTO court, Authentication auth){
		return courtService.addCourt(auth, court);
	}

	@GetMapping("/{id}")
	@Procedure("application/json")
	@ApiOperation(value = "${CourtController.getCourt}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong")})
	public ResponseEntity<?> getCourtById(@PathVariable Long id){
		return courtService.getCourtById(id);
	}

	@PostMapping("/{id}/payments")
	@PreAuthorize("hasRole('ROLE_ORGANIZATION')")
	@Procedure("application/json")
	@ApiOperation(value = "${CourtController.createCourtDetails}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> createCourtPayments(@ApiParam("Court payment body") @RequestBody CourtPaymentDTO courtDetailsDTO,
										   @ApiParam("Court id") @PathVariable Long id,
										   Authentication auth){
		return courtService.createCourtPayments(courtDetailsDTO, id, auth);
	}

	@GetMapping("/{id}/payments")
	@Procedure("application/json")
	@ApiOperation(value = "${CourtController.getCourtDetails}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong")})
	public ResponseEntity<?> getCourtPayments(@ApiParam("Court id") @PathVariable Long id){
		return courtService.getCourtPayments(id);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/{courtId}/book")
	public ResponseEntity<?> bookCourt(Authentication auth,
									   @PathVariable Long courtId,
									   @RequestBody CourtBookDTO courtPaymentIds){
		return courtService.bookCourt(auth, courtId, courtPaymentIds);
	}

	@GetMapping("/{id}/reviews")
	@Procedure("application/json")
	@ApiOperation(value = "${CourtController.getCourtReviews}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong")})
	public ResponseEntity<?> getReviews(@ApiParam("Court id") @PathVariable Long id){
		return courtService.getReviews(id);
	}

	@GetMapping("/{id}/reviews/{reviewId}")
	@Procedure("application/json")
	@ApiOperation(value = "${CourtController.getCourtReview}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong")})
	public ResponseEntity<?> getReview(@ApiParam("Court id") @PathVariable Long id,
									   @ApiParam("Review id") @PathVariable Long reviewId){
		return courtService.getReview(id, reviewId);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/{id}/review")
	@ApiOperation(value = "${CourtController.addCourtReview}")
	public ResponseEntity<?> addReview(Authentication auth,
									   @PathVariable Long id,
									   @RequestBody CourtReviewEntity review){
		return courtService.addReview(auth, id, review);
	}

}
