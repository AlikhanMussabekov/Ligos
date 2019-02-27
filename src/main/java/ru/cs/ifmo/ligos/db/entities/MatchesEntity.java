package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "matches", schema = "public", catalog = "ligos")
public class MatchesEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "tournamentid", nullable = true)
	private TournamentEntity tournamentid;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "team_first", nullable = false)
	private TeamEntity teamFirst;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "team_second", nullable = false)
	private TeamEntity teamSecond;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "stageid", nullable = false)
	private StagesEntity stageid;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "courtid", nullable = false)
	private CourtEntity courtid;

	@Column(name = "DATE", nullable = false)
	private Date date;

	@Column(name = "goals_first", nullable = false)
	private Integer goalsFirst;

	@Column(name = "goals_second", nullable = false)
	private Integer goalsSecond;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TournamentEntity getTournamentid() {
		return tournamentid;
	}

	public void setTournamentid(TournamentEntity tournamentid) {
		this.tournamentid = tournamentid;
	}

	public TeamEntity getTeamFirst() {
		return teamFirst;
	}

	public void setTeamFirst(TeamEntity teamFirst) {
		this.teamFirst = teamFirst;
	}

	public TeamEntity getTeamSecond() {
		return teamSecond;
	}

	public void setTeamSecond(TeamEntity teamSecond) {
		this.teamSecond = teamSecond;
	}

	public StagesEntity getStageid() {
		return stageid;
	}

	public void setStageid(StagesEntity stageid) {
		this.stageid = stageid;
	}

	public CourtEntity getCourtid() {
		return courtid;
	}

	public void setCourtid(CourtEntity courtid) {
		this.courtid = courtid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getGoalsFirst() {
		return goalsFirst;
	}

	public void setGoalsFirst(Integer goalsFirst) {
		this.goalsFirst = goalsFirst;
	}

	public Integer getGoalsSecond() {
		return goalsSecond;
	}

	public void setGoalsSecond(Integer goalsSecond) {
		this.goalsSecond = goalsSecond;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MatchesEntity that = (MatchesEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(tournamentid, that.tournamentid) &&
				Objects.equals(teamFirst, that.teamFirst) &&
				Objects.equals(teamSecond, that.teamSecond) &&
				Objects.equals(stageid, that.stageid) &&
				Objects.equals(courtid, that.courtid) &&
				Objects.equals(date, that.date) &&
				Objects.equals(goalsFirst, that.goalsFirst) &&
				Objects.equals(goalsSecond, that.goalsSecond);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tournamentid, teamFirst, teamSecond, stageid, courtid, date, goalsFirst, goalsSecond);
	}
}
