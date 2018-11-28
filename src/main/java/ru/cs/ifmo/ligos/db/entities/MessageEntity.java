package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message", schema = "public", catalog = "ligos")
public class MessageEntity {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Basic
	@Column(name = "chatid", nullable = false)
	private Integer chatid;

	@Basic
	@Column(name = "context", nullable = false, length = -1)
	private String context;

	@Basic
	@Column(name = "time", nullable = false)
	private Timestamp time;

	@Basic
	@Column(name = "type", nullable = false)
	private Boolean type;

	@ManyToOne
	@JoinColumn(name = "chatid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private ChatEntity chatByChatid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChatid() {
		return chatid;
	}

	public void setChatid(Integer chatid) {
		this.chatid = chatid;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public ChatEntity getChatByChatid() {
		return chatByChatid;
	}

	public void setChatByChatid(ChatEntity chatByChatid) {
		this.chatByChatid = chatByChatid;
	}
}
