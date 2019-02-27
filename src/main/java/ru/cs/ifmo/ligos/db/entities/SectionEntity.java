package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "section", schema = "public", catalog = "ligos")
public class SectionEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "organizationid", nullable = false)
	private OrganizationEntity organizationid;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "description", nullable = false, length = -1)
	private String description;

	@Column(name = "photo", nullable = false)
	private byte[] photo;

	@Column(name = "addressid", nullable = false)
	private Integer addressid;

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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SectionEntity that = (SectionEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(organizationid, that.organizationid) &&
				Objects.equals(name, that.name) &&
				Objects.equals(description, that.description) &&
				Arrays.equals(photo, that.photo) &&
				Objects.equals(addressid, that.addressid);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(id, organizationid, name, description, addressid);
		result = 31 * result + Arrays.hashCode(photo);
		return result;
	}
}
