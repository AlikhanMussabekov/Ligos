package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "organization", schema = "public", catalog = "ligos")
public class OrganizationEntity implements Serializable {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "email", unique = true, nullable = false, length = 254)
	private String email;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "name", nullable = true, length = 255)
	private String name;

	@Column(name = "photo", nullable = true, length = -1)
	private String photo;

	@Column(name = "type", nullable = true, length = 4)
	private String type;

	@Column(name = "iin",unique = true,nullable = true, length = 10)
	private String iin;

	@Column(name = "kpp",unique = true, nullable = true, length = 9)
	private String kpp;

	@Column(name = "ogrn", unique = true,nullable = true, length = 13)
	private String ogrn;

	@Column(name = "ogrn_date", nullable = true)
	private Timestamp ogrnDate;

	@Column(name = "ogrnip",unique = true, nullable = true, length = 15)
	private String ogrnip;

	@Column(name = "ogrnip_date", nullable = true)
	private Timestamp ogrnipDate;

	@Column(name = "bik", nullable = true, length = 9)
	private String bik;

	@Column(name = "bank_name", nullable = true, length = 255)
	private String bankName;

	@Column(name = "korr_bill", nullable = true, length = 20)
	private String korrBill;

	@Column(name = "payment_bill", nullable = true, length = 20)
	private String paymentBill;

	@ManyToOne
	@JoinColumn(name = "legal_address", referencedColumnName = "id", nullable = false)
	private AddressEntity addressByLegalAddress;

	@ManyToOne
	@JoinColumn(name = "actual_address", referencedColumnName = "id", nullable = false)
	private AddressEntity addressByActualAddress;

	@OneToMany(mappedBy = "organizationByOrganizationid")
	private Collection<CourtEntity> courtsById;

	@OneToMany(mappedBy = "organizationByOrganizationid")
	private Collection<SectionEntity> sectionsById;

	@OneToMany(mappedBy = "organizationByOrganizationid")
	private Collection<TrainerEntity> trainersById;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIin() {
		return iin;
	}

	public void setIin(String iin) {
		this.iin = iin;
	}

	public String getKpp() {
		return kpp;
	}

	public void setKpp(String kpp) {
		this.kpp = kpp;
	}

	public String getOgrn() {
		return ogrn;
	}

	public void setOgrn(String ogrn) {
		this.ogrn = ogrn;
	}

	public Timestamp getOgrnDate() {
		return ogrnDate;
	}

	public void setOgrnDate(Timestamp ogrnDate) {
		this.ogrnDate = ogrnDate;
	}

	public String getOgrnip() {
		return ogrnip;
	}

	public void setOgrnip(String ogrnip) {
		this.ogrnip = ogrnip;
	}

	public Timestamp getOgrnipDate() {
		return ogrnipDate;
	}

	public void setOgrnipDate(Timestamp ogrnipDate) {
		this.ogrnipDate = ogrnipDate;
	}

	public String getBik() {
		return bik;
	}

	public void setBik(String bik) {
		this.bik = bik;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getKorrBill() {
		return korrBill;
	}

	public void setKorrBill(String korrBill) {
		this.korrBill = korrBill;
	}

	public String getPaymentBill() {
		return paymentBill;
	}

	public void setPaymentBill(String paymentBill) {
		this.paymentBill = paymentBill;
	}

	public Collection<CourtEntity> getCourtsById() {
		return courtsById;
	}

	public void setCourtsById(Collection<CourtEntity> courtsById) {
		this.courtsById = courtsById;
	}

	public AddressEntity getAddressByLegalAddress() {
		return addressByLegalAddress;
	}

	public void setAddressByLegalAddress(AddressEntity addressByLegalAddress) {
		this.addressByLegalAddress = addressByLegalAddress;
	}

	public AddressEntity getAddressByActualAddress() {
		return addressByActualAddress;
	}

	public void setAddressByActualAddress(AddressEntity addressByActualAddress) {
		this.addressByActualAddress = addressByActualAddress;
	}

	public Collection<SectionEntity> getSectionsById() {
		return sectionsById;
	}

	public void setSectionsById(Collection<SectionEntity> sectionsById) {
		this.sectionsById = sectionsById;
	}

	public Collection<TrainerEntity> getTrainersById() {
		return trainersById;
	}

	public void setTrainersById(Collection<TrainerEntity> trainersById) {
		this.trainersById = trainersById;
	}
}
