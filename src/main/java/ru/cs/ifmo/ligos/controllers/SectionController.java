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
import ru.cs.ifmo.ligos.db.entities.SectionDetailsEntity;
import ru.cs.ifmo.ligos.db.services.SectionService;
import ru.cs.ifmo.ligos.dto.SectionDTO;
import ru.cs.ifmo.ligos.dto.SectionDetailsDTO;

@RestController
@RequestMapping("section")
public class SectionController {

	private final SectionService sectionService;

	@Autowired
	public SectionController(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getSections(){
		return sectionService.getSections();
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ORGANIZATION')")
	@Procedure("application/json")
	@ApiOperation(value = "${SectionsController.createSection}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<?> createSection(@ApiParam("Section params")SectionDTO section, Authentication auth){
		return sectionService.createSection(section, auth);
	}

	@GetMapping("/{id}")
	@Procedure("application/json")
	@ApiOperation(value = "${SectionsController.createSection}")
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
	public ResponseEntity<?> createDetails(@ApiParam("Section id") @PathVariable Long id){
		return sectionService.getSectionDetails(id);
	}

}
