package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "team", schema = "public", catalog = "ligos")
public class TeamEntity implements Serializable {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "photo", nullable = true, length = -1)
	private String photo;

	@OneToMany(mappedBy = "teamByTeamFirst")
	private Collection<MatchesEntity> matchesById;

	@OneToMany(mappedBy = "teamByTeamSecond")
	private Collection<MatchesEntity> matchesById_0;

	@ManyToOne
	@JoinColumn(name = "trainerid", referencedColumnName = "userid")
	private TrainerEntity trainerByTrainerid;

	@ManyToOne
	@JoinColumn(name = "captainid", referencedColumnName = "id", nullable = false)
	private UserEntity userByCaptainid;

/*
	@OneToOne(mappedBy = "teamByTeamid")
	private TeamUserEntity teamUserById;
*/

	@ManyToMany(mappedBy = "teams")
	private Set<UserEntity> users;

	/*@OneToMany(mappedBy = "teamByTeamid")
	private Collection<TournamentTeamsEntity> tournamentTeamsById;
*/

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "tournament_teams",
			joinColumns = { @JoinColumn(name = "teamid")},
			inverseJoinColumns = { @JoinColumn(name = "tournament_detailsid")})
	private Set<TournamentDetailsEntity> tournamentDetails;



}
