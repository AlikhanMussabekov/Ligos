package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.CourtEntity;
import ru.cs.ifmo.ligos.db.entities.CourtReviewsEntity;

import java.util.List;

public interface CourtReviewsRepository extends JpaRepository<CourtReviewsEntity, Long> {

	List<CourtReviewsEntity> findAllByCourtid(CourtEntity courtEntity);

}