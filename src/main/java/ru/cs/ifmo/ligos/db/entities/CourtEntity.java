package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "court", schema = "public", catalog = "ligos")
public class CourtEntity {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@Column(name = "photos", nullable = false, length = -1)
	private String photos;

	@Basic
	@Column(name = "addressid", nullable = false)
	private Integer addressid;

	@Basic
	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@ManyToOne
	@JoinColumn(name = "organizationid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private OrganizationEntity organizationByOrganizationid;

	@ManyToOne
	@JoinColumn(name = "addressid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private AddressEntity addressByAddressid;

	@OneToOne(mappedBy = "courtByCourtid")
	private CourtPaymentEntity courtPaymentById;

	@OneToOne(mappedBy = "courtByCourtid")
	private CourtReviewsEntity courtReviewsById;

	@OneToMany(mappedBy = "courtByCourtid")
	private Collection<MatchesEntity> matchesById;

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

	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
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

	public CourtPaymentEntity getCourtPaymentById() {
		return courtPaymentById;
	}
	public void setCourtPaymentById(CourtPaymentEntity courtPaymentById) {
		this.courtPaymentById = courtPaymentById;
	}

	public CourtReviewsEntity getCourtReviewsById() {
		return courtReviewsById;
	}
	public void setCourtReviewsById(CourtReviewsEntity courtReviewsById) {
		this.courtReviewsById = courtReviewsById;
	}

	public Collection<MatchesEntity> getMatchesById() {
		return matchesById;
	}
	public void setMatchesById(Collection<MatchesEntity> matchesById) {
		this.matchesById = matchesById;
	}
}
