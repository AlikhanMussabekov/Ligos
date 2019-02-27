package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "section_reviews", schema = "public", catalog = "ligos")
public class SectionReviewsEntity implements Serializable {

	@Id
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity userid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sectionid", nullable = false)
	private SectionEntity sectionid;

	@Column(name = "review", nullable = false, length = -1)
	private String review;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@Column(name = "DATE", nullable = false)
	private Date date;

	public UsersEntity getUserid() {
		return userid;
	}

	public void setUserid(UsersEntity userid) {
		this.userid = userid;
	}

	public SectionEntity getSectionid() {
		return sectionid;
	}

	public void setSectionid(SectionEntity sectionid) {
		this.sectionid = sectionid;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Short getRaiting() {
		return raiting;
	}

	public void setRaiting(Short raiting) {
		this.raiting = raiting;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SectionReviewsEntity that = (SectionReviewsEntity) o;
		return Objects.equals(userid, that.userid) &&
				Objects.equals(sectionid, that.sectionid) &&
				Objects.equals(review, that.review) &&
				Objects.equals(raiting, that.raiting) &&
				Objects.equals(date, that.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userid, sectionid, review, raiting, date);
	}
}
