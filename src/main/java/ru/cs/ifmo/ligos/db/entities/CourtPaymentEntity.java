package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "court_payment", schema = "public", catalog = "ligos")
public class CourtPaymentEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@OneToOne
	@JoinColumn(name = "courtid", referencedColumnName = "id")
	private CourtEntity courtByCourtid;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "FROM", nullable = false)
	//Change to java.util.date
	private Timestamp from;

	@Column(name = "TO", nullable = false)
	private Timestamp to;

	@Column(name = "status", nullable = false)
	private Boolean status;


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
