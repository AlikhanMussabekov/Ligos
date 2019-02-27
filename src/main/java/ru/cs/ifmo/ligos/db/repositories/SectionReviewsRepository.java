package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;
import ru.cs.ifmo.ligos.db.entities.SectionReviewsEntity;

import java.util.List;

public interface SectionReviewsRepository extends JpaRepository<SectionReviewsEntity, Long> {

}