package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.TournamentEntity;
import ru.cs.ifmo.ligos.db.entities.TournamentReviewsEntity;

import java.util.List;

public interface TournamentReviewsRepository extends JpaRepository<TournamentReviewsEntity, Long> {

	List<TournamentReviewsEntity> findAllByTournament(TournamentEntity tournamentEntity);

}