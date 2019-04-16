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
import ru.cs.ifmo.ligos.db.entities.SectionReviewEntity;
import ru.cs.ifmo.ligos.db.services.SectionService;
import ru.cs.ifmo.ligos.dto.EventDTO;
import ru.cs.ifmo.ligos.dto.ReviewDTO;
import ru.cs.ifmo.ligos.dto.SectionDetailsDTO;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/section")
public class SectionController {

	private final SectionService sectionService;

	@Autowired
	public SectionController(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	@GetMapping("/all/{count}")
	public ResponseEntity<?> getSections(@PathVariable("count") Integer count){
		return sectionService.getSections(count);
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ORGANIZATION')")
	@Procedure("application/json")
	@ApiOperation(value = "${SectionsController.createSection}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> createSection(@ApiParam("Section params") EventDTO section, Authentication auth){
		return sectionService.createSection(section, auth);
	}

	@GetMapping("/{id}")
	@Procedure("application/json")
	@ApiOperation(value = "${SectionsController.getSection}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong")})
	public ResponseEntity<?> getSectionById(@PathVariable Long id){
		return sectionService.getSectionById(id);
	}

	@PostMapping("/{id}/details")
	@PreAuthorize("hasRole('ROLE_ORGANIZATION')")
	@Procedure("application/json")
	@ApiOperation(value = "${SectionsController.createSectionDetails}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> createDetails(@ApiParam("Section details params") @RequestBody SectionDetailsDTO sectionDetailsDTO,
										   @ApiParam("Section id") @PathVariable Long id,
										   Authentication auth){
		return sectionService.createSectionDetails(sectionDetailsDTO, id, auth);
	}

	@GetMapping("/{id}/details")
	@Procedure("application/json")
	@ApiOperation(value = "${SectionsController.getSectionDetails}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong")})
	public ResponseEntity<?> getDetails(@ApiParam("Section id") @PathVariable Long id){
		return sectionService.getSectionDetails(id);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/{sectionId}/details/{sectionDetailsId}/register")
	@Procedure("application/json")
	@ApiOperation(value = "${SectionsController.registerToSection}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> registerToSection(Authentication auth,
											   @ApiParam("Section id") @PathVariable Long sectionId,
											   @ApiParam("Section Details id") @PathVariable Long sectionDetailsId){
		return sectionService.registerToSection(auth, sectionId, sectionDetailsId);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/{id}/review")
	@ApiOperation(value = "${SectionController.addSectionReview}")
	public ResponseEntity<?> addReview(Authentication auth,
									   @ApiParam("Section id") @PathVariable Long id,
									   @ApiParam("Review body") @RequestBody ReviewDTO review){
		return sectionService.addReview(auth, id, review);
	}

	@GetMapping("/{id}/reviews")
	@Procedure("application/json")
	@ApiOperation(value = "${SectionController.getSectionReviews}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong")})
	public ResponseEntity<?> getReviews(@ApiParam("Section id") @PathVariable Long id){
		return sectionService.getReviews(id);
	}

	@GetMapping("/{id}/reviews/{reviewId}")
	@Procedure("application/json")
	@ApiOperation(value = "${SectionController.getSectionReview}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong")})
	public ResponseEntity<?> getReview(@ApiParam("Section id") @PathVariable Long id,
									   @ApiParam("Review id") @PathVariable Long reviewId){
		return sectionService.getReview(id, reviewId);
	}

	@GetMapping("/mySections")
	@PreAuthorize("hasRole('ROLE_ORGANIZATION')")
	public ResponseEntity<?> mySections(Authentication authentication) {
		return sectionService.mySections(authentication.getName());
	}

}
