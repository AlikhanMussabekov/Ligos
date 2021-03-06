package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "matches", schema = "public", catalog = "ligos")
public class MatchesEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "matches_id_seq", sequenceName = "matches_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matches_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "team_first", nullable = false)
	private TeamEntity teamFirst;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "team_second", nullable = false)
	private TeamEntity teamSecond;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "courtid", nullable = false)
	private CourtEntity court;

	@Column(name = "DATE", nullable = false)
	private Date date;

	@Column(name = "goals_first", nullable = false)
	private Integer goalsFirst;

	@Column(name = "goals_second", nullable = false)
	private Integer goalsSecond;

	@OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
	private Set<UserMatchEntity> userMatchEntities;
}
