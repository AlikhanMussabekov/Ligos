package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "message", schema = "public", catalog = "ligos")
public class ChatMessage implements Serializable {

	@Id
	@SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;


	@OneToOne
	@JoinColumn(name = "authorUserId")
	private UsersEntity authorUser;

	@OneToOne
	@JoinColumn(name = "recipientUserId")
	private UsersEntity recipientUser;

	@Column(name = "TIME", nullable = false)
	private Date timeSent;

	@Column(name = "CONTEXT", nullable = false)
	private String contents;

	public ChatMessage(UsersEntity authorUser, UsersEntity recipientUser, String contents) {
		this.authorUser = authorUser;
		this.recipientUser = recipientUser;
		this.contents = contents;
		this.timeSent = new Date();
	}

}
