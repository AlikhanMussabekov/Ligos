package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "address", schema = "public", catalog = "ligos")
public class AddressEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AddressEntity that = (AddressEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(country, that.country) &&
				Objects.equals(state, that.state) &&
				Objects.equals(zipCode, that.zipCode) &&
				Objects.equals(city, that.city) &&
				Objects.equals(street, that.street);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, country, state, zipCode, city, street);
	}
}
