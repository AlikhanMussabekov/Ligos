package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "court_reviews", schema = "public", catalog = "ligos")
public class CourtReviewsEntity {
	@Id
	@Column(name = "courtid", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer courtid;

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
	@JoinColumn(name = "courtid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private CourtEntity courtByCourtid;

	public Integer getCourtid() {
		return courtid;
	}
	public void setCourtid(Integer courtid) {
		this.courtid = courtid;
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


	public CourtEntity getCourtByCourtid() {
		return courtByCourtid;
	}
	public void setCourtByCourtid(CourtEntity courtByCourtid) {
		this.courtByCourtid = courtByCourtid;
	}
}
