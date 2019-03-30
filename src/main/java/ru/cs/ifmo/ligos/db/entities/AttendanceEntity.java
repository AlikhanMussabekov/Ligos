package ru.cs.ifmo.ligos.db.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "attendance", schema = "public", catalog = "ligos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceEntity implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "section_detailsid")
	private SectionDetailsEntity sectionDetails;

	@Id
	@ManyToOne
	@JoinColumn(name = "usersid")
	private UsersEntity user;

	@Column(name = "PRESENT", nullable = true)
	private Boolean present;

	@Column(name = "DATE", nullable = true)
	private Date date;

}
