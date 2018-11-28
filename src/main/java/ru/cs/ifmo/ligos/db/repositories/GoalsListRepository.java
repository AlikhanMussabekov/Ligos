package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.GoalsListEntity;

public interface GoalsListRepository extends JpaRepository<GoalsListEntity, Long> {
}