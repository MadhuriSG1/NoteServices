package com.api.note.dto;

import javax.persistence.Column;

public class CreateNoteDto {
	
	   @Column
	   private String title;

	   @Column
	   private String description;

	   @Column
	   private Boolean archive ;

	   @Column
	   private Boolean pin;
	  
	   @Column
	   private String image;
	   
	   @Column
	   private String color;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getArchive() {
		return archive;
	}

	public Boolean getPin() {
		return pin;
	}

	public String getImage() {
		return image;
	}

	public String getColor() {
		return color;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	public void setPin(Boolean pin) {
		this.pin = pin;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setColor(String color) {
		this.color = color;
	}
	   
	   

}
