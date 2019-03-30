package ru.cs.ifmo.ligos.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;
import ru.cs.ifmo.ligos.db.entities.AddressEntity;
import ru.cs.ifmo.ligos.db.entities.OrganizationEntity;
import ru.cs.ifmo.ligos.db.entities.RoleName;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

	@ApiModelProperty(position = 0)
	private String name;

	@ApiModelProperty(position = 1)
	private String description;

	@ApiModelProperty(position = 2)
	private MultipartFile photo;

	@ApiModelProperty(position = 3)
	private AddressEntity address;
}
