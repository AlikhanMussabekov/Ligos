package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "organization", schema = "public", catalog = "ligos")
public class OrganizationEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "email", nullable = false, length = 254)
	private String email;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "name", nullable = true, length = 255)
	private String name;

	@Column(name = "photo", nullable = true)
	private byte[] photo;

	@Column(name = "type", nullable = true, length = 4)
	private String type;

	@Column(name = "iin", nullable = true, length = 10)
	private String iin;

	@Column(name = "kpp", nullable = true, length = 9)
	private String kpp;

	@Column(name = "ogrn", nullable = true, length = 13)
	private String ogrn;

	@Column(name = "ogrn_date", nullable = true)
	private Date ogrnDate;

	@Column(name = "ogrnip", nullable = true, length = 15)
	private String ogrnip;

	@Column(name = "ogrnip_date", nullable = true)
	private Date ogrnipDate;

	@Column(name = "bik", nullable = true, length = 9)
	private String bik;

	@Column(name = "bank_name", nullable = true, length = 255)
	private String bankName;

	@Column(name = "korr_bill", nullable = true, length = 20)
	private String korrBill;

	@Column(name = "payment_bill", nullable = true, length = 20)
	private String paymentBill;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "legal_address", nullable = false)
	private AddressEntity legalAddress;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "actual_address", nullable = false)
	private AddressEntity actualAddress;

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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
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

	public Date getOgrnDate() {
		return ogrnDate;
	}

	public void setOgrnDate(Date ogrnDate) {
		this.ogrnDate = ogrnDate;
	}

	public String getOgrnip() {
		return ogrnip;
	}

	public void setOgrnip(String ogrnip) {
		this.ogrnip = ogrnip;
	}

	public Date getOgrnipDate() {
		return ogrnipDate;
	}

	public void setOgrnipDate(Date ogrnipDate) {
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

	public AddressEntity getLegalAddress() {
		return legalAddress;
	}

	public void setLegalAddress(AddressEntity legalAddress) {
		this.legalAddress = legalAddress;
	}

	public AddressEntity getActualAddress() {
		return actualAddress;
	}

	public void setActualAddress(AddressEntity actualAddress) {
		this.actualAddress = actualAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrganizationEntity that = (OrganizationEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(email, that.email) &&
				Objects.equals(password, that.password) &&
				Objects.equals(name, that.name) &&
				Arrays.equals(photo, that.photo) &&
				Objects.equals(type, that.type) &&
				Objects.equals(iin, that.iin) &&
				Objects.equals(kpp, that.kpp) &&
				Objects.equals(ogrn, that.ogrn) &&
				Objects.equals(ogrnDate, that.ogrnDate) &&
				Objects.equals(ogrnip, that.ogrnip) &&
				Objects.equals(ogrnipDate, that.ogrnipDate) &&
				Objects.equals(bik, that.bik) &&
				Objects.equals(bankName, that.bankName) &&
				Objects.equals(korrBill, that.korrBill) &&
				Objects.equals(paymentBill, that.paymentBill) &&
				Objects.equals(legalAddress, that.legalAddress) &&
				Objects.equals(actualAddress, that.actualAddress);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(id, email, password, name, type, iin, kpp, ogrn, ogrnDate, ogrnip, ogrnipDate, bik, bankName, korrBill, paymentBill, legalAddress, actualAddress);
		result = 31 * result + Arrays.hashCode(photo);
		return result;
	}
}
