package ru.cs.ifmo.ligos.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ru.cs.ifmo.ligos.db.entities.AddressEntity;
import ru.cs.ifmo.ligos.db.entities.AgeCategoryEntity;
import ru.cs.ifmo.ligos.db.entities.SectionEntity;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SectionDetailsDTO {

	@ApiModelProperty(position = 0)
	private Integer price;

	@ApiModelProperty(position = 1)
	private AgeCategoryEntity ageCategory;

	@ApiModelProperty(position = 2)
	private Date timeStart;

	@ApiModelProperty(position = 3)
	private Date timeEnd;

	@ApiModelProperty(position = 4)
	private Integer maxUsersCount;

	@ApiModelProperty(position = 5)
	private String trainer_email;

}
