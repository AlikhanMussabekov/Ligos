package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "team", schema = "public", catalog = "ligos")
public class TeamEntity {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Basic
	@Column(name = "trainerid", nullable = true)
	private Integer trainerid;

	@Basic
	@Column(name = "captainid", nullable = false)
	private Integer captainid;

	@Basic
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Basic
	@Column(name = "photo", nullable = true, length = -1)
	private String photo;

	@OneToMany(mappedBy = "teamByTeamFirst")
	private Collection<MatchesEntity> matchesById;

	@OneToMany(mappedBy = "teamByTeamSecond")
	private Collection<MatchesEntity> matchesById_0;

	@ManyToOne
	@JoinColumn(name = "trainerid", referencedColumnName = "userid", insertable = false, updatable = false)
	private TrainerEntity trainerByTrainerid;

	@ManyToOne
	@JoinColumn(name = "captainid", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private UserEntity userByCaptainid;

	@OneToOne(mappedBy = "teamByTeamid")
	private TeamUserEntity teamUserById;

	@OneToMany(mappedBy = "teamByTeamid")
	private Collection<TournamentTeamsEntity> tournamentTeamsById;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTrainerid() {
		return trainerid;
	}

	public void setTrainerid(Integer trainerid) {
		this.trainerid = trainerid;
	}

	public Integer getCaptainid() {
		return captainid;
	}

	public void setCaptainid(Integer captainid) {
		this.captainid = captainid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Collection<MatchesEntity> getMatchesById() {
		return matchesById;
	}

	public void setMatchesById(Collection<MatchesEntity> matchesById) {
		this.matchesById = matchesById;
	}

	public Collection<MatchesEntity> getMatchesById_0() {
		return matchesById_0;
	}

	public void setMatchesById_0(Collection<MatchesEntity> matchesById_0) {
		this.matchesById_0 = matchesById_0;
	}

	public TrainerEntity getTrainerByTrainerid() {
		return trainerByTrainerid;
	}

	public void setTrainerByTrainerid(TrainerEntity trainerByTrainerid) {
		this.trainerByTrainerid = trainerByTrainerid;
	}

	public UserEntity getUserByCaptainid() {
		return userByCaptainid;
	}

	public void setUserByCaptainid(UserEntity userByCaptainid) {
		this.userByCaptainid = userByCaptainid;
	}

	public TeamUserEntity getTeamUserById() {
		return teamUserById;
	}

	public void setTeamUserById(TeamUserEntity teamUserById) {
		this.teamUserById = teamUserById;
	}

	public Collection<TournamentTeamsEntity> getTournamentTeamsById() {
		return tournamentTeamsById;
	}

	public void setTournamentTeamsById(Collection<TournamentTeamsEntity> tournamentTeamsById) {
		this.tournamentTeamsById = tournamentTeamsById;
	}
}
