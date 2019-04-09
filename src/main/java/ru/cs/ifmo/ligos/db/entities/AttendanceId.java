package ru.cs.ifmo.ligos.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceId implements Serializable {

	@ManyToOne(cascade = CascadeType.ALL)
	private SectionDetailsEntity sectionDetails;

	@ManyToOne(cascade = CascadeType.ALL)
	private UsersEntity user;
}
