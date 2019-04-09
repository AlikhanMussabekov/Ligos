package ru.cs.ifmo.ligos.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@AssociationOverrides({
		@AssociationOverride(name = "id.user",
				joinColumns = @JoinColumn(name = "usersid")),
		@AssociationOverride(name = "id.sectionDetails",
				joinColumns = @JoinColumn(name = "section_detailsid")) })
public class AttendanceEntity implements Serializable {

	@EmbeddedId
	private AttendanceId id = new AttendanceId();

	@Column(name = "PRESENT", nullable = true)
	private Boolean present;

	@Column(name = "date_at", nullable = true)
	private Date date;

	public void setUser(UsersEntity user){
		getId().setUser(user);
	}

	public void setSectionDetails(SectionDetailsEntity sectionDetails){
		getId().setSectionDetails(sectionDetails);
	}

}
