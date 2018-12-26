package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "chat", schema = "public", catalog = "ligos")
public class ChatEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/*@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private UserEntity userid;*/
	@ManyToMany(mappedBy = "chats")
	private Set<UserEntity> users;

	@Column(name = "created")
	private Date created;

	@Column(name = "type")
	private Boolean type;

	@OneToMany(mappedBy = "chatid")
	private Collection<MessageEntity> messagesById;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public Collection<MessageEntity> getMessagesById() {
		return messagesById;
	}

	public void setMessagesById(Collection<MessageEntity> messagesById) {
		this.messagesById = messagesById;
	}
}
