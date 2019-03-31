package ru.cs.ifmo.ligos.db.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cs.ifmo.ligos.db.entities.*;
import ru.cs.ifmo.ligos.db.repositories.*;
import ru.cs.ifmo.ligos.dto.ApiResponse;
import ru.cs.ifmo.ligos.dto.GoalDTO;
import ru.cs.ifmo.ligos.dto.MatchDTO;
import ru.cs.ifmo.ligos.dto.UserMatchDTO;
import ru.cs.ifmo.ligos.exception.CustomException;

import java.net.URI;
import java.util.Optional;

@Service
public class MatchService {

	private final MatchesRepository matchesRepository;
	private final OrganizationRepository organizationRepository;
	private final ModelMapper modelMapper;
	private final CourtRepository courtRepository;
	private final TeamRepository teamRepository;
	private final GoalsListRepository goalsListRepository;
	private final UserRepository userRepository;

	@Autowired
	public MatchService(MatchesRepository matchesRepository,
						OrganizationRepository organizationRepository,
						ModelMapper modelMapper,
						CourtRepository courtRepository,
						TeamRepository teamRepository,
						GoalsListRepository goalsListRepository,
						UserRepository userRepository) {
		this.matchesRepository = matchesRepository;
		this.organizationRepository = organizationRepository;
		this.modelMapper = modelMapper;
		this.courtRepository = courtRepository;
		this.teamRepository = teamRepository;
		this.goalsListRepository = goalsListRepository;
		this.userRepository = userRepository;
	}

	public ResponseEntity<?> getMatchById(Long id){

		return ResponseEntity.ok().body(matchesRepository.findById(id).orElseThrow(
				() -> new CustomException("Incorrect match id", HttpStatus.BAD_REQUEST)

		));
	}

	public ResponseEntity<?> addMatch(Authentication auth,
									  MatchDTO matchDTO){

		Optional<OrganizationEntity> organization = organizationRepository.findByEmail(auth.getName());

		if (organization.isPresent()){

			MatchesEntity match = modelMapper.map(matchDTO, MatchesEntity.class);

			match.setCourt(courtRepository.findById(matchDTO.getCourtId()).orElseThrow( () ->
					new CustomException("Incorrect court id", HttpStatus.BAD_REQUEST)
			));

			match.setTeamFirst(teamRepository.findById(matchDTO.getFirstTeamId()).orElseThrow( () ->
					new CustomException("Incorrect first team id", HttpStatus.BAD_REQUEST)
			));

			match.setTeamSecond(teamRepository.findById(matchDTO.getSecondTeamId()).orElseThrow( () ->
					new CustomException("Incorrect second team id", HttpStatus.BAD_REQUEST)
			));

			MatchesEntity result = matchesRepository.save(match);

			URI location = ServletUriComponentsBuilder
					.fromCurrentContextPath().path("/match/{id}")
					.buildAndExpand(result.getId()).toUri();

			return ResponseEntity.created(location).body(new ApiResponse(true, "Match successfully created"));

		}else{
			throw new CustomException("Auth error", HttpStatus.UNAUTHORIZED);
		}

	}

	public ResponseEntity<?> getGoalsByUserId(Long userId){
		return ResponseEntity.ok().body(goalsListRepository.findAllByUser(
				userRepository.findById(userId).orElseThrow( () ->
					new CustomException("Incorrect user id", HttpStatus.BAD_REQUEST)
				)).orElseThrow( () ->
					new CustomException("Incorrect user id", HttpStatus.BAD_REQUEST)
				)
		);
	}

	public ResponseEntity<?> addGoal(GoalDTO goalDTO){

		GoalsListEntity goal = modelMapper.map(goalDTO,GoalsListEntity.class);
		goal.setUser(userRepository.findById(goalDTO.getUserId()).orElseThrow( () ->
			new CustomException("Incorrect user id", HttpStatus.BAD_REQUEST)
		));

		goal.setMatch(matchesRepository.findById(goalDTO.getMatchId()).orElseThrow( () ->
				new CustomException("Incorrect match id", HttpStatus.BAD_REQUEST)
		));

		goalsListRepository.save(goal);

		return ResponseEntity.created(null).body(new ApiResponse(true, "Goal successfully added"));

	}

	public ResponseEntity<?> getUserMatchInfo(Long userId){
		return ResponseEntity.ok().body(
				userRepository.findById(userId).orElseThrow( () ->
					new CustomException("Incorrect user id", HttpStatus.BAD_REQUEST)
				).getUserMatchEntities()
		);
	}

	public ResponseEntity<?> getUserMatchInfoByMatch(Long userId,
													 Long matchId){
		return ResponseEntity.ok().body(
				userRepository.findById(userId).orElseThrow( () ->
						new CustomException("Incorrect user id", HttpStatus.BAD_REQUEST)
				).getUserMatchEntities().stream().filter(e -> e.getMatch().getId().equals(matchId))
		);
	}

	public ResponseEntity<?> addUserMatchInfo(UserMatchDTO userMatchDTO){

		Optional<UsersEntity> user = userRepository.findById(userMatchDTO.getUserId());

		if (user.isPresent()){
			UserMatchEntity userMatchInfo = modelMapper.map(userMatchDTO, UserMatchEntity.class);
			userMatchInfo.setUser(user.get());
			userMatchInfo.setMatch(matchesRepository.findById(userMatchDTO.getMatchId()).orElseThrow( () ->
				new CustomException("Incorrect match id", HttpStatus.BAD_REQUEST)
			));

			user.get().getUserMatchEntities().add(userMatchInfo);
			userRepository.save(user.get());

			return ResponseEntity.created(null).body(new ApiResponse(true, "User match info successfully added"));
		}else{
			throw new CustomException("Incorrect user id", HttpStatus.BAD_REQUEST);
		}

	}


}
