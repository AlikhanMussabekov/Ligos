package ru.cs.ifmo.ligos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstablishedChatChannelDTO {
	private String channelUuid;

	private String userOneFullName;

	private String userTwoFullName;
}
