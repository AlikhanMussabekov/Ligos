package ru.cs.ifmo.ligos.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GoalDTO {

	@ApiModelProperty(position = 0, required = true)
	private Long matchId;

	@ApiModelProperty(position = 1, required = true)
	private Long userId;

	@ApiModelProperty(position = 3, required = true)
	private Integer time;

}
