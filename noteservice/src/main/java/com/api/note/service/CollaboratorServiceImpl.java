package com.api.note.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.note.entity.Collaborator;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.repository.CollaboratorRepository;
import com.api.note.repository.NoteRepository;
import com.api.note.util.TokenUtil;

@Service
public class CollaboratorServiceImpl implements CollaboratorService {

	@Autowired
	private CollaboratorRepository collaboratorRepository;
	
	@Autowired
	private NoteRepository noterepository;


	@Override
	public void addCollaborator(Long noteid, Long id, String token) throws NoteException {
		TokenUtil.verifyToken(token);
		Collaborator  collaboratorNote=new Collaborator(noteid,id);
		collaboratorRepository.save(collaboratorNote);
	}

	@Override
	public List<Note> getCollaboratorNotes(String token) throws NoteException {
		
		long id=TokenUtil.verifyToken(token);
		
		List<Long> noteId=collaboratorRepository.findAllById(id);
		
		           
				return noterepository.findAllCollaboratorNotes(noteId);
	}

	@Override
	public void deleteCollaborator(String token, Long noteid) throws NoteException {
		long id=TokenUtil.verifyToken(token);
				
	}

}
