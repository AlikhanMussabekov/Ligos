package ru.cs.ifmo.ligos.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "court_payment", schema = "public", catalog = "ligos")
public class CourtPaymentEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "court_payment_id_seq", sequenceName = "court_payment_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_payment_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "courtid", nullable = false)
	private CourtEntity court;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "hour_from", nullable = false)
	private Date from;

	@Column(name = "hour_to", nullable = false)
	private Date to;

	@Column(name = "status", nullable = false)
	private Boolean status;

	@Column(name = "date_at", nullable = false)
	private Date date;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usersid", nullable = false)
	private UsersEntity user;
}
