package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "section_reviews", schema = "public", catalog = "ligos")
public class SectionReviewsEntity implements Serializable {

	@Id
	@OneToOne
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private UserEntity userByUserid;

	@ManyToOne
	@JoinColumn(name = "sectionid", referencedColumnName = "id", nullable = false)
	private SectionEntity sectionBySectionid;

	@Column(name = "review", nullable = false, length = -1)
	private String review;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@Column(name = "date", nullable = false)
	private Timestamp date;

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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public UserEntity getUserByUserid() {
		return userByUserid;
	}

	public void setUserByUserid(UserEntity userByUserid) {
		this.userByUserid = userByUserid;
	}

	public SectionEntity getSectionBySectionid() {
		return sectionBySectionid;
	}

	public void setSectionBySectionid(SectionEntity sectionBySectionid) {
		this.sectionBySectionid = sectionBySectionid;
	}
}
