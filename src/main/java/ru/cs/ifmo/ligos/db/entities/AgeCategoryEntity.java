package ru.cs.ifmo.ligos.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "age_category", schema = "public", catalog = "ligos")
public class AgeCategoryEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "age_category_id_seq", sequenceName = "age_category_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "age_category_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "FROM", nullable = false)
	private Integer from;

	@Column(name = "TO", nullable = false)
	private Integer to;

}
