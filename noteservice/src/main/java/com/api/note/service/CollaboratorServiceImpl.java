package com.api.note.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
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
	public Long addCollaborator(Long UserId,Long noteid, String token) throws NoteException {
		TokenUtil.verifyToken(token);
		
		Optional<Long> value=collaboratorRepository.findBy(UserId,noteid);
		if(value.isPresent())
		{
			return -1L;
		}
		else 
		{
			Collaborator  collaboratorNote=new Collaborator(noteid,UserId);
			collaboratorRepository.save(collaboratorNote);
			return 1L;
		}
		
	
	}

	@Override
	public List<Note> getCollaboratorNotes(String token) throws NoteException {
		
		long userId=TokenUtil.verifyToken(token);
		System.out.println("gfhhgfhgfj "+userId);
		
		List<Long> noteIds=collaboratorRepository.findAllById(userId)
												 .orElseThrow(()-> new NoteException("Collaborator not found",100));
		
		return noterepository.findAllCollaboratorNotes(noteIds);
				
	}
	@Override
	public void deleteCollaborator(String token,Long userId, Long noteid) throws NoteException {
		TokenUtil.verifyToken(token);
		
		Long collaboratorid=collaboratorRepository.findBy(userId,noteid).get();
		collaboratorRepository.deleteById(collaboratorid);
				
	}

}

//@Override
//public List<Notes> getCollabNotes(String token) throws NoteException {
//	long userId=TokenVerify.tokenVerifing(token);
//	
//	//List<Long> noteIds=collabRepository.findAllById(userId).get();
//	Optional<List<Long>> noteIds=collabRepository.findAllById(userId);
//	if(noteIds.isPresent())
//	{
//		return notesRepository.findAllCollabNotes(noteIds.get()).get();
//	}
//	else
//	{
//		return new ArrayList<Notes>();
//	}
//}
//
//@Override
//public void deleteCollabNote(String token, long noteId) throws NoteException {
//	
//	long userId=TokenVerify.tokenVerifing(token);
//	userId=31;
//	Long id	=collabRepository.findBy(noteId,userId).get();
//	collabRepository.deleteById(id);
//	
//}
//
//}
//
//
