package com.api.note.service;
import java.util.List;

import com.api.note.entity.Note;
import com.api.note.exception.NoteException;

public interface CollaboratorService {

	Long addCollaborator( Long id,Long noteid, String token) throws NoteException;

	List<Note> getCollaboratorNotes(String token) throws NoteException;

	void deleteCollaborator(String token, Long UserId, Long noteid) throws NoteException;
	

}
