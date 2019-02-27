package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "section_details", schema = "public", catalog = "ligos")
public class SectionDetailsEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "section_details_id_seq", sequenceName = "section_details_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section_details_id_seq")
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
}
