package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "section_users", schema = "public", catalog = "ligos")
public class SectionUsersEntity {
	@Id
	@Column(name = "section_detailsid", nullable = false)
	private Integer sectionDetailsid;

	@Basic
	@Column(name = "userid", nullable = false)
	private Integer userid;

	@Basic
	@Column(name = "present", nullable = false)
	private Boolean present;

	@OneToOne
	@JoinColumn(name = "section_detailsid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private SectionDetailsEntity sectionDetailsBySectionDetailsid;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private UserEntity userByUserid;

	public Integer getSectionDetailsid() {
		return sectionDetailsid;
	}

	public void setSectionDetailsid(Integer sectionDetailsid) {
		this.sectionDetailsid = sectionDetailsid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Boolean getPresent() {
		return present;
	}

	public void setPresent(Boolean present) {
		this.present = present;
	}


	public SectionDetailsEntity getSectionDetailsBySectionDetailsid() {
		return sectionDetailsBySectionDetailsid;
	}

	public void setSectionDetailsBySectionDetailsid(SectionDetailsEntity sectionDetailsBySectionDetailsid) {
		this.sectionDetailsBySectionDetailsid = sectionDetailsBySectionDetailsid;
	}

	public UserEntity getUserByUserid() {
		return userByUserid;
	}

	public void setUserByUserid(UserEntity userByUserid) {
		this.userByUserid = userByUserid;
	}
}
