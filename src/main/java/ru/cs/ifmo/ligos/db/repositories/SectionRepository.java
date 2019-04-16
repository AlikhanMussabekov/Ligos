package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

	List<SectionEntity> findTopByRaitingOrderByRaitingDesc(Short num);

	Page<SectionEntity> findAll(Pageable pageable);

	Optional<List<SectionEntity>> findByOrganization(OrganizationEntity organizationEntity);

}