package ru.cs.ifmo.ligos.db.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cs.ifmo.ligos.db.entities.*;
import ru.cs.ifmo.ligos.db.repositories.*;
import ru.cs.ifmo.ligos.dto.ApiResponse;
import ru.cs.ifmo.ligos.dto.EventDTO;
import ru.cs.ifmo.ligos.dto.ReviewDTO;
import ru.cs.ifmo.ligos.dto.SectionDetailsDTO;
import ru.cs.ifmo.ligos.exception.CustomException;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@Service
public class SectionService {

	private final Logger logger = LogManager.getLogger(SectionService.class);


	private final SectionRepository sectionRepository;
	private final OrganizationRepository organizationRepository;
	private final AddressRepository addressRepository;
	private final SectionDetailsRepository sectionDetailsRepository;
	private final AgeCategoryRepository ageCategoryRepository;
	private final UserRepository userRepository;
	private final SectionReviewRepository sectionReviewRepository;

	@Autowired
	public SectionService(SectionRepository sectionRepository,
						  OrganizationRepository organizationRepository,
						  AddressRepository addressRepository,
						  SectionDetailsRepository sectionDetailsRepository,
						  AgeCategoryRepository ageCategoryRepository,
						  UserRepository userRepository,
						  SectionReviewRepository sectionReviewRepository) {
		this.sectionRepository = sectionRepository;
		this.organizationRepository = organizationRepository;
		this.addressRepository = addressRepository;
		this.sectionDetailsRepository = sectionDetailsRepository;
		this.ageCategoryRepository = ageCategoryRepository;
		this.userRepository = userRepository;
		this.sectionReviewRepository = sectionReviewRepository;
	}

	public ResponseEntity<?> getSections(Integer count){
		return ResponseEntity.ok(sectionRepository.findAll(PageRequest.of(0,count)));
	}

	public ResponseEntity<?> getSectionById(Long id){

		Optional<SectionEntity> section = sectionRepository.findById(id);

		if (section.isPresent()){
			return ResponseEntity.ok(section.get());
		}else{
			throw new CustomException("Incorrect section id", HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> createSection(EventDTO eventDto, Authentication auth){

		try {
			SectionEntity section = SectionEntity.builder()
					.organization(organizationRepository.findByEmail(auth.getName()).get())
					.name(eventDto.getName())
					.description(eventDto.getDescription())
					.photo(eventDto.getPhoto().getBytes())
					//todo address!!!
					.address(addressRepository.findById((long)2).get())
					.build();

			SectionEntity result = sectionRepository.save(section);

			URI location = ServletUriComponentsBuilder
					.fromCurrentContextPath().path("/section/{id}")
					.buildAndExpand(result.getId()).toUri();

			return ResponseEntity.created(location).body(new ApiResponse(true, "Section successfully created"));

		} catch (IOException e) {
			logger.debug("[Section creation] - error");
			throw new CustomException("[Section creation] - error", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> createSectionDetails(SectionDetailsDTO sectionDto,
												  Long id,
												  Authentication auth) {


		Optional<SectionEntity> section = sectionRepository.findById(id);

		if (section.isPresent()){

			if (auth.getName().equalsIgnoreCase(section.get().getOrganization().getEmail())){

				Optional<AgeCategoryEntity> ageCategoryEntity = ageCategoryRepository
						.findByFromAndTo(sectionDto.getAgeCategory().getFrom(),
										sectionDto.getAgeCategory().getTo());

				Optional<UsersEntity> usersEntity = userRepository.findByEmail(sectionDto.getTrainer_email());

				if (usersEntity.get().getTrainer() != null) {

					SectionDetailsEntity sectionDetailsEntity = sectionDetailsRepository
							.save(SectionDetailsEntity
									.builder()
									.section(section.get())
									.ageCategory(ageCategoryEntity.orElseGet(() -> ageCategoryRepository.save(sectionDto.getAgeCategory())))
									.price(sectionDto.getPrice())
									.maxUsersCount(sectionDto.getMaxUsersCount())
									.timeStart(sectionDto.getTimeStart())
									.timeEnd(sectionDto.getTimeEnd())
									.trainer(usersEntity.get().getTrainer())
									.build()
							);

					URI location = ServletUriComponentsBuilder
							.fromCurrentContextPath().path("/section/{id}")
							.buildAndExpand(section.get().getId()).toUri();

					return ResponseEntity.created(location).body(new ApiResponse(true, "Section details successfully created"));
				}else{
					throw new CustomException("Incorrect trainer email", HttpStatus.BAD_REQUEST);
				}

			}else {
				throw new CustomException("Access denied", HttpStatus.FORBIDDEN);
			}

		}else{
			throw new CustomException("Incorrect section id", HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<?> getSectionDetails(Long id){
		Optional<SectionEntity> section = sectionRepository.findById(id);

		if (section.isPresent()){
			Optional <List<SectionDetailsEntity>> list = sectionDetailsRepository.findAllBySection(section.get());
			return ResponseEntity.ok(list.orElseThrow( () ->
				new CustomException("Not found", HttpStatus.NOT_FOUND)
			));
		}else{
			throw new CustomException("Incorrect section id", HttpStatus.NOT_FOUND);
		}
	}

	@Transactional
	public ResponseEntity<?> registerToSection(Authentication auth,
											   Long sectionId,
											   Long sectionDetailsId){

		Optional<UsersEntity> authUser = userRepository.findByEmail(auth.getName());

		if (authUser.isPresent()){

			Optional<SectionEntity> section = sectionRepository.findById(sectionId);

			if (section.isPresent()){

				Optional<SectionDetailsEntity> sectionDetails = sectionDetailsRepository.findByIdAndSection(sectionDetailsId,section.get());

				if (sectionDetails.isPresent()){

					AttendanceEntity attendance = new AttendanceEntity();
					attendance.setUser(authUser.get());
					attendance.setSectionDetails(sectionDetails.get());

					sectionDetails.get().addUserToAttendance(attendance);

					sectionDetailsRepository.save(sectionDetails.get());

					return ResponseEntity.accepted().body(new ApiResponse(true, "User successfully registered to section"));
				}else{
					throw new CustomException("Incorrect section details id", HttpStatus.NOT_FOUND);
				}

			}else{
				throw new CustomException("Incorrect section id", HttpStatus.NOT_FOUND);
			}

		}else{
			throw new CustomException("Access denied", HttpStatus.FORBIDDEN);
		}

	}


	@Transactional
	public ResponseEntity<?> addReview(Authentication auth,
									   Long sectionId,
									   ReviewDTO reviewDTO) {

		Optional<UsersEntity> authUser = userRepository.findByEmail(auth.getName());

		if (authUser.isPresent()) {

			Optional<SectionEntity> section = sectionRepository.findById(sectionId);

			if (section.isPresent()) {

				if (sectionDetailsRepository.findAllBySection(section.get()).orElseThrow(
						() ->
							new CustomException("Not section details found", HttpStatus.NOT_FOUND)
						).stream().anyMatch(sectionDetailsEntity -> {
							return sectionDetailsEntity.getAttendance().stream().anyMatch(attendanceEntity -> {
								if (attendanceEntity.getId().getUser().equals(authUser.get())){
									return true;
								}else{
									return false;
								}
							});
						})

				)
				{

					SectionReviewEntity review = new SectionReviewEntity();
							review.setReview(reviewDTO.getReview());
							review.setRaiting(reviewDTO.getRating());
							review.setDate(new Date());
							review.setUser(authUser.get());
							review.setSection(section.get());



					SectionReviewEntity result = sectionReviewRepository.save(review);

					Map<String, Object> pathVariableMap = new HashMap<>();
					pathVariableMap.put("sectionId", sectionId);
					pathVariableMap.put("reviewId", result.getId());

					URI location = ServletUriComponentsBuilder
							.fromCurrentContextPath().path("/section/{sectionId}/reviews/{reviewId}")
							.buildAndExpand(pathVariableMap)
							.toUri();

					return ResponseEntity.created(location).body(new ApiResponse(true, "Review added"));

				}else{
					throw new CustomException("You cannot write a review", HttpStatus.FORBIDDEN);
				}

			} else {
				throw new CustomException("Incorrect section id", HttpStatus.NOT_FOUND);
			}

		} else {
			throw new CustomException("Auth error", HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity<?> getReviews(Long sectionId){

		Optional<SectionEntity> section = sectionRepository.findById(sectionId);

		if(section.isPresent()){

			return ResponseEntity.ok(sectionReviewRepository.findAllBySection(section.get()));

		}else{
			throw new CustomException("Incorrect section id", HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<?> getReview(Long sectionId, Long reviewId){

		Optional<SectionEntity> section = sectionRepository.findById(sectionId);

		if(section.isPresent()){

			Optional<SectionReviewEntity> review = sectionReviewRepository.findById(reviewId);

			if(review.isPresent()){

				return ResponseEntity.ok(review.get());

			}else{
				throw new CustomException("Incorrect section id", HttpStatus.NOT_FOUND);
			}

		}else{
			throw new CustomException("Incorrect section id", HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<?> mySections(String email){
		Optional<OrganizationEntity> auth = organizationRepository.findByEmail(email);

		return ResponseEntity.ok( sectionRepository.findByOrganization(auth.orElseThrow(
				() ->
					new CustomException("Auth error",HttpStatus.FORBIDDEN)
		)).orElseThrow( () ->
							new CustomException("No sections",HttpStatus.NOT_FOUND)
		) );

	}

}
