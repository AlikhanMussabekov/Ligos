package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

	boolean existsByEmail(String email);

	Optional<UsersEntity> findByEmail(String email);

	void deleteByEmail(String email);

	@Query("update UsersEntity u set u.surname = :surname, " +
			"u.patronymic = :patronymic, " +
			"u.phoneNumber = :phoneNumber, " +
			"u.city = :city, " +
			"u.gender = :gender, " +
			"u.birthday = :birthday, " +
			"u.phoneNumber = :photo " +
			"where u.id = :id")
	Optional<UsersEntity> updateInfo(@Param("surname") String surname,
									 @Param("patronymic") String patronymic,
									 @Param("phoneNumber") String phoneNumber,
									 @Param("city") String city,
									 @Param("gender") String gender,
									 @Param("birthday") Date birthday,
									 @Param("photo") byte[] photo,
									 @Param("id") Integer id);

	/*@Query("select name from TEAM where ")
	public List<UserEntity> findUserTeams(@Param("userId") Long id);*/

}