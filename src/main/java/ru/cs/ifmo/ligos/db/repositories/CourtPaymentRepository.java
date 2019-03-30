package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.CourtEntity;
import ru.cs.ifmo.ligos.db.entities.CourtPaymentEntity;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;

import java.util.List;
import java.util.Optional;

public interface CourtPaymentRepository extends JpaRepository<CourtPaymentEntity, Long> {

	Optional < List<CourtPaymentEntity>> findAllByCourt(CourtEntity courtEntity);

	boolean existsByCourtAndUser(CourtEntity courtEntity, UsersEntity usersEntity);
}
