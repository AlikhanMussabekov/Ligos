package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tournament_details", schema = "public", catalog = "ligos")
public class TournamentDetailsEntity implements Serializable {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "max_teams_count", nullable = false)
	private Integer maxTeamsCount;

	@ManyToOne
	@JoinColumn(name = "age_categoryid", referencedColumnName = "id", nullable = false)
	private AgeCategoryEntity ageCategoryByAgeCategoryid;

	@ManyToOne
	@JoinColumn(name = "tournamentid", referencedColumnName = "id", nullable = false)
	private TournamentEntity tournamentByTournamentid;

	/*@OneToOne(mappedBy = "tournamentDetailsByTournamentDetailsid")
	private TournamentTeamsEntity tournamentTeamsById;
*/

	@ManyToMany(mappedBy = "tournamentDetails")
	private Set<TeamEntity> teams;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<TeamEntity> getTeams() {
		return teams;
	}

	public void setTeams(Set<TeamEntity> teams) {
		this.teams = teams;
	}
}
