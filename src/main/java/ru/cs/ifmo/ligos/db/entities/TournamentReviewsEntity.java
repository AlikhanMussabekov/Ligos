package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tournament_reviews", schema = "public", catalog = "ligos")
public class TournamentReviewsEntity {
	@Id
	@Column(name = "tournamentid", nullable = false)
	private Integer tournamentid;

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
	@JoinColumn(name = "tournamentid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private TournamentEntity tournamentByTournamentid;

	public Integer getTournamentid() {
		return tournamentid;
	}

	public void setTournamentid(Integer tournamentid) {
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}


	public TournamentEntity getTournamentByTournamentid() {
		return tournamentByTournamentid;
	}

	public void setTournamentByTournamentid(TournamentEntity tournamentByTournamentid) {
		this.tournamentByTournamentid = tournamentByTournamentid;
	}
}
