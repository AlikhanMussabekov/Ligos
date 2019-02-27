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
@Table(name = "section_reviews", schema = "public", catalog = "ligos")
public class SectionReviewsEntity implements Serializable {

	@Id
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity userid;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sectionid", nullable = false)
	private SectionEntity sectionid;

	@Column(name = "review", nullable = false, length = -1)
	private String review;

	@Column(name = "raiting", nullable = false)
	private Short raiting;

	@Column(name = "DATE", nullable = false)
	private Date date;
}
