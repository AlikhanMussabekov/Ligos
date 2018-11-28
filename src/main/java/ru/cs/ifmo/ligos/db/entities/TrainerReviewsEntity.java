package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "trainer_reviews", schema = "public", catalog = "ligos")
public class TrainerReviewsEntity {
	@Id
	@Column(name = "traineruserid", nullable = false)
	private Integer traineruserid;
	@Basic
	@Column(name = "userid", nullable = false)
	private Integer userid;
	@Basic
	@Column(name = "review", nullable = false, length = -1)
	private String review;
	@Basic
	@Column(name = "raiting", nullable = false)
	private Short raiting;
	@Basic
	@Column(name = "DATE", nullable = false)
	private Timestamp date;
	@OneToOne
	@JoinColumn(name = "traineruserid", referencedColumnName = "userid", nullable = false,insertable = false, updatable = false)
	private TrainerEntity trainerByTraineruserid;
	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private UserEntity userByUserid;

	public Integer getTraineruserid() {
		return traineruserid;
	}

	public void setTraineruserid(Integer traineruserid) {
		this.traineruserid = traineruserid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Short getRaiting() {
		return raiting;
	}

	public void setRaiting(Short raiting) {
		this.raiting = raiting;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}


	public TrainerEntity getTrainerByTraineruserid() {
		return trainerByTraineruserid;
	}

	public void setTrainerByTraineruserid(TrainerEntity trainerByTraineruserid) {
		this.trainerByTraineruserid = trainerByTraineruserid;
	}

	public UserEntity getUserByUserid() {
		return userByUserid;
	}

	public void setUserByUserid(UserEntity userByUserid) {
		this.userByUserid = userByUserid;
	}
}
