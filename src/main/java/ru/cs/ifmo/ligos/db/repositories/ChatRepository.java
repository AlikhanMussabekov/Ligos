package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.ChatEntity;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

	List<ChatEntity> findAllByUsers(UsersEntity usersEntity);

	void removeById(ChatEntity chatEntity);

}