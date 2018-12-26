package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.AgeCategoryEntity;
import ru.cs.ifmo.ligos.db.entities.SectionDetailsEntity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface SectionDetailsRepository extends JpaRepository<SectionDetailsEntity, Long> {

	public List<SectionDetailsEntity>
	findAllByAgeCategoryByAgeCategoryidAndPriceInAndTimeStartIn(
			AgeCategoryEntity ageCategoryEntity,
			Collection<Integer> price,
			Collection<Timestamp> timestamp);

}