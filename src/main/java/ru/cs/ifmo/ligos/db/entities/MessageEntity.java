package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "message", schema = "public", catalog = "ligos")
public class MessageEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "chatid", nullable = false)
	private ChatEntity chatid;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "senderid", nullable = false)
	private UsersEntity senderid;

	@Column(name = "context", nullable = false, length = -1)
	private String context;

	@Column(name = "time", nullable = false)
	private Date time;
}
