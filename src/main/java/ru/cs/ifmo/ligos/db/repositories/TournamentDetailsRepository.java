package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.TournamentDetailsEntity;

public interface TournamentDetailsRepository extends JpaRepository<TournamentDetailsEntity, Long> {
}