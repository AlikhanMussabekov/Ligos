package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.StagesEntity;

public interface StagesRepository extends JpaRepository<StagesEntity, Long> {
}