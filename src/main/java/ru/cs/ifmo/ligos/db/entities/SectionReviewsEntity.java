package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "section_reviews", schema = "public", catalog = "ligos")
public class SectionReviewsEntity {

	@Id
	@Column(name = "userid", nullable = false)
	private Integer userid;

	@Basic
	@Column(name = "sectionid", nullable = false)
	private Integer sectionid;

	@Basic
	@Column(name = "review", nullable = false, length = -1)
	private String review;

	@Basic
	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@Basic
	@Column(name = "DATE", nullable = false)
	private Timestamp date;

	@OneToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private UserEntity userByUserid;

	@ManyToOne
	@JoinColumn(name = "sectionid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private SectionEntity sectionBySectionid;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getSectionid() {
		return sectionid;
	}

	public void setSectionid(Integer sectionid) {
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
