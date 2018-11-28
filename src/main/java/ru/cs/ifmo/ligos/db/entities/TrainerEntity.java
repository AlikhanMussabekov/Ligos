package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "trainer", schema = "public", catalog = "ligos")
public class TrainerEntity {
	@Id
	@Column(name = "userid", nullable = false)
	private Integer userid;

	@Basic
	@Column(name = "organizationid", nullable = false)
	private Integer organizationid;

	@Basic
	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@OneToMany(mappedBy = "trainerByTrainerid")
	private Collection<SectionDetailsEntity> sectionDetailsByUserid;

	@OneToMany(mappedBy = "trainerByTrainerid")
	private Collection<TeamEntity> teamsByUserid;

	@OneToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private UserEntity userByUserid;

	@ManyToOne
	@JoinColumn(name = "organizationid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private OrganizationEntity organizationByOrganizationid;

	@OneToOne(mappedBy = "trainerByTraineruserid")
	private TrainerReviewsEntity trainerReviewsByUserid;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Integer organizationid) {
		this.organizationid = organizationid;
	}

	public Short getRaiting() {
		return raiting;
	}

	public void setRaiting(Short raiting) {
		this.raiting = raiting;
	}


	public Collection<SectionDetailsEntity> getSectionDetailsByUserid() {
		return sectionDetailsByUserid;
	}

	public void setSectionDetailsByUserid(Collection<SectionDetailsEntity> sectionDetailsByUserid) {
		this.sectionDetailsByUserid = sectionDetailsByUserid;
	}

	public Collection<TeamEntity> getTeamsByUserid() {
		return teamsByUserid;
	}

	public void setTeamsByUserid(Collection<TeamEntity> teamsByUserid) {
		this.teamsByUserid = teamsByUserid;
	}

	public UserEntity getUserByUserid() {
		return userByUserid;
	}

	public void setUserByUserid(UserEntity userByUserid) {
		this.userByUserid = userByUserid;
	}

	public OrganizationEntity getOrganizationByOrganizationid() {
		return organizationByOrganizationid;
	}

	public void setOrganizationByOrganizationid(OrganizationEntity organizationByOrganizationid) {
		this.organizationByOrganizationid = organizationByOrganizationid;
	}

	public TrainerReviewsEntity getTrainerReviewsByUserid() {
		return trainerReviewsByUserid;
	}

	public void setTrainerReviewsByUserid(TrainerReviewsEntity trainerReviewsByUserid) {
		this.trainerReviewsByUserid = trainerReviewsByUserid;
	}
}
