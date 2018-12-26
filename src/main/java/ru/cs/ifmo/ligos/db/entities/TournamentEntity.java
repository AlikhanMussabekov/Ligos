package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "tournament", schema = "public", catalog = "ligos")
public class TournamentEntity implements Serializable {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "DATE", nullable = false)
	private Timestamp date;

	@ManyToOne
	@JoinColumn(name = "addressid", referencedColumnName = "id", nullable = false)
	private AddressEntity addressByAddressid;

	@OneToMany(mappedBy = "tournamentByTournamentid")
	private Collection<MatchesEntity> matchesById;

	@OneToMany(mappedBy = "tournamentByTournamentid")
	private Collection<TournamentDetailsEntity> tournamentDetailsById;

	@OneToOne(mappedBy = "tournamentByTournamentid")
	private TournamentReviewsEntity tournamentReviewsById;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Collection<MatchesEntity> getMatchesById() {
		return matchesById;
	}

	public void setMatchesById(Collection<MatchesEntity> matchesById) {
		this.matchesById = matchesById;
	}

	public AddressEntity getAddressByAddressid() {
		return addressByAddressid;
	}

	public void setAddressByAddressid(AddressEntity addressByAddressid) {
		this.addressByAddressid = addressByAddressid;
	}

	public Collection<TournamentDetailsEntity> getTournamentDetailsById() {
		return tournamentDetailsById;
	}

	public void setTournamentDetailsById(Collection<TournamentDetailsEntity> tournamentDetailsById) {
		this.tournamentDetailsById = tournamentDetailsById;
	}

	public TournamentReviewsEntity getTournamentReviewsById() {
		return tournamentReviewsById;
	}

	public void setTournamentReviewsById(TournamentReviewsEntity tournamentReviewsById) {
		this.tournamentReviewsById = tournamentReviewsById;
	}
}
