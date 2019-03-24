package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.ChatEntity;
import ru.cs.ifmo.ligos.db.entities.MessageEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

	List<MessageEntity> findAllByChat(ChatEntity chatEntity);

}