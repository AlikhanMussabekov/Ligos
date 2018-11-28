package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cs.ifmo.ligos.db.entities.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}