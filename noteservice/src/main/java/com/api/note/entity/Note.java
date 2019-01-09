package com.api.note.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long noteid;

	private String title;

	private String description;

	private Boolean trash;

	private Boolean archive;

	private Boolean pin;;
	
	private String image;

	private String color;

	private LocalDateTime reminder;

	private LocalDateTime createdate;

	private LocalDateTime updateddate;

	private long userid;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getNoteid() {
		return noteid;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getTrash() {
		return trash;
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

	public LocalDateTime getReminder() {
		return reminder;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public LocalDateTime getUpdateddate() {
		return updateddate;
	}

	public void setNoteid(long noteid) {
		this.noteid = noteid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTrash(Boolean trash) {
		this.trash = trash;
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

	public void setReminder(LocalDateTime reminder) {
		this.reminder = reminder;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	public void setUpdateddate(LocalDateTime updateddate) {
		this.updateddate = updateddate;
	}

}
