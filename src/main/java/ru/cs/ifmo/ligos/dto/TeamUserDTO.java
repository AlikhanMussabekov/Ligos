package ru.cs.ifmo.ligos.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.cs.ifmo.ligos.db.entities.Position;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamUserDTO {

	@ApiModelProperty(position = 0, required = true)
	private Long teamId;

	@ApiModelProperty(position = 1, required = true)
	private Long userId;

	@ApiModelProperty(position = 2, required = true)
	private Position position;

}
