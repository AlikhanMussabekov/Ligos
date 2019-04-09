package ru.cs.ifmo.ligos.db.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review", schema = "public", catalog = "ligos")
@Inheritance(
		strategy = InheritanceType.JOINED
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {

	@Id
	@SequenceGenerator(name = "review_id_seq", sequenceName = "review_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_seq")
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity user;

	@Column(name = "review", nullable = false)
	private String review;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@Column(name = "date", nullable = false)
	private Date date;


}
