package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.TeamUserEntity;

public interface TeamUserRepository extends JpaRepository<TeamUserEntity, Long> {
}