package com.api.note.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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

	private String title;

	private String description;

	private Boolean isTrash;

	private Boolean isArchive;

	private Boolean isPin;
	
	private String image;

	private String color;

	private LocalDateTime reminder;

	private LocalDateTime createdate =LocalDateTime.now();

	private LocalDateTime updateddate=LocalDateTime.now();

	private long userid;
}
