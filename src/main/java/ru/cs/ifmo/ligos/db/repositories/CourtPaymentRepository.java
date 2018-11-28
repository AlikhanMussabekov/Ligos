package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.CourtPaymentEntity;

public interface CourtPaymentRepository extends JpaRepository<CourtPaymentEntity, Long> {

}