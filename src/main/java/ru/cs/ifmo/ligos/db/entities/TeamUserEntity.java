package ru.cs.ifmo.ligos.db.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "team_user", schema = "public", catalog = "ligos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamUserEntity implements Serializable {

	@Id
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "teamid")
	private TeamEntity team;

	@Id
	@ManyToOne
	@JoinColumn(name = "userid")
	private UsersEntity user;

	@Enumerated(EnumType.STRING)
	@Column(name = "position", nullable = false)
	private Position position;
}
