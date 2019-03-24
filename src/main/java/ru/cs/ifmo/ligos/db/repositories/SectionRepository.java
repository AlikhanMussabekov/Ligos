package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cs.ifmo.ligos.db.entities.AgeCategoryEntity;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

	List<SectionEntity> findTopByRaitingOrderByRaitingDesc(Short num);
}