package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

	boolean existsByEmail(String email);

	OrganizationEntity findByEmail(String email);

	void deleteByEmail(String email);

}