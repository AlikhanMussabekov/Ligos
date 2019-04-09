package ru.cs.ifmo.ligos.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO implements Serializable {
	private String review;
	private Short rating;
}
