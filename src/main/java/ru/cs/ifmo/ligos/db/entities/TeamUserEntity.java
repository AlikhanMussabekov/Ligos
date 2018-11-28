package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "team_user", schema = "public", catalog = "ligos")
public class TeamUserEntity {
	@Id
	@Column(name = "teamid", nullable = false)
	private Integer teamid;

	@Basic
	@Column(name = "userid", nullable = false)
	private Integer userid;

	@OneToOne
	@JoinColumn(name = "teamid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private TeamEntity teamByTeamid;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private UserEntity userByUserid;

	public Integer getTeamid() {
		return teamid;
	}

	public void setTeamid(Integer teamid) {
		this.teamid = teamid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public TeamEntity getTeamByTeamid() {
		return teamByTeamid;
	}

	public void setTeamByTeamid(TeamEntity teamByTeamid) {
		this.teamByTeamid = teamByTeamid;
	}

	public UserEntity getUserByUserid() {
		return userByUserid;
	}

	public void setUserByUserid(UserEntity userByUserid) {
		this.userByUserid = userByUserid;
	}
}
