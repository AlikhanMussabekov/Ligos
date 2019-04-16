package ru.cs.ifmo.ligos.db.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "court_review", schema = "public", catalog = "ligos")
@Getter
@Setter
public class CourtReviewEntity extends ReviewEntity {

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "court_id", nullable = false)
	private CourtEntity court;

}
