package ru.cs.ifmo.ligos.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "organization", schema = "public", catalog = "ligos")
public class OrganizationEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "organization_id_seq", sequenceName = "organization_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "email", nullable = false, length = 254)
	private String email;

	@JsonIgnore
	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "name", nullable = true, length = 255)
	private String name;

	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "photo", nullable = true)
	private byte[] photo;

	@JsonIgnore
	@Column(name = "type", nullable = true, length = 4)
	private String type;

	@JsonIgnore
	@Column(name = "iin", nullable = true, length = 10)
	private String iin;

	@JsonIgnore
	@Column(name = "kpp", nullable = true, length = 9)
	private String kpp;

	@JsonIgnore
	@Column(name = "ogrn", nullable = true, length = 13)
	private String ogrn;

	@JsonIgnore
	@Column(name = "ogrn_date", nullable = true)
	private Date ogrnDate;

	@JsonIgnore
	@Column(name = "ogrnip", nullable = true, length = 15)
	private String ogrnip;

	@JsonIgnore
	@Column(name = "ogrnip_date", nullable = true)
	private Date ogrnipDate;

	@JsonIgnore
	@Column(name = "bik", nullable = true, length = 9)
	private String bik;

	@JsonIgnore
	@Column(name = "bank_name", nullable = true, length = 255)
	private String bankName;

	@JsonIgnore
	@Column(name = "korr_bill", nullable = true, length = 20)
	private String korrBill;

	@JsonIgnore
	@Column(name = "payment_bill", nullable = true, length = 20)
	private String paymentBill;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "legal_address", nullable = true)
	private AddressEntity legalAddress;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "actual_address", nullable = true)
	private AddressEntity actualAddress;

}
