package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "tournament_details", schema = "public", catalog = "ligos")
public class TournamentDetailsEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "tournament_details_id_seq", sequenceName = "tournament_details_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournament_details_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "age_categoryid", nullable = false)
	private AgeCategoryEntity ageCategoryid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "tournamentid", nullable = false)
	private TournamentEntity tournamentid;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "max_teams_count", nullable = false)
	private Integer maxTeamsCount;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tournament_teams",
			joinColumns = @JoinColumn(name = "tournament_detailsid", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "teamid", referencedColumnName = "id"))
	private Set<TeamEntity> teams;
}
