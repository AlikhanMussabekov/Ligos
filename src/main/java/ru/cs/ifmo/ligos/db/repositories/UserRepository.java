package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cs.ifmo.ligos.db.entities.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	/*@Query("select name from TEAM where ")
	public List<UserEntity> findUserTeams(@Param("userId") Long id);*/

}