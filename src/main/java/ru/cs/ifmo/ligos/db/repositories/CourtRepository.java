package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.AddressEntity;
import ru.cs.ifmo.ligos.db.entities.CourtEntity;
import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CourtRepository extends JpaRepository<CourtEntity, Long> {

	List<CourtEntity> findTopByRaitingOrderByRaitingDesc(Short num);

	List<CourtRepository> findAllByAddress(AddressEntity addressEntity);

	Optional<List<CourtEntity>> findByOrganization(OrganizationEntity organizationEntity);
}