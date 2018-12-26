package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "matches", schema = "public", catalog = "ligos")
public class MatchesEntity implements Serializable {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "tournamentid", referencedColumnName = "id")
	private TournamentEntity tournamentByTournamentid;

	@ManyToOne
	@JoinColumn(name = "team_first", referencedColumnName = "id", nullable = false)
	private TeamEntity teamByTeamFirst;

	@ManyToOne
	@JoinColumn(name = "team_second", referencedColumnName = "id", nullable = false)
	private TeamEntity teamByTeamSecond;

	@ManyToOne
	@JoinColumn(name = "stageid", referencedColumnName = "id", nullable = false)
	private StagesEntity stagesByStageid;

	@ManyToOne
	@JoinColumn(name = "courtid", referencedColumnName = "id", nullable = false)
	private CourtEntity courtByCourtid;

	@Column(name = "DATE", nullable = false)
	private Date date;

	@Column(name = "goals_first", nullable = false)
	private Integer goalsFirst;

	@Column(name = "goals_second", nullable = false)
	private Integer goalsSecond;

	@Column(name = "stage", nullable = false)
	private Integer stage;

	@OneToMany(mappedBy = "matchesByMatcheid")
	private Collection<GoalsListEntity> goalsListsById;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public Collection<GoalsListEntity> getGoalsListsById() {
		return goalsListsById;
	}

	public void setGoalsListsById(Collection<GoalsListEntity> goalsListsById) {
		this.goalsListsById = goalsListsById;
	}

	public TournamentEntity getTournamentByTournamentid() {
		return tournamentByTournamentid;
	}

	public void setTournamentByTournamentid(TournamentEntity tournamentByTournamentid) {
		this.tournamentByTournamentid = tournamentByTournamentid;
	}

	public TeamEntity getTeamByTeamFirst() {
		return teamByTeamFirst;
	}

	public void setTeamByTeamFirst(TeamEntity teamByTeamFirst) {
		this.teamByTeamFirst = teamByTeamFirst;
	}

	public TeamEntity getTeamByTeamSecond() {
		return teamByTeamSecond;
	}

	public void setTeamByTeamSecond(TeamEntity teamByTeamSecond) {
		this.teamByTeamSecond = teamByTeamSecond;
	}

	public StagesEntity getStagesByStageid() {
		return stagesByStageid;
	}

	public void setStagesByStageid(StagesEntity stagesByStageid) {
		this.stagesByStageid = stagesByStageid;
	}

	public CourtEntity getCourtByCourtid() {
		return courtByCourtid;
	}

	public void setCourtByCourtid(CourtEntity courtByCourtid) {
		this.courtByCourtid = courtByCourtid;
	}
}
