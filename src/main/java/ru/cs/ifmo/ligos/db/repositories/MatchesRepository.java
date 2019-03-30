package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.MatchesEntity;

import java.util.List;

public interface MatchesRepository extends JpaRepository<MatchesEntity, Long> {

}