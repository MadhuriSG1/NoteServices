package com.api.note.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.note.dto.NoteDto;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.repository.NoteRepository;
import com.api.note.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noterepository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Create Note
	 * 
	 * @throws NoteException
	 */
	@Override
	public void createNote(NoteDto createnotedto, String token) throws NoteException {

		long id = TokenUtil.verifyToken(token);
		Note note = modelMapper.map(createnotedto, Note.class);
		note.setUserId(id);
		noterepository.save(note);
//		return note;
	}

	/**
	 * Update note
	 * @param note,token
	 * @throws NoteException
	 */
	@Override
	public void updateNote(Note note, String token) throws NoteException {

		long userid = TokenUtil.verifyToken(token);
		log.info("userid");
		note.setUpdateddate(LocalDateTime.now());
		note.setUserId(userid);
		noterepository.save(note);
	}

	/**
	 * Delete note
	 * @param note,token
	 * @throws NoteException
	 */
	@Override
	public void deleteNote(Note note, String token) throws NoteException 
	{
		long id = TokenUtil.verifyToken(token);
		noterepository.delete(note);
	}
	
	/**
	 * Get all notes
	 * @throws NoteException
	 */
	@Override
	public List<Note> getAllNotes(String token,String isTrash,String isArchive) throws NoteException {
		long userid = TokenUtil.verifyToken(token);	
		boolean is1=false;
		boolean is2=false;
		if(isArchive.equals("true"))
				{
				is1=true;
				}
		
		if(isTrash.equals("true"))
				{
				is2=true;
				}
		
		return noterepository.findAllByStatus(userid, is1, is2)
				.orElse(new ArrayList<Note>());
	}

}
