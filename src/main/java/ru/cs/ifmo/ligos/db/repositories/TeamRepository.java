package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.cs.ifmo.ligos.db.entities.Position;
import ru.cs.ifmo.ligos.db.entities.TeamEntity;
import ru.cs.ifmo.ligos.dto.TeamDTO;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

	void removeById(TeamEntity teamEntity);
	boolean findByName(String name);


	@Query("select new ru.cs.ifmo.ligos.dto.TeamDTO(t.id, t.name) from TeamEntity t " +
			"left join t.teamUserEntities tu where tu.position <> :position ")
	List<TeamDTO> findTeamsByPosition(@Param("position") Position position);

}