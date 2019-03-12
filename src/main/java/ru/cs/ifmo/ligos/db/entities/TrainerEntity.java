package ru.cs.ifmo.ligos.db.entities;

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
	private Integer id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity userid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "organizationid", nullable = false)
	private OrganizationEntity organizationid;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@OneToMany(fetch = FetchType.LAZY ,mappedBy = "trainerid")
	private Set<TeamEntity> teams;
}
