package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

	boolean existsByEmail(String email);

	Optional<UsersEntity> findByEmail(String email);

	void deleteByEmail(String email);

	/*@Query("select name from TEAM where ")
	public List<UserEntity> findUserTeams(@Param("userId") Long id);*/

}