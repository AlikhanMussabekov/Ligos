package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.SectionDetailsEntity;

public interface SectionDetailsRepository extends JpaRepository<SectionDetailsEntity, Long> {
}