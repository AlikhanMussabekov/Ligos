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
public class MatchDTO {

	@ApiModelProperty(position = 0, required = true)
	private Long firstTeamId;

	@ApiModelProperty(position = 1, required = true)
	private Long secondTeamId;

	@ApiModelProperty(position = 2, required = false)
	private Long courtId;

	@ApiModelProperty(position = 3, required = true)
	private Date date;

	@ApiModelProperty(position = 4, required = true)
	private Integer goalsFirst;

	@ApiModelProperty(position = 5, required = true)
	private Integer goalsSecond;

}
