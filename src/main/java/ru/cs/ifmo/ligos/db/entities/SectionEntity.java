package ru.cs.ifmo.ligos.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "section", schema = "public", catalog = "ligos")
public class SectionEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "section_id_seq", sequenceName = "section_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationid", nullable = false)
	private OrganizationEntity organization;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "description", nullable = false, length = -1)
	private String description;

	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "photo", nullable = false)
	private byte[] photo;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "addressid", nullable = false)
	private AddressEntity address;

	@Column(name = "raiting")
	private Short raiting;
}
