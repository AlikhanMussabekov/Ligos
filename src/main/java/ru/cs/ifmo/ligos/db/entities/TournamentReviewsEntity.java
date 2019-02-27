package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tournament_reviews", schema = "public", catalog = "ligos")
public class TournamentReviewsEntity implements Serializable {

	@Id
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tournamentid", nullable = false)
	private TournamentEntity tournamentid;

	@Column(name = "review", nullable = false, length = -1)
	private String review;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@Column(name = "DATE", nullable = false)
	private Date date;

	public TournamentEntity getTournamentid() {
		return tournamentid;
	}

	public void setTournamentid(TournamentEntity tournamentid) {
		this.tournamentid = tournamentid;
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
		TournamentReviewsEntity that = (TournamentReviewsEntity) o;
		return Objects.equals(tournamentid, that.tournamentid) &&
				Objects.equals(review, that.review) &&
				Objects.equals(raiting, that.raiting) &&
				Objects.equals(date, that.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tournamentid, review, raiting, date);
	}
}
