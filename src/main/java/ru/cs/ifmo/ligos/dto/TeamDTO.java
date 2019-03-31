package ru.cs.ifmo.ligos.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

	private Long id;

	@ApiModelProperty(position = 0)
	private Long trainerId;

	@ApiModelProperty(position = 1)
	private Long captainId;

	@ApiModelProperty(position = 2, required = true)
	private String name;

	@ApiModelProperty(position = 3)
	private MultipartFile photo;

	public TeamDTO(Long id, String name){
		this.id = id;
		this.name = name;
	}

}
