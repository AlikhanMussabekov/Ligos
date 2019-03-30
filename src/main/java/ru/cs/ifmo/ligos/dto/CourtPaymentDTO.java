package ru.cs.ifmo.ligos.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.cs.ifmo.ligos.db.entities.AgeCategoryEntity;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourtPaymentDTO {

	@ApiModelProperty(position = 0)
	private Integer price;

	@ApiModelProperty(position = 1)
	private Date hour_from;

	@ApiModelProperty(position = 2)
	private Date hour_to;

	@ApiModelProperty(position = 4)
	private Boolean status;

	@ApiModelProperty(position = 5)
	private Date date;

}