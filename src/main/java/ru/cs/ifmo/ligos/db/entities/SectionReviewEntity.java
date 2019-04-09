package ru.cs.ifmo.ligos.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "section_review", schema = "public", catalog = "ligos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionReviewEntity extends ReviewEntity {

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "section_id", nullable = false)
	private SectionEntity section;

}
