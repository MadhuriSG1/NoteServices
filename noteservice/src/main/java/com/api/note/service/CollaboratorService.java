package com.api.note.service;
import java.util.List;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;

public interface CollaboratorService {

	void addCollaborator(Long noteid, Long id, String token) throws NoteException;

	List<Note> getCollaboratorNotes(String token) throws NoteException;

	void deleteCollaborator(String token, Long noteid) throws NoteException;
	

}
