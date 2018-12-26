package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "section_details", schema = "public", catalog = "ligos")
public class SectionDetailsEntity implements Serializable {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "age_categoryid", referencedColumnName = "id", nullable = false)
	private AgeCategoryEntity ageCategoryByAgeCategoryid;

	@ManyToOne
	@JoinColumn(name = "sectionid", referencedColumnName = "id", nullable = false)
	private SectionEntity sectionBySectionid;

	@ManyToOne
	@JoinColumn(name = "trainerid", referencedColumnName = "userid", nullable = false)
	private TrainerEntity trainerByTrainerid;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "time_start", nullable = false)
	private Timestamp timeStart;

	@Column(name = "time_end", nullable = false)
	private Timestamp timeEnd;

	@Column(name = "max_users_count", nullable = false)
	private Integer maxUsersCount;

	@OneToOne(mappedBy = "sectionDetailsBySectionDetailsid")
	private SectionUsersEntity sectionUsersById;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
