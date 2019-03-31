package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "team", schema = "public", catalog = "ligos")
public class TeamEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "team_id_seq", sequenceName = "team_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "trainerid", nullable = true)
	private TrainerEntity trainer;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "captainid", nullable = false)
	private UsersEntity captain;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Lob
	@Column(name = "photo", nullable = true)
	private byte[] photo;

	@Column(name = "wins")
	private Integer wins;

	@Column(name = "draws")
	private Integer draws;

	@Column(name = "defeats")
	private Integer defeats;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Set<TeamUserEntity> teamUserEntities;

}
