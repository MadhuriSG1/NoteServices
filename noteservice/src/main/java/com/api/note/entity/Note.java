package com.api.note.entity;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "note")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long noteid;
	
	@NotNull
	private String title;

//	@NotNull
	@Column(length=2000)
	private String description;

	private Boolean isTrash;

	private Boolean isArchive;

	private Boolean isPin;
	
	@Column(columnDefinition="varchar(500)")
	private String image;

	@Column(columnDefinition="varchar(20) ")
	private String color;

	private LocalDateTime reminder;

//	@NotNull
	private LocalDateTime createdate =LocalDateTime.now();

//	@NotNull
	private LocalDateTime updateddate=LocalDateTime.now();

	@NotNull
	private long userId;
}
