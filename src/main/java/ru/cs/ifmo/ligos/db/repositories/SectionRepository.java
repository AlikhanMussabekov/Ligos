package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

}