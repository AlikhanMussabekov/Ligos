package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "trainer_reviews", schema = "public", catalog = "ligos")
public class TrainerReviewsEntity  implements Serializable {

	@Id
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "traineruserid", nullable = false)
	private TrainerEntity traineruserid;

	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity userid;

	@Column(name = "review", nullable = false, length = -1)
	private String review;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@Column(name = "DATE", nullable = false)
	private Date date;

	public TrainerEntity getTraineruserid() {
		return traineruserid;
	}

	public void setTraineruserid(TrainerEntity traineruserid) {
		this.traineruserid = traineruserid;
	}

	public UsersEntity getUserid() {
		return userid;
	}

	public void setUserid(UsersEntity userid) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TrainerReviewsEntity that = (TrainerReviewsEntity) o;
		return Objects.equals(traineruserid, that.traineruserid) &&
				Objects.equals(userid, that.userid) &&
				Objects.equals(review, that.review) &&
				Objects.equals(raiting, that.raiting) &&
				Objects.equals(date, that.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(traineruserid, userid, review, raiting, date);
	}
}
