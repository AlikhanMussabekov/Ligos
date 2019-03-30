package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "goals_list", schema = "public", catalog = "ligos")
public class GoalsListEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "goals_list_id_seq", sequenceName = "goals_list_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goals_list_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "matchid", nullable = false)
	private MatchesEntity match;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity user;

	@Column(name = "time", nullable = false)
	private Date time;
}
