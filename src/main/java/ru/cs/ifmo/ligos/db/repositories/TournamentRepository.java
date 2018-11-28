package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.TournamentEntity;

public interface TournamentRepository extends JpaRepository<TournamentEntity, Long> {
}