package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "section_users", schema = "public", catalog = "ligos")
public class SectionUsersEntity  implements Serializable {

	@Id
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "section_detailsid", nullable = false)
	private SectionDetailsEntity sectionDetailsid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity userid;

	@Column(name = "present", nullable = false)
	private Boolean present;

	public SectionDetailsEntity getSectionDetailsid() {
		return sectionDetailsid;
	}

	public void setSectionDetailsid(SectionDetailsEntity sectionDetailsid) {
		this.sectionDetailsid = sectionDetailsid;
	}

	public UsersEntity getUserid() {
		return userid;
	}

	public void setUserid(UsersEntity userid) {
		this.userid = userid;
	}

	public Boolean getPresent() {
		return present;
	}

	public void setPresent(Boolean present) {
		this.present = present;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SectionUsersEntity that = (SectionUsersEntity) o;
		return Objects.equals(sectionDetailsid, that.sectionDetailsid) &&
				Objects.equals(userid, that.userid) &&
				Objects.equals(present, that.present);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sectionDetailsid, userid, present);
	}
}
