package com.api.note.dto;

import java.util.List;

import com.api.note.entity.Note;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
//@ToString
public class TotalNotesDto {
	private Note note;
	private List<CollaboratorUserDetails> collabList;
	
	public TotalNotesDto(Note note,List<CollaboratorUserDetails>collabList )
	{
		this.note=note;
		this.collabList=collabList;
	}

}
