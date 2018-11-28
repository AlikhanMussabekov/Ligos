package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "section", schema = "public", catalog = "ligos")
public class SectionEntity {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Basic
	@Column(name = "organizationid", nullable = false)

	private Integer organizationid;
	@Basic
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Basic
	@Column(name = "description", nullable = false, length = -1)
	private String description;

	@Basic
	@Column(name = "photo", nullable = false, length = -1)
	private String photo;

	@Basic
	@Column(name = "addressid", nullable = false)
	private Integer addressid;

	@ManyToOne
	@JoinColumn(name = "organizationid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private OrganizationEntity organizationByOrganizationid;

	@ManyToOne
	@JoinColumn(name = "addressid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private AddressEntity addressByAddressid;

	@OneToMany(mappedBy = "sectionBySectionid")
	private Collection<SectionDetailsEntity> sectionDetailsById;

	@OneToMany(mappedBy = "sectionBySectionid")
	private Collection<SectionReviewsEntity> sectionReviewsById;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Integer organizationid) {
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}


	public OrganizationEntity getOrganizationByOrganizationid() {
		return organizationByOrganizationid;
	}

	public void setOrganizationByOrganizationid(OrganizationEntity organizationByOrganizationid) {
		this.organizationByOrganizationid = organizationByOrganizationid;
	}

	public AddressEntity getAddressByAddressid() {
		return addressByAddressid;
	}

	public void setAddressByAddressid(AddressEntity addressByAddressid) {
		this.addressByAddressid = addressByAddressid;
	}

	public Collection<SectionDetailsEntity> getSectionDetailsById() {
		return sectionDetailsById;
	}

	public void setSectionDetailsById(Collection<SectionDetailsEntity> sectionDetailsById) {
		this.sectionDetailsById = sectionDetailsById;
	}

	public Collection<SectionReviewsEntity> getSectionReviewsById() {
		return sectionReviewsById;
	}

	public void setSectionReviewsById(Collection<SectionReviewsEntity> sectionReviewsById) {
		this.sectionReviewsById = sectionReviewsById;
	}
}
