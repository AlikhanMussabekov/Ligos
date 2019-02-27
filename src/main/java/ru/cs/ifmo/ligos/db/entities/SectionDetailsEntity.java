package ru.cs.ifmo.ligos.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "section_details", schema = "public", catalog = "ligos")
public class SectionDetailsEntity implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "age_categoryid", nullable = false)
	private AgeCategoryEntity ageCategoryid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sectionid", nullable = false)
	private SectionEntity sectionid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "trainerid", nullable = false)
	private UsersEntity trainerid;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "time_start", nullable = false)
	private Date timeStart;

	@Column(name = "time_end", nullable = false)
	private Date timeEnd;

	@Column(name = "max_users_count", nullable = false)
	private Integer maxUsersCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AgeCategoryEntity getAgeCategoryid() {
		return ageCategoryid;
	}

	public void setAgeCategoryid(AgeCategoryEntity ageCategoryid) {
		this.ageCategoryid = ageCategoryid;
	}

	public SectionEntity getSectionid() {
		return sectionid;
	}

	public void setSectionid(SectionEntity sectionid) {
		this.sectionid = sectionid;
	}

	public UsersEntity getTrainerid() {
		return trainerid;
	}

	public void setTrainerid(UsersEntity trainerid) {
		this.trainerid = trainerid;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Integer getMaxUsersCount() {
		return maxUsersCount;
	}

	public void setMaxUsersCount(Integer maxUsersCount) {
		this.maxUsersCount = maxUsersCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SectionDetailsEntity that = (SectionDetailsEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(ageCategoryid, that.ageCategoryid) &&
				Objects.equals(sectionid, that.sectionid) &&
				Objects.equals(trainerid, that.trainerid) &&
				Objects.equals(price, that.price) &&
				Objects.equals(timeStart, that.timeStart) &&
				Objects.equals(timeEnd, that.timeEnd) &&
				Objects.equals(maxUsersCount, that.maxUsersCount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ageCategoryid, sectionid, trainerid, price, timeStart, timeEnd, maxUsersCount);
	}
}
