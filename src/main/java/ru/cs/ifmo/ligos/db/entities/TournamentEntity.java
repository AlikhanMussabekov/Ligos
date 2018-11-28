package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "tournament", schema = "public", catalog = "ligos")
public class TournamentEntity {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Basic
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Basic
	@Column(name = "description", nullable = false, length = -1)
	private String description;

	@Basic
	@Column(name = "DATE", nullable = false)
	private Timestamp date;

	@Basic
	@Column(name = "addressid", nullable = false)
	private Integer addressid;

	@OneToMany(mappedBy = "tournamentByTournamentid")
	private Collection<MatchesEntity> matchesById;

	@ManyToOne
	@JoinColumn(name = "addressid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private AddressEntity addressByAddressid;

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

	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
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
