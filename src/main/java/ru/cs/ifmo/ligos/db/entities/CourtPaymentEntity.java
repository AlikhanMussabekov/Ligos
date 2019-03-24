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
@Table(name = "court_payment", schema = "public", catalog = "ligos")
public class CourtPaymentEntity implements Serializable {

	@Id
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "courtid", nullable = false)
	private CourtEntity court;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "FROM", nullable = false)
	private Date from;

	@Column(name = "TO", nullable = false)
	private Date to;

	@Column(name = "status", nullable = false)
	private Boolean status;
}
