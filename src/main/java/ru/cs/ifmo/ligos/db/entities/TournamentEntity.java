package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "tournament", schema = "public", catalog = "ligos")
public class TournamentEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "tournament_id_seq", sequenceName = "tournament_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournament_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "description", nullable = false, length = -1)
	private String description;

	@Column(name = "DATE", nullable = false)
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "addressid", nullable = false)
	private AddressEntity addressid;

	@Column(name = "raiting")
	private Short raiting;
}
