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
@Table(name = "section_users", schema = "public", catalog = "ligos")
public class SectionUsersEntity  implements Serializable {

	@Id
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "section_detailsid", nullable = false)
	private SectionDetailsEntity sectionDetails;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	private UsersEntity user;

	@Column(name = "present", nullable = false)
	private Boolean present;
}
