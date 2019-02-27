package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "message", schema = "public", catalog = "ligos")
public class MessageEntity implements Serializable {

	@Id
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChatEntity getChatid() {
		return chatid;
	}

	public void setChatid(ChatEntity chatid) {
		this.chatid = chatid;
	}

	public UsersEntity getSenderid() {
		return senderid;
	}

	public void setSenderid(UsersEntity senderid) {
		this.senderid = senderid;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MessageEntity that = (MessageEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(chatid, that.chatid) &&
				Objects.equals(senderid, that.senderid) &&
				Objects.equals(context, that.context) &&
				Objects.equals(time, that.time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, chatid, senderid, context, time);
	}
}
