package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "court_payment", schema = "public", catalog = "ligos")
public class CourtPaymentEntity {

	@Id
	@Column(name = "courtid", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer courtid;

	@Basic
	@Column(name = "price", nullable = false)
	private Integer price;

	@Basic
	@Column(name = "FROM", nullable = false)
	private Timestamp from;

	@Basic
	@Column(name = "TO", nullable = false)
	private Timestamp to;

	@Basic
	@Column(name = "status", nullable = false)
	private Boolean status;

	@OneToOne
	@JoinColumn(name = "courtid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private CourtEntity courtByCourtid;

	public Integer getCourtid() {
		return courtid;
	}
	public void setCourtid(Integer courtid) {
		this.courtid = courtid;
	}

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public Timestamp getFrom() {
		return from;
	}
	public void setFrom(Timestamp from) {
		this.from = from;
	}

	public Timestamp getTo() {
		return to;
	}
	public void setTo(Timestamp to) {
		this.to = to;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}


	public CourtEntity getCourtByCourtid() {
		return courtByCourtid;
	}
	public void setCourtByCourtid(CourtEntity courtByCourtid) {
		this.courtByCourtid = courtByCourtid;
	}
}
