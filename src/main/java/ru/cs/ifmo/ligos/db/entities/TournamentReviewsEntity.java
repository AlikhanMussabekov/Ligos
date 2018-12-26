package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tournament_reviews", schema = "public", catalog = "ligos")
public class TournamentReviewsEntity implements Serializable {
	@Id
	@OneToOne
	@JoinColumn(name = "tournamentid", referencedColumnName = "id")
	private TournamentEntity tournamentByTournamentid;

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


	public TournamentEntity getTournamentByTournamentid() {
		return tournamentByTournamentid;
	}

	public void setTournamentByTournamentid(TournamentEntity tournamentByTournamentid) {
		this.tournamentByTournamentid = tournamentByTournamentid;
	}
}
