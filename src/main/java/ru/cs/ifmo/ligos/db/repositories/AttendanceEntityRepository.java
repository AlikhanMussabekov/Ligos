package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.cs.ifmo.ligos.db.entities.AttendanceEntity;
import ru.cs.ifmo.ligos.db.entities.SectionDetailsEntity;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;

import java.util.List;
import java.util.Optional;

public interface AttendanceEntityRepository extends JpaRepository<AttendanceEntity, Long> {

	@Query("select count(ae)>0 from AttendanceEntity as ae where ae.user=:user and ae.sectionDetails IN (:sectionDetails)")
	boolean existsBySectionAndUser(@Param("sectionDetails") List<SectionDetailsEntity> sectionDetailsEntities,
								   @Param("user") UsersEntity usersEntity);

}
