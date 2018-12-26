package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "trainer_reviews", schema = "public", catalog = "ligos")
public class TrainerReviewsEntity implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "traineruserid", referencedColumnName = "userid")
	private TrainerEntity trainerByTraineruserid;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
	private UserEntity userByUserid;


	@Column(name = "review", nullable = false, length = -1)
	private String review;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@Column(name = "date", nullable = false)
	private Timestamp date;

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
