package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "age_category", schema = "public", catalog = "ligos")
public class AgeCategoryEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "FROM", nullable = false)
	private Integer from;

	@Column(name = "TO", nullable = false)
	private Integer to;

	@OneToMany(mappedBy = "ageCategoryByAgeCategoryid")
	private Collection<SectionDetailsEntity> sectionDetailsById;

	@OneToMany(mappedBy = "ageCategoryByAgeCategoryid")
	private Collection<TournamentDetailsEntity> tournamentDetailsById;


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

	public Collection<SectionDetailsEntity> getSectionDetailsById() {
		return sectionDetailsById;
	}
	public void setSectionDetailsById(Collection<SectionDetailsEntity> sectionDetailsById) {
		this.sectionDetailsById = sectionDetailsById;
	}


	public Collection<TournamentDetailsEntity> getTournamentDetailsById() {
		return tournamentDetailsById;
	}
	public void setTournamentDetailsById(Collection<TournamentDetailsEntity> tournamentDetailsById) {
		this.tournamentDetailsById = tournamentDetailsById;
	}
}
