package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "goals_list", schema = "public", catalog = "ligos")
public class GoalsListEntity implements Serializable {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "time", nullable = false)
	private Time time;

	@ManyToOne
	@JoinColumn(name = "matcheid", referencedColumnName = "id", nullable = false)
	private MatchesEntity matchesByMatcheid;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
	private UserEntity userByUserid;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
