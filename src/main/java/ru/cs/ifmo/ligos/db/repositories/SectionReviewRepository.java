package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;
import ru.cs.ifmo.ligos.db.entities.SectionReviewEntity;

import java.util.List;

public interface SectionReviewRepository extends JpaRepository<SectionReviewEntity, Long> {

	List<SectionReviewEntity> findAllBySection(SectionEntity sectionEntity);

}