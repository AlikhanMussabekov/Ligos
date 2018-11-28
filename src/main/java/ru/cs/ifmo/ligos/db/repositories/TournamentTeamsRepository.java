package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.TournamentTeamsEntity;

public interface TournamentTeamsRepository extends JpaRepository<TournamentTeamsEntity, Long> {
}