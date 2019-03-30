package ru.cs.ifmo.ligos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDataFullDTO {

	@ApiModelProperty(position = 0)
	private String surname;

	@ApiModelProperty(position = 1)
	private String patronymic;

	@ApiModelProperty(position = 2)
	private String phoneNumber;

	@ApiModelProperty(position = 3)
	private String city;

	@ApiModelProperty(position = 4)
	private String gender;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(position = 5)
	private Date birthday;

	@ApiModelProperty(position = 6)
	private MultipartFile photo;

}
