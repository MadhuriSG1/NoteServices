package com.api.note.entity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Collaborator implements Serializable {

	
	public Collaborator(Long noteid2, Long id2) {
		this.noteid=noteid2;
		this.id=id2;
	}
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long collaboratorid;
	private Long id; 
	private Long noteid;
	
	
	
	

}
