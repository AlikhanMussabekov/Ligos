package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "chat", schema = "public", catalog = "ligos")
public class ChatEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "chat_id_seq", sequenceName = "chat_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "type", nullable = false)
	private Boolean type;

	@ManyToMany(mappedBy = "chats")
	private Set<UsersEntity> users;

}
