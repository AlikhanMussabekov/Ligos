package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.TrainerEntity;
import ru.cs.ifmo.ligos.db.entities.TrainerReviewsEntity;

import java.util.List;

public interface TrainerReviewsRepository extends JpaRepository<TrainerReviewsEntity, Long> {

	List<TrainerReviewsEntity> findAllByTraineruser(TrainerEntity trainerEntity);

}