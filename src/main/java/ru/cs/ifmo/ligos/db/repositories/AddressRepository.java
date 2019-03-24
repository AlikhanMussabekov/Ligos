package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.AddressEntity;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

	Optional<AddressEntity> findById(Long id);

}
