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

	private Boolean isArchive=Boolean.FALSE;

	private Boolean isPin=Boolean.FALSE;
	
	private Boolean isTrash = Boolean.FALSE;

	private String image;

	private String color;

}
