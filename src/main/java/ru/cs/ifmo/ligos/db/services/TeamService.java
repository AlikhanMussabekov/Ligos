package ru.cs.ifmo.ligos.db.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cs.ifmo.ligos.db.entities.Position;
import ru.cs.ifmo.ligos.db.entities.TeamEntity;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.repositories.TeamRepository;
import ru.cs.ifmo.ligos.db.repositories.TrainerRepository;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;
import ru.cs.ifmo.ligos.dto.ApiResponse;
import ru.cs.ifmo.ligos.dto.TeamDTO;
import ru.cs.ifmo.ligos.dto.TeamUserDTO;
import ru.cs.ifmo.ligos.exception.CustomException;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Service
public class TeamService {

	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	private final TrainerRepository trainerRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public TeamService(TeamRepository teamRepository,
					   UserRepository userRepository,
					   TrainerRepository trainerRepository,
					   ModelMapper modelMapper) {
		this.teamRepository = teamRepository;
		this.userRepository = userRepository;
		this.trainerRepository = trainerRepository;
		this.modelMapper = modelMapper;
	}

	//todo add pageable request, retrieve pages from request params
	public ResponseEntity<?> getAllTeams(){
		//todo more pages
		return ResponseEntity.ok(teamRepository.findAll(PageRequest.of(0,10)));
	}

	public ResponseEntity<?> createTeam(Authentication auth,
										TeamDTO teamDTO){

		Optional<UsersEntity> authUser = userRepository.findByEmail(auth.getName());

		if (authUser.isPresent()){

			if (teamRepository.findByName(teamDTO.getName().toLowerCase())){
				throw new CustomException("Name is already taken", HttpStatus.BAD_REQUEST);
			}else {

				TeamEntity team = modelMapper.map(teamDTO,TeamEntity.class);
				team.setCaptain(authUser.get());
				TeamEntity result = teamRepository.save(team);

				URI location = ServletUriComponentsBuilder
						.fromCurrentContextPath().path("/team/{id}")
						.buildAndExpand(result.getId()).toUri();

				return ResponseEntity.created(location).body(new ApiResponse(true, "Team successfully created"));
			}
		}else{
			throw new CustomException("Auth error", HttpStatus.FORBIDDEN);
		}


	}

	@Transactional
	public ResponseEntity<?> updateTeamInfo(Authentication auth,
											Long teamId,
											TeamDTO teamDTO){

		Optional<UsersEntity> authUser = userRepository.findByEmail(auth.getName());

		if (authUser.isPresent()){

			Optional<TeamEntity> team = teamRepository.findById(teamId);

			if (team.isPresent()){

				if (team.get().getCaptain().equals(authUser.get()) ||
						team.get().getTrainer().getUser().equals(authUser.get())) {

					if (teamRepository.findByName(teamDTO.getName().toLowerCase())){
						throw new CustomException("Name is already taken", HttpStatus.BAD_REQUEST);
					}else{
						try {
							team.get().setPhoto(teamDTO.getPhoto().getBytes());
						} catch (IOException e) {
							throw new CustomException("Cannot process photo", HttpStatus.UNPROCESSABLE_ENTITY);
						}
						team.get().setName(teamDTO.getName());
						team.get().setCaptain(userRepository.findById(teamDTO.getCaptainId())
								.orElseThrow(() -> new CustomException("Incorrect captain id", HttpStatus.BAD_REQUEST)));
						team.get().setTrainer(trainerRepository.findById(teamDTO.getTrainerId())
								.orElseThrow(() -> new CustomException("Incorrect trainer id", HttpStatus.BAD_REQUEST)));

						return ResponseEntity.accepted().body(new ApiResponse(true, "Team successfully updated"));
					}

				}else{
					throw new CustomException("You cannot update this team", HttpStatus.FORBIDDEN);
				}

			}else{
				throw new CustomException("Incorrect team id", HttpStatus.NOT_FOUND);
			}

		}else{
			throw new CustomException("Auth error", HttpStatus.FORBIDDEN);
		}

	}

	public ResponseEntity<?> addUserToTeam(Authentication auth,
										   TeamUserDTO teamUserDTO){

		//todo

		return null;
	}

	public ResponseEntity<?> removeUserFromTeam(Authentication auth,
												Long teamId,
												Long userId){
		//todo

		return null;
	}

	public ResponseEntity<?> updateUserPosition(Authentication auth,
												Long teamId,
												Long userId,
												Position newPosition){
		//todo

		return null;
	}


	public ResponseEntity<?> findTeamByPosition(Position position){

		return ResponseEntity.ok().body(teamRepository.findTeamsByPosition(position));

	}

}
