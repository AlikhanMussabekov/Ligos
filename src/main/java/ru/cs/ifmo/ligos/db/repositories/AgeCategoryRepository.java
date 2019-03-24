package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.AgeCategoryEntity;

import java.util.Optional;

public interface AgeCategoryRepository extends JpaRepository<AgeCategoryEntity, Long> {

	Optional<AgeCategoryEntity> findByFromAndTo(Integer FROM, Integer TO);

}