package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.CourtEntity;
import ru.cs.ifmo.ligos.db.entities.CourtReviewEntity;

import java.util.List;

public interface CourtReviewRepository extends JpaRepository<CourtReviewEntity, Long> {

	List<CourtReviewEntity> findAllByCourt(CourtEntity courtEntity);

}