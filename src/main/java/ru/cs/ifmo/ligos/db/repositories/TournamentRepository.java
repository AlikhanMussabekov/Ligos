package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;
import ru.cs.ifmo.ligos.db.entities.TournamentEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface TournamentRepository extends JpaRepository<TournamentEntity, Long> {

	List<TournamentEntity> findTopByRaitingOrderByRaitingDesc(Short num);

}