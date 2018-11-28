package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.CourtEntity;

public interface CourtRepository extends JpaRepository<CourtEntity, Long> {
}