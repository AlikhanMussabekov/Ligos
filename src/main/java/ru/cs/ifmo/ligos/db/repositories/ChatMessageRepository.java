package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.cs.ifmo.ligos.db.entities.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

	@Query(" FROM ChatMessage m WHERE " +
			"m.authorUser.id IN (:userIdOne, :userIdTwo)"
			+ "  AND"
			+ "    m.recipientUser.id IN (:userIdOne, :userIdTwo)"
			+ "  ORDER BY"
			+ "    m.timeSent"
			+ "  DESC")
	public List<ChatMessage> getExistingChatMessages(
			@Param("userIdOne") long userIdOne, @Param("userIdTwo") long userIdTwo, Pageable pageable);
}