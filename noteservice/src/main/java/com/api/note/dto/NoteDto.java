package com.api.note.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class NoteDto {
	
	   private String title;

	   private String description;

	   private Boolean isArchive ;

	   private Boolean isPin;
	  
	   private String image;
	   
	   private String color;


}
