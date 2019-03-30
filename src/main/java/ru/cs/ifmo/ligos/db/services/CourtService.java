package ru.cs.ifmo.ligos.db.services;

import jdk.nashorn.internal.runtime.options.Option;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cs.ifmo.ligos.db.entities.*;
import ru.cs.ifmo.ligos.db.repositories.*;
import ru.cs.ifmo.ligos.dto.ApiResponse;
import ru.cs.ifmo.ligos.dto.CourtBookDTO;
import ru.cs.ifmo.ligos.dto.CourtPaymentDTO;
import ru.cs.ifmo.ligos.dto.EventDTO;
import ru.cs.ifmo.ligos.exception.CustomException;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@Service
public class CourtService {

	private final Logger logger = LogManager.getLogger(CourtService.class);

	private final CourtRepository courtRepository;
	private final OrganizationRepository organizationRepository;
	private final AddressRepository addressRepository;
	private final CourtPaymentRepository courtPaymentRepository;
	private final UserRepository userRepository;
	private final CourtReviewRepository courtReviewRepository;

	public CourtService(CourtRepository courtRepository,
						OrganizationRepository organizationRepository,
						AddressRepository addressRepository,
						CourtPaymentRepository courtPaymentRepository,
						UserRepository userRepository,
						CourtReviewRepository courtReviewRepository) {
		this.courtRepository = courtRepository;
		this.organizationRepository = organizationRepository;
		this.addressRepository = addressRepository;
		this.courtPaymentRepository = courtPaymentRepository;
		this.userRepository = userRepository;
		this.courtReviewRepository = courtReviewRepository;
	}

	public ResponseEntity<?> addCourt(Authentication auth,
									  EventDTO eventDto){
		try {
			CourtEntity court = CourtEntity.builder()
					.organization(organizationRepository.findByEmail(auth.getName()))
					.name(eventDto.getName())
					.description(eventDto.getDescription())
					.photos(eventDto.getPhoto() == null ? null : eventDto.getPhoto().getBytes())
					//todo address!!!
					.address(addressRepository.findById((long)2).get())
					.build();

			CourtEntity result = courtRepository.save(court);

			URI location = ServletUriComponentsBuilder
					.fromCurrentContextPath().path("/court/{id}")
					.buildAndExpand(result.getId()).toUri();

			return ResponseEntity.created(location)
					.body(new ApiResponse(true, "Court successfully created"));

		} catch (IOException e) {
			logger.debug("[Court creation] - error");
			throw new CustomException("[Court creation] - error", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> getCourts(){
		return ResponseEntity.ok(courtRepository.findAll());
	}

	public ResponseEntity<?> getCourtById(Long id){
		Optional<CourtEntity> court = courtRepository.findById(id);

		if (court.isPresent()){
			return ResponseEntity.ok(court.get());
		}else{
			return ResponseEntity.badRequest().body("Incorrect court id");
		}

	}

	public ResponseEntity<?> createCourtPayments(CourtPaymentDTO courtPaymentDTO,
												 Long id,
												 Authentication auth){

		Optional<CourtEntity> court = courtRepository.findById(id);

		if (court.isPresent()){

			if (auth.getName().equalsIgnoreCase(court.get().getOrganization().getEmail())){

					CourtPaymentEntity courtPaymentEntity = courtPaymentRepository
							.save(CourtPaymentEntity
									.builder()
									.court(court.get())
									.price(courtPaymentDTO.getPrice())
									.date(courtPaymentDTO.getDate())
									.from(courtPaymentDTO.getHour_from())
									.to(courtPaymentDTO.getHour_to())
									.status(false)
									.build()
							);

					URI location = ServletUriComponentsBuilder
							.fromCurrentContextPath().path("/court/{id}")
							.buildAndExpand(court.get().getId()).toUri();

					return ResponseEntity.created(location).body(new ApiResponse(true, "Section details successfully created"));


			}else {
				throw new CustomException("Access denied", HttpStatus.FORBIDDEN);
			}

		}else{
			throw new CustomException("Court not exists", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> getCourtPayments(Long id){
		Optional<CourtEntity> court = courtRepository.findById(id);

		if (court.isPresent()){
			Optional <List<CourtPaymentEntity>> list = courtPaymentRepository.findAllByCourt(court.get());
			return ResponseEntity.ok(list.get());
		}else{
			throw new CustomException("Wrong court id", HttpStatus.BAD_REQUEST);
		}
	}

	@Transactional
	public ResponseEntity<?> bookCourt(Authentication auth,
									   Long courtId,
									   CourtBookDTO courtPaymentIds){

		Optional<UsersEntity> authUser = userRepository.findByEmail(auth.getName());

		if (authUser.isPresent()){

			Optional<CourtEntity> court = courtRepository.findById(courtId);

			if (court.isPresent()){

				courtPaymentIds.getIds().forEach((id) -> {

					Optional<CourtPaymentEntity> courtPaymentEntity = courtPaymentRepository.findById(id);

					if (courtPaymentEntity.isPresent() && courtPaymentEntity.get().getCourt().equals(court.get())){
						if(!courtPaymentEntity.get().getStatus()) {
							courtPaymentEntity.get().setUser(authUser.get());
							courtPaymentEntity.get().setStatus(true);
							courtPaymentRepository.save(courtPaymentEntity.get());
						}
					}else {
						throw new CustomException("Incorrect court payment id", HttpStatus.BAD_REQUEST);
					}

				});

				return ResponseEntity.accepted().body("Court successfully booked");

			}else{
				throw new CustomException("Incorrect court id", HttpStatus.BAD_REQUEST);
			}

		}else{
			throw new CustomException("Auth error", HttpStatus.UNAUTHORIZED);
		}

	}

	@Transactional
	public ResponseEntity<?> addReview(Authentication auth,
									   Long courtId,
									   CourtReviewEntity review) {

		Optional<UsersEntity> authUser = userRepository.findByEmail(auth.getName());

		if (authUser.isPresent()) {

			Optional<CourtEntity> court = courtRepository.findById(courtId);

			if (court.isPresent()) {

				if(courtPaymentRepository.existsByCourtAndUser(court.get(), authUser.get())){

					CourtReviewEntity result = courtReviewRepository.save(review);

					Map<String, Object> pathVariableMap = new HashMap<>();
					pathVariableMap.put("courtId", courtId);
					pathVariableMap.put("reviewId", result.getId());

					URI location = ServletUriComponentsBuilder
							.fromCurrentContextPath().path("/court/{courtId}/review/{reviewId}")
							.buildAndExpand(pathVariableMap)
							.toUri();

					return ResponseEntity.created(location).body(new ApiResponse(true, "Review added"));
				}else{
					throw new CustomException("You cannot write a review", HttpStatus.FORBIDDEN);
				}

			} else {
				throw new CustomException("Incorrect court id", HttpStatus.BAD_REQUEST);
			}

		} else {
			throw new CustomException("Auth error", HttpStatus.UNAUTHORIZED);
		}
	}


	public ResponseEntity<?> getReviews(Long courtId){

		Optional<CourtEntity> court = courtRepository.findById(courtId);

		if(court.isPresent()){

			return ResponseEntity.ok(courtReviewRepository.findAllByCourt(court.get()));

		}else{
			throw new CustomException("Incorrect court id", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> getReview(Long courtId, Long reviewId){

		Optional<CourtEntity> court = courtRepository.findById(courtId);

		if(court.isPresent()){

			Optional<CourtReviewEntity> review = courtReviewRepository.findById(reviewId);

			if(review.isPresent()){

				return ResponseEntity.ok(review.get());

			}else{
				throw new CustomException("Incorrect review id", HttpStatus.BAD_REQUEST);
			}

		}else{
			throw new CustomException("Incorrect court id", HttpStatus.BAD_REQUEST);
		}

	}

}
