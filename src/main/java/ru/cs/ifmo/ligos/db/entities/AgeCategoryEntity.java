package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "age_category", schema = "public", catalog = "ligos")
public class AgeCategoryEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "FROM", nullable = false)
	private Integer from;

	@Column(name = "TO", nullable = false)
	private Integer to;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AgeCategoryEntity that = (AgeCategoryEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(from, that.from) &&
				Objects.equals(to, that.to);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, from, to);
	}
}
