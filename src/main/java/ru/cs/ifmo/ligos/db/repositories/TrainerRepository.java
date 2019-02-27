package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;
import ru.cs.ifmo.ligos.db.entities.TrainerEntity;

import java.util.List;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

	List<TrainerEntity> findAllByOrganizationid(OrganizationEntity organizationEntity);

}