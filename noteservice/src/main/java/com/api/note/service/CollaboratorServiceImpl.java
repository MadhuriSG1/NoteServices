package com.api.note.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.note.entity.Collaborator;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.repository.CollaboratorRepository;
import com.api.note.repository.NoteRepository;
import com.api.note.util.TokenUtil;

/**
 * @author admin1
 *
 */
@Service
public class CollaboratorServiceImpl implements CollaboratorService {

	@Autowired
	private CollaboratorRepository collaboratorRepository;

	@Autowired
	private NoteRepository noterepository;

	/*
	 * Add collaborator to note by passing UserId,noteid.
	 */
	@Override
	public Long addCollaborator(Long UserId, Long noteid, String token) throws NoteException {
		TokenUtil.verifyToken(token);

		Optional<Long> collabid = collaboratorRepository.findBy(UserId, noteid);
		if (collabid.isPresent()) {
			return -1L;
		} else {
			Collaborator collaboratorNote = new Collaborator(noteid, UserId);
			collaboratorRepository.save(collaboratorNote);
			return 1L;
		}

	}

	/*
	 * Get collaborator note by passing token findAllById(userId)-gives noteIds for
	 * particular userId findAllCollaboratorNotes(noteIds)-gives no. of notes for
	 * no. of noteIds
	 */
	@Override
	public List<Note> getCollaboratorNotes(String token) throws NoteException {

		long userId = TokenUtil.verifyToken(token);
		List<Long> noteIds = collaboratorRepository.findAllById(userId)
				.orElseThrow(() -> new NoteException("Collaborator not found", 100));

		return noterepository.findAllCollaboratorNotes(noteIds);

	}

	/*
	 * Delete collaboratorId by passing userId,noteId
	 */
	@Override
	public void deleteCollaborator(String token, Long userId, Long noteId) throws NoteException {
		TokenUtil.verifyToken(token);

		Long collaboratorId = collaboratorRepository.findBy(userId, noteId).get();
		collaboratorRepository.deleteById(collaboratorId);

	}

}
