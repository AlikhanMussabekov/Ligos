package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "tournament_details", schema = "public", catalog = "ligos")
public class TournamentDetailsEntity {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Basic
	@Column(name = "age_categoryid", nullable = false,insertable = false, updatable = false)
	private Integer ageCategoryid;

	@Basic
	@Column(name = "tournamentid", nullable = false)
	private Integer tournamentid;

	@Basic
	@Column(name = "price", nullable = false)
	private Integer price;

	@Basic
	@Column(name = "max_teams_count", nullable = false)
	private Integer maxTeamsCount;

	@ManyToOne
	@JoinColumn(name = "age_categoryid", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private AgeCategoryEntity ageCategoryByAgeCategoryid;

	@ManyToOne
	@JoinColumn(name = "tournamentid", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private TournamentEntity tournamentByTournamentid;

	@OneToOne(mappedBy = "tournamentDetailsByTournamentDetailsid")
	private TournamentTeamsEntity tournamentTeamsById;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAgeCategoryid() {
		return ageCategoryid;
	}

	public void setAgeCategoryid(Integer ageCategoryid) {
		this.ageCategoryid = ageCategoryid;
	}

	public Integer getTournamentid() {
		return tournamentid;
	}

	public void setTournamentid(Integer tournamentid) {
		this.tournamentid = tournamentid;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getMaxTeamsCount() {
		return maxTeamsCount;
	}

	public void setMaxTeamsCount(Integer maxTeamsCount) {
		this.maxTeamsCount = maxTeamsCount;
	}


	public AgeCategoryEntity getAgeCategoryByAgeCategoryid() {
		return ageCategoryByAgeCategoryid;
	}

	public void setAgeCategoryByAgeCategoryid(AgeCategoryEntity ageCategoryByAgeCategoryid) {
		this.ageCategoryByAgeCategoryid = ageCategoryByAgeCategoryid;
	}

	public TournamentEntity getTournamentByTournamentid() {
		return tournamentByTournamentid;
	}

	public void setTournamentByTournamentid(TournamentEntity tournamentByTournamentid) {
		this.tournamentByTournamentid = tournamentByTournamentid;
	}

	public TournamentTeamsEntity getTournamentTeamsById() {
		return tournamentTeamsById;
	}

	public void setTournamentTeamsById(TournamentTeamsEntity tournamentTeamsById) {
		this.tournamentTeamsById = tournamentTeamsById;
	}
}
