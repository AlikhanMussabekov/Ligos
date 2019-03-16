package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "organization", schema = "public", catalog = "ligos")
public class OrganizationEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "organization_id_seq", sequenceName = "organization_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_id_seq")
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
	@JoinColumn(name = "legal_address", nullable = true)
	private AddressEntity legalAddress;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "actual_address", nullable = true)
	private AddressEntity actualAddress;
}
