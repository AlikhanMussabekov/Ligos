package ru.cs.ifmo.ligos.db.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "user_match", schema = "public", catalog = "ligos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMatchEntity implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "matchesid")
	private MatchesEntity match;

	@Id
	@ManyToOne
	@JoinColumn(name = "usersid")
	private UsersEntity user;

	@Column(name = "assists", nullable = false)
	private Integer assists;

	@Column(name = "shots", nullable = false)
	private Integer shots;

	@Column(name = "mins_played", nullable = false)
	private Integer mins_played;

	@Column(name = "yellow_card", nullable = false)
	private Integer yellow_card;

	@Column(name = "red_card", nullable = false)
	private Integer red_card;

	@Column(name = "saved", nullable = false)
	private Integer saved;
}
