package ru.cs.ifmo.ligos.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.cs.ifmo.ligos.db.entities.ChatChannel;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ChatChannelRepository extends JpaRepository<ChatChannel,  String> {

	@Query(" FROM"
			+ "    ChatChannel c"
			+ "  WHERE"
			+ "    c.userOne.id IN (:userOneId, :userTwoId) "
			+ "  AND"
			+ "    c.userTwo.id IN (:userOneId, :userTwoId)")
	public List<ChatChannel> findExistingChannel(
			@Param("userOneId") long userOneId, @Param("userTwoId") long userTwoId);

	@Query(" SELECT"
			+ "    uuid"
			+ "  FROM"
			+ "    ChatChannel c"
			+ "  WHERE"
			+ "    c.userOne.id IN (:userIdOne, :userIdTwo)"
			+ "  AND"
			+ "    c.userTwo.id IN (:userIdOne, :userIdTwo)")
	public String getChannelUuid(
			@Param("userIdOne") long userIdOne, @Param("userIdTwo") long userIdTwo);

	@Query(" FROM"
			+ "    ChatChannel c"
			+ "  WHERE"
			+ "    c.uuid = :uuid")
	public ChatChannel getChannelDetails(@Param("uuid") String uuid);
}
