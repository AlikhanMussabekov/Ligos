package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "goals_list", schema = "public", catalog = "ligos")
public class GoalsListEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "matcheid", nullable = false)
	private MatchesEntity matcheid;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity userid;

	@Column(name = "time", nullable = false)
	private Date time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MatchesEntity getMatcheid() {
		return matcheid;
	}

	public void setMatcheid(MatchesEntity matcheid) {
		this.matcheid = matcheid;
	}

	public UsersEntity getUserid() {
		return userid;
	}

	public void setUserid(UsersEntity userid) {
		this.userid = userid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GoalsListEntity that = (GoalsListEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(matcheid, that.matcheid) &&
				Objects.equals(userid, that.userid) &&
				Objects.equals(time, that.time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, matcheid, userid, time);
	}
}
