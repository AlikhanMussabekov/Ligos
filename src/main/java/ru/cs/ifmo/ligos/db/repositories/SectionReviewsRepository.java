package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.SectionReviewsEntity;

public interface SectionReviewsRepository extends JpaRepository<SectionReviewsEntity, Long> {
}