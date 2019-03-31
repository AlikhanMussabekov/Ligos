package ru.cs.ifmo.ligos.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatChannelInitializationDTO {
	private long userIdOne;

	private long userIdTwo;
}