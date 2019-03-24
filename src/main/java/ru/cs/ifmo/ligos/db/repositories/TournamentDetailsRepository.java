package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.TournamentDetailsEntity;
import ru.cs.ifmo.ligos.db.entities.TournamentEntity;

import java.util.List;

public interface TournamentDetailsRepository extends JpaRepository<TournamentDetailsEntity, Long> {

	List<TournamentDetailsEntity> findAllByTournament(TournamentEntity tournamentEntity);

}