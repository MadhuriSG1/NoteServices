package com.api.note.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@AllArgsConstructor
@Getter
@ToString
public class CollaboratorUserDetails {
	private String email;
	private String image;
	
	public CollaboratorUserDetails () {
		
}
}
