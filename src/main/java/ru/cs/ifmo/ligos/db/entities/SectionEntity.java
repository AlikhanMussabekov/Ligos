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
@Table(name = "section", schema = "public", catalog = "ligos")
public class SectionEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "section_id_seq", sequenceName = "section_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section_id_seq")
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

	@Column(name = "raiting")
	private Short raiting;
}
