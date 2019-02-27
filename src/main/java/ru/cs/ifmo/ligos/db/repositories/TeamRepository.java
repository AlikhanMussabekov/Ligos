package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

	void removeById(TeamEntity teamEntity);

}