package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.GoalsListEntity;
import ru.cs.ifmo.ligos.db.entities.MatchesEntity;

import java.util.List;

public interface GoalsListRepository extends JpaRepository<GoalsListEntity, Long> {

	List<GoalsListEntity> findAllByMatcheid(MatchesEntity matchesEntity);

}