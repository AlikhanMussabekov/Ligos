package ru.cs.ifmo.ligos.db.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "court", schema = "public", catalog = "ligos")
public class CourtEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "court_id_seq", sequenceName = "court_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "organizationid", nullable = false)
	private OrganizationEntity organization;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "description", nullable = false, length = -1)
	private String description;

	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "photos", nullable = false)
	private byte[] photos;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "addressid", nullable = false)
	private AddressEntity address;

	@Column(name = "raiting", nullable = true)
	private Short raiting;
}
