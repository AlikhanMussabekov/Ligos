package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;
import ru.cs.ifmo.ligos.db.entities.TrainerEntity;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

	List<TrainerEntity> findAllByOrganization(OrganizationEntity organizationEntity);

}