package ru.cs.ifmo.ligos.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserMatchDTO {

	@ApiModelProperty(position = 0, required = true)
	private Long matchId;

	@ApiModelProperty(position = 1, required = true)
	private Long userId;

	@ApiModelProperty(position = 2, required = false)
	private Integer assists;

	@ApiModelProperty(position = 3, required = false)
	private Integer shots;

	@ApiModelProperty(position = 4, required = false)
	private Integer minsPlayed;

	@ApiModelProperty(position = 5, required = false)
	private Integer yellowCard;

	@ApiModelProperty(position = 6, required = false)
	private Integer redCard;

	@ApiModelProperty(position = 7, required = false)
	private Integer saved;

}
