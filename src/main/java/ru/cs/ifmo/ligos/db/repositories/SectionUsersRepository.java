package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cs.ifmo.ligos.db.entities.SectionUsersEntity;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;

import java.util.List;

public interface SectionUsersRepository extends JpaRepository<SectionUsersEntity, Long> {

	@Query("SELECT sue.user from SectionUsersEntity sue where section_details=?1")
	public List<UsersEntity> selectAllUsersInSection(SectionUsersEntity sectionUsersEntity);
}
