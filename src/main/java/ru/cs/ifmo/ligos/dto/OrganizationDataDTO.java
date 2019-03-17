package ru.cs.ifmo.ligos.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.cs.ifmo.ligos.db.entities.RoleName;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDataDTO {

	@ApiModelProperty(position = 0)
	private String email;

	@ApiModelProperty(position = 1)
	private String password;

	@ApiModelProperty(position = 2)
	List<RoleName> roles;
}
