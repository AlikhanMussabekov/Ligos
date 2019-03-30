package ru.cs.ifmo.ligos.db.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "section_review", schema = "public", catalog = "ligos")
@Getter
@Setter
public class SectionReviewEntity extends ReviewEntity {

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "section_id", nullable = false)
	private SectionEntity section;

}
