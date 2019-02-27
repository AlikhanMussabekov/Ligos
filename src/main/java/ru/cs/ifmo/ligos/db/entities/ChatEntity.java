package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "chat", schema = "public", catalog = "ligos")
public class ChatEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "type", nullable = false)
	private Boolean type;

	@ManyToMany(mappedBy = "chats")
	private Set<UsersEntity> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChatEntity that = (ChatEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(created, that.created) &&
				Objects.equals(type, that.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, created, type);
	}
}
