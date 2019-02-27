package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "court", schema = "public", catalog = "ligos")
public class CourtEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "court_id_seq", sequenceName = "court_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "organizationid", nullable = false)
	private OrganizationEntity organizationid;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "description", nullable = false, length = -1)
	private String description;

	@Lob
	@Column(name = "photos", nullable = false)
	private byte[] photos;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "addressid", nullable = false)
	private AddressEntity addressid;

	@Column(name = "raiting", nullable = false)
	private Short raiting;
}
