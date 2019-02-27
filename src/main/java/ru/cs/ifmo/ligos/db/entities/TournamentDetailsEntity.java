package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tournament_details", schema = "public", catalog = "ligos")
public class TournamentDetailsEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "age_categoryid", nullable = false)
	private AgeCategoryEntity ageCategoryid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tournamentid", nullable = false)
	private TournamentEntity tournamentid;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "max_teams_count", nullable = false)
	private Integer maxTeamsCount;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tournament_teams",
			joinColumns = @JoinColumn(name = "tournament_detailsid", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "teamid", referencedColumnName = "id"))
	private Set<TeamEntity> teams;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AgeCategoryEntity getAgeCategoryid() {
		return ageCategoryid;
	}

	public void setAgeCategoryid(AgeCategoryEntity ageCategoryid) {
		this.ageCategoryid = ageCategoryid;
	}

	public TournamentEntity getTournamentid() {
		return tournamentid;
	}

	public void setTournamentid(TournamentEntity tournamentid) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TournamentDetailsEntity that = (TournamentDetailsEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(ageCategoryid, that.ageCategoryid) &&
				Objects.equals(tournamentid, that.tournamentid) &&
				Objects.equals(price, that.price) &&
				Objects.equals(maxTeamsCount, that.maxTeamsCount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ageCategoryid, tournamentid, price, maxTeamsCount);
	}

	public Set<TeamEntity> getTeams() {
		return teams;
	}

	public void setTeams(Set<TeamEntity> teams) {
		this.teams = teams;
	}
}
