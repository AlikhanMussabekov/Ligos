package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "tournament_teams", schema = "public", catalog = "ligos")
public class TournamentTeamsEntity {
	@Id
	@Column(name = "tournament_detailsid", nullable = false)
	private Integer tournamentDetailsid;

	@Basic
	@Column(name = "teamid", nullable = false)
	private Integer teamid;

	@OneToOne
	@JoinColumn(name = "tournament_detailsid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private TournamentDetailsEntity tournamentDetailsByTournamentDetailsid;

	@ManyToOne
	@JoinColumn(name = "teamid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private TeamEntity teamByTeamid;

	public Integer getTournamentDetailsid() {
		return tournamentDetailsid;
	}

	public void setTournamentDetailsid(Integer tournamentDetailsid) {
		this.tournamentDetailsid = tournamentDetailsid;
	}

	public Integer getTeamid() {
		return teamid;
	}

	public void setTeamid(Integer teamid) {
		this.teamid = teamid;
	}

	public TournamentDetailsEntity getTournamentDetailsByTournamentDetailsid() {
		return tournamentDetailsByTournamentDetailsid;
	}

	public void setTournamentDetailsByTournamentDetailsid(TournamentDetailsEntity tournamentDetailsByTournamentDetailsid) {
		this.tournamentDetailsByTournamentDetailsid = tournamentDetailsByTournamentDetailsid;
	}

	public TeamEntity getTeamByTeamid() {
		return teamByTeamid;
	}

	public void setTeamByTeamid(TeamEntity teamByTeamid) {
		this.teamByTeamid = teamByTeamid;
	}
}
