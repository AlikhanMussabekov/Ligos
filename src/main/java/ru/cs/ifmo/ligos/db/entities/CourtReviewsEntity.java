package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "court_reviews", schema = "public", catalog = "ligos")
public class CourtReviewsEntity implements Serializable {
	@Id
	@OneToOne
	@JoinColumn(name = "courtid", referencedColumnName = "id", nullable = false)
	private CourtEntity courtByCourtid;

	@Column(name = "review", nullable = false, length = -1)
	private String review;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@Column(name = "DATE", nullable = false)
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


	public CourtEntity getCourtByCourtid() {
		return courtByCourtid;
	}
	public void setCourtByCourtid(CourtEntity courtByCourtid) {
		this.courtByCourtid = courtByCourtid;
	}
}
