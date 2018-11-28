package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "section_details", schema = "public", catalog = "ligos")
public class SectionDetailsEntity {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Basic
	@Column(name = "age_categoryid", nullable = false)
	private Integer ageCategoryid;

	@Basic
	@Column(name = "sectionid", nullable = false)
	private Integer sectionid;

	@Basic
	@Column(name = "trainerid", nullable = false)
	private Integer trainerid;

	@Basic
	@Column(name = "price", nullable = false)
	private Integer price;

	@Basic
	@Column(name = "time_start", nullable = false)
	private Timestamp timeStart;

	@Basic
	@Column(name = "time_end", nullable = false)
	private Timestamp timeEnd;

	@Basic
	@Column(name = "max_users_count", nullable = false)
	private Integer maxUsersCount;

	@ManyToOne
	@JoinColumn(name = "age_categoryid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private AgeCategoryEntity ageCategoryByAgeCategoryid;

	@ManyToOne
	@JoinColumn(name = "sectionid", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
	private SectionEntity sectionBySectionid;

	@ManyToOne
	@JoinColumn(name = "trainerid", referencedColumnName = "userid", nullable = false,insertable = false, updatable = false)
	private TrainerEntity trainerByTrainerid;

	@OneToOne(mappedBy = "sectionDetailsBySectionDetailsid")
	private SectionUsersEntity sectionUsersById;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAgeCategoryid() {
		return ageCategoryid;
	}

	public void setAgeCategoryid(Integer ageCategoryid) {
		this.ageCategoryid = ageCategoryid;
	}

	public Integer getSectionid() {
		return sectionid;
	}

	public void setSectionid(Integer sectionid) {
		this.sectionid = sectionid;
	}

	public Integer getTrainerid() {
		return trainerid;
	}

	public void setTrainerid(Integer trainerid) {
		this.trainerid = trainerid;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Timestamp getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Timestamp timeStart) {
		this.timeStart = timeStart;
	}

	public Timestamp getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Timestamp timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Integer getMaxUsersCount() {
		return maxUsersCount;
	}

	public void setMaxUsersCount(Integer maxUsersCount) {
		this.maxUsersCount = maxUsersCount;
	}



	public AgeCategoryEntity getAgeCategoryByAgeCategoryid() {
		return ageCategoryByAgeCategoryid;
	}

	public void setAgeCategoryByAgeCategoryid(AgeCategoryEntity ageCategoryByAgeCategoryid) {
		this.ageCategoryByAgeCategoryid = ageCategoryByAgeCategoryid;
	}

	public SectionEntity getSectionBySectionid() {
		return sectionBySectionid;
	}

	public void setSectionBySectionid(SectionEntity sectionBySectionid) {
		this.sectionBySectionid = sectionBySectionid;
	}

	public TrainerEntity getTrainerByTrainerid() {
		return trainerByTrainerid;
	}

	public void setTrainerByTrainerid(TrainerEntity trainerByTrainerid) {
		this.trainerByTrainerid = trainerByTrainerid;
	}

	public SectionUsersEntity getSectionUsersById() {
		return sectionUsersById;
	}

	public void setSectionUsersById(SectionUsersEntity sectionUsersById) {
		this.sectionUsersById = sectionUsersById;
	}
}
