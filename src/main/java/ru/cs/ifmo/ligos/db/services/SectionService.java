package ru.cs.ifmo.ligos.db.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cs.ifmo.ligos.db.entities.*;
import ru.cs.ifmo.ligos.db.repositories.*;
import ru.cs.ifmo.ligos.dto.ApiResponse;
import ru.cs.ifmo.ligos.dto.SectionDTO;
import ru.cs.ifmo.ligos.dto.SectionDetailsDTO;
import ru.cs.ifmo.ligos.exception.CustomException;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

	private final Logger logger = LogManager.getLogger(SectionService.class);


	private final SectionRepository sectionRepository;
	private final OrganizationRepository organizationRepository;
	private final AddressRepository addressRepository;
	private final SectionDetailsRepository sectionDetailsRepository;
	private final AgeCategoryRepository ageCategoryRepository;
	private final UserRepository userRepository;

	@Autowired
	public SectionService(SectionRepository sectionRepository,
						  OrganizationRepository organizationRepository,
						  AddressRepository addressRepository,
						  SectionDetailsRepository sectionDetailsRepository,
						  AgeCategoryRepository ageCategoryRepository,
						  UserRepository userRepository) {
		this.sectionRepository = sectionRepository;
		this.organizationRepository = organizationRepository;
		this.addressRepository = addressRepository;
		this.sectionDetailsRepository = sectionDetailsRepository;
		this.ageCategoryRepository = ageCategoryRepository;
		this.userRepository = userRepository;
	}

	public ResponseEntity<?> getSections(){
		//return ResponseEntity.ok(sectionRepository.findTopByRaitingOrderByRaitingDesc((short) 5));
		return ResponseEntity.ok(sectionRepository.findAll());
	}

	public ResponseEntity<?> getSectionById(Long id){
		return ResponseEntity.ok(sectionRepository.findById(id));
	}

	public ResponseEntity<?> createSection(SectionDTO sectionDto, Authentication auth){

		try {
			SectionEntity section = SectionEntity.builder()
					.organization(organizationRepository.findByEmail(auth.getName()))
					.name(sectionDto.getName())
					.description(sectionDto.getDescription())
					.photo(sectionDto.getPhoto().getBytes())
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
					throw new CustomException("Trainer email not exists", HttpStatus.BAD_REQUEST);
				}

			}else {
				throw new CustomException("Access denied", HttpStatus.FORBIDDEN);
			}

		}else{
			throw new CustomException("Section not exists", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> getSectionDetails(Long id){
		Optional<SectionEntity> section = sectionRepository.findById(id);

		if (section.isPresent()){
			Optional <List<SectionDetailsEntity>> list = sectionDetailsRepository.findAllBySection(section.get());
			return ResponseEntity.ok(list.get());
		}else{
			throw new CustomException("Wrong section id", HttpStatus.BAD_REQUEST);
		}
	}

}
