package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "court", schema = "public", catalog = "ligos")
public class CourtEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "organizationid", nullable = false)
	private OrganizationEntity organizationid;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "description", nullable = false, length = -1)
	private String description;

	@Column(name = "photos", nullable = false)
	private byte[] photos;

	@Column(name = "addressid", nullable = false)
	private Integer addressid;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrganizationEntity getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(OrganizationEntity organizationid) {
		this.organizationid = organizationid;
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

	public byte[] getPhotos() {
		return photos;
	}

	public void setPhotos(byte[] photos) {
		this.photos = photos;
	}

	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public Short getRaiting() {
		return raiting;
	}

	public void setRaiting(Short raiting) {
		this.raiting = raiting;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CourtEntity that = (CourtEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(organizationid, that.organizationid) &&
				Objects.equals(name, that.name) &&
				Objects.equals(description, that.description) &&
				Arrays.equals(photos, that.photos) &&
				Objects.equals(addressid, that.addressid) &&
				Objects.equals(raiting, that.raiting);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(id, organizationid, name, description, addressid, raiting);
		result = 31 * result + Arrays.hashCode(photos);
		return result;
	}
}
