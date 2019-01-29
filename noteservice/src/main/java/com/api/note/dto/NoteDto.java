package com.api.note.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NoteDto {
	@NotNull
	private String title;

	//@NotNull
	private String description;

	private Boolean isArchive;

	private Boolean isPin;

	private String image;

	private String color;

}
