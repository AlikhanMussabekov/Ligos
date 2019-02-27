package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cs.ifmo.ligos.db.entities.AgeCategoryEntity;
import ru.cs.ifmo.ligos.db.entities.SectionDetailsEntity;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface SectionDetailsRepository extends JpaRepository<SectionDetailsEntity, Long> {

	List<SectionDetailsEntity> findAllBySectionid(SectionEntity sectionEntity);

	@Query("select sde.sectionid from SectionDetailsEntity sde where sde.ageCategoryid = ?1")
	List<SectionEntity> selectSectionsByAgeCategory(AgeCategoryEntity ageCategoryEntity, Pageable pageable);

}