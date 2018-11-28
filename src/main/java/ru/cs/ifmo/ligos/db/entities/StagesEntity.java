package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "stages", schema = "public", catalog = "ligos")
public class StagesEntity {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Basic
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@OneToMany(mappedBy = "stagesByStageid")
	private Collection<MatchesEntity> matchesById;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Collection<MatchesEntity> getMatchesById() {
		return matchesById;
	}

	public void setMatchesById(Collection<MatchesEntity> matchesById) {
		this.matchesById = matchesById;
	}
}
