package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "section_users", schema = "public", catalog = "ligos")
public class SectionUsersEntity implements Serializable {
	@Id
	@OneToOne
	@JoinColumn(name = "section_detailsid", referencedColumnName = "id", nullable = false)
	private SectionDetailsEntity sectionDetailsBySectionDetailsid;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
	private UserEntity userByUserid;

	@Column(name = "present", nullable = false)
	private Boolean present;

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
