package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "trainer", schema = "public", catalog = "ligos")
public class TrainerEntity implements Serializable {

	@Id
	private Integer id;

	@MapsId
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity userid;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "organizationid", nullable = false)
	private OrganizationEntity organizationid;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@OneToMany(fetch = FetchType.LAZY ,mappedBy = "trainerid")
	private Set<TeamEntity> teams;

	public UsersEntity getUserid() {
		return userid;
	}

	public void setUserid(UsersEntity userid) {
		this.userid = userid;
	}

	public OrganizationEntity getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(OrganizationEntity organizationid) {
		this.organizationid = organizationid;
	}

	public Short getRaiting() {
		return raiting;
	}

	public void setRaiting(Short raiting) {
		this.raiting = raiting;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TrainerEntity that = (TrainerEntity) o;
		return Objects.equals(userid, that.userid) &&
				Objects.equals(organizationid, that.organizationid) &&
				Objects.equals(raiting, that.raiting);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userid, organizationid, raiting);
	}
}
