package com.api.note.dto;

import java.util.List;

import com.api.note.entity.Note;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
//@ToString
public class TotalNotesDto {
	private Note note;
	private List<CollaboratorUserDetails> collabUserDetailsList;
	
	public TotalNotesDto(Note note,List<CollaboratorUserDetails>collabUserDetailsList )
	{
		this.note=note;
		this.collabUserDetailsList=collabUserDetailsList;
	}

}
