package ru.cs.ifmo.ligos.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "trainer", schema = "public", catalog = "ligos")
public class TrainerEntity implements Serializable {

	@Id
	private Long id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity user;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "organizationid", nullable = false)
	private OrganizationEntity organization;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@OneToMany(fetch = FetchType.LAZY ,mappedBy = "trainer")
	private Set<TeamEntity> teams;
}
