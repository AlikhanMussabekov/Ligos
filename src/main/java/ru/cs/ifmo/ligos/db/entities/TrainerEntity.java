package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "trainer", schema = "public", catalog = "ligos")
public class TrainerEntity implements Serializable {
	@Id
	@OneToOne
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private UserEntity userByUserid;

	@ManyToOne
	@JoinColumn(name = "organizationid", referencedColumnName = "id", nullable = false)
	private OrganizationEntity organizationByOrganizationid;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@OneToMany(mappedBy = "trainerByTrainerid")
	private Collection<SectionDetailsEntity> sectionDetailsByUserid;

	@OneToMany(mappedBy = "trainerByTrainerid")
	private Collection<TeamEntity> teamsByUserid;

	//todo
	@OneToMany(mappedBy = "trainerByTraineruserid")
	private Set<TrainerReviewsEntity> trainerReviews;

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

	public Set<TrainerReviewsEntity> getTrainerReviews() {
		return trainerReviews;
	}

	public void setTrainerReviews(Set<TrainerReviewsEntity> trainerReviews) {
		this.trainerReviews = trainerReviews;
	}
}
