package com.api.note.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CollabUser {
	long id;
	String email;
	String image;
	public CollabUser(long id)
	{
		this.id=id;
	}
	

}





