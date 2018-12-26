package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "message", schema = "public", catalog = "ligos")
public class MessageEntity implements Serializable {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "context", nullable = false)
	private String context;

	@Column(name = "time", nullable = false)
	private Timestamp time;

	@ManyToOne
	@JoinColumn(name = "chatid", referencedColumnName = "id")
	private ChatEntity chatid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public ChatEntity getChatByChatid() {
		return chatid;
	}

	public void setChatByChatid(ChatEntity chatid) {
		this.chatid = chatid;
	}
}
