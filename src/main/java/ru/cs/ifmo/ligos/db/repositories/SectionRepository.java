package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;

import java.util.List;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

	List<SectionEntity> getTopSections();

}