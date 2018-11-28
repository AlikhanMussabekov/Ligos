package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "chat", schema = "public", catalog = "ligos")
public class ChatEntity {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Basic
	@Column(name = "userid", nullable = false)
	private Integer userid;

	@Basic
	@Column(name = "userid2", nullable = false)
	private Integer userid2;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private UserEntity userByUserid;

	@ManyToOne
	@JoinColumn(name = "userid2", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private UserEntity userByUserid2;

	@OneToMany(mappedBy = "chatByChatid")
	private Collection<MessageEntity> messagesById;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getUserid2() {
		return userid2;
	}
	public void setUserid2(Integer userid2) {
		this.userid2 = userid2;
	}

	public UserEntity getUserByUserid() {
		return userByUserid;
	}
	public void setUserByUserid(UserEntity userByUserid) {
		this.userByUserid = userByUserid;
	}

	public UserEntity getUserByUserid2() {
		return userByUserid2;
	}
	public void setUserByUserid2(UserEntity userByUserid2) {
		this.userByUserid2 = userByUserid2;
	}

	public Collection<MessageEntity> getMessagesById() {
		return messagesById;
	}
	public void setMessagesById(Collection<MessageEntity> messagesById) {
		this.messagesById = messagesById;
	}
}
