package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.CourtEntity;
import ru.cs.ifmo.ligos.db.entities.CourtPaymentEntity;

import java.util.List;

public interface CourtPaymentRepository extends JpaRepository<CourtPaymentEntity, Long> {

	List<CourtPaymentEntity> findAllByCourtid(CourtEntity courtEntity);

}