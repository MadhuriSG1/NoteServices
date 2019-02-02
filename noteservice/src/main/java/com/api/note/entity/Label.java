package com.api.note.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@Table(name = "labels")
public class Label implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long labelId;
	
	private String labelTitle;
	
	private long userId;
	
	@JsonIgnore
	 @ManyToMany(mappedBy = "labels",cascade=CascadeType.ALL)
	    private List<Note> notes ;

}
