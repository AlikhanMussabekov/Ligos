package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.GoalsListEntity;
import ru.cs.ifmo.ligos.db.entities.MatchesEntity;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;

import java.util.List;
import java.util.Optional;

public interface GoalsListRepository extends JpaRepository<GoalsListEntity, Long> {

	Optional<List<GoalsListEntity>> findAllByMatch(MatchesEntity matchesEntity);

	Optional<List<GoalsListEntity>> findAllByUser(UsersEntity usersEntity);
}