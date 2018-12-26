package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "address", schema = "public", catalog = "ligos")
public class AddressEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "country", nullable = false, length = 255)
	private String country;

	@Column(name = "state", nullable = false, length = 255)
	private String state;

	@Column(name = "zip_code", nullable = false, length = 255)
	private String zipCode;

	@Column(name = "city", nullable = false, length = 255)
	private String city;

	@Column(name = "street", nullable = false, length = 255)
	private String street;

	@OneToMany(mappedBy = "addressByAddressid")
	private Collection<CourtEntity> courtsById;

	@OneToMany(mappedBy = "addressByLegalAddress")
	private Collection<OrganizationEntity> organizationsById;

	@OneToMany(mappedBy = "addressByActualAddress")
	private Collection<OrganizationEntity> organizationsById_0;

	@OneToMany(mappedBy = "addressByAddressid")
	private Collection<SectionEntity> sectionsById;

	@OneToMany(mappedBy = "addressByAddressid")
	private Collection<TournamentEntity> tournamentsById;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Collection<CourtEntity> getCourtsById() {
		return courtsById;
	}

	public void setCourtsById(Collection<CourtEntity> courtsById) {
		this.courtsById = courtsById;
	}


	public Collection<OrganizationEntity> getOrganizationsById() {
		return organizationsById;
	}

	public void setOrganizationsById(Collection<OrganizationEntity> organizationsById) {
		this.organizationsById = organizationsById;
	}


	public Collection<OrganizationEntity> getOrganizationsById_0() {
		return organizationsById_0;
	}

	public void setOrganizationsById_0(Collection<OrganizationEntity> organizationsById_0) {
		this.organizationsById_0 = organizationsById_0;
	}


	public Collection<SectionEntity> getSectionsById() {
		return sectionsById;
	}

	public void setSectionsById(Collection<SectionEntity> sectionsById) {
		this.sectionsById = sectionsById;
	}


	public Collection<TournamentEntity> getTournamentsById() {
		return tournamentsById;
	}

	public void setTournamentsById(Collection<TournamentEntity> tournamentsById) {
		this.tournamentsById = tournamentsById;
	}
}
