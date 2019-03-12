package ru.cs.ifmo.ligos.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.cs.ifmo.ligos.db.entities.Role;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDTO {

	@ApiModelProperty(position = 0)
	private String name;

	@ApiModelProperty(position = 1)
	private String email;

	@ApiModelProperty(position = 2)
	private String password;

	@ApiModelProperty(position = 3)
	private String authType;

	@ApiModelProperty(position = 4)
	List<Role> roles;

}