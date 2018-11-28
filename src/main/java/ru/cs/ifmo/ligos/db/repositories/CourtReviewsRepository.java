package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.CourtReviewsEntity;

public interface CourtReviewsRepository extends JpaRepository<CourtReviewsEntity, Long> {
}