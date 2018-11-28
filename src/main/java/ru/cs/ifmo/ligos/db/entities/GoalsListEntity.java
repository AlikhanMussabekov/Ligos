package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "goals_list", schema = "public", catalog = "ligos")
public class GoalsListEntity {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Basic
	@Column(name = "matcheid", nullable = false)
	private Integer matcheid;

	@Basic
	@Column(name = "userid", nullable = false)
	private Integer userid;

	@Basic
	@Column(name = "time", nullable = false)
	private Time time;

	@ManyToOne
	@JoinColumn(name = "matcheid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private MatchesEntity matchesByMatcheid;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private UserEntity userByUserid;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMatcheid() {
		return matcheid;
	}

	public void setMatcheid(Integer matcheid) {
		this.matcheid = matcheid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}


	public MatchesEntity getMatchesByMatcheid() {
		return matchesByMatcheid;
	}

	public void setMatchesByMatcheid(MatchesEntity matchesByMatcheid) {
		this.matchesByMatcheid = matchesByMatcheid;
	}

	public UserEntity getUserByUserid() {
		return userByUserid;
	}

	public void setUserByUserid(UserEntity userByUserid) {
		this.userByUserid = userByUserid;
	}
}
