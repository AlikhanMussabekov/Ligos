package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "court_payment", schema = "public", catalog = "ligos")
public class CourtPaymentEntity implements Serializable {

	@Id
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "courtid", nullable = false)
	private CourtEntity courtid;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "FROM", nullable = false)
	private Date from;

	@Column(name = "TO", nullable = false)
	private Date to;

	@Column(name = "status", nullable = false)
	private Boolean status;

	public CourtEntity getCourtid() {
		return courtid;
	}

	public void setCourtid(CourtEntity courtid) {
		this.courtid = courtid;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CourtPaymentEntity that = (CourtPaymentEntity) o;
		return Objects.equals(courtid, that.courtid) &&
				Objects.equals(price, that.price) &&
				Objects.equals(from, that.from) &&
				Objects.equals(to, that.to) &&
				Objects.equals(status, that.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courtid, price, from, to, status);
	}
}
