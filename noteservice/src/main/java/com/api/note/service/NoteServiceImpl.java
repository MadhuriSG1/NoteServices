package com.api.note.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.api.note.dto.NoteDto;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.repository.NoteRepository;
import com.api.note.util.TokenUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

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
	public Note createNote(@Valid NoteDto createnotedto, String token) throws NoteException {

		long id = TokenUtil.verifyToken(token);
		Note note = modelMapper.map(createnotedto, Note.class);
		note.setUserid(id);
		noterepository.save(note);
		return note;
	}

	/**
	 * Update note
	 * @param note,token
	 * @throws NoteException
	 */
	@Override
	public void updateNote(Note note, String token) throws NoteException {

		long id = TokenUtil.verifyToken(token);
		System.out.println(id);
		note.setUpdateddate(LocalDateTime.now());
		note.setUserid(id);
		noterepository.save(note);
	}

	/**
	 * Delete note
	 * @param note,token
	 * @throws NoteException
	 */
	@Override
	public void deleteNote(Note note, String token) throws NoteException {
		long id = TokenUtil.verifyToken(token);
		noterepository.delete(note);
	}

	/**
	 * Delete note by id
	 * @param note,token
	 * @throws NoteException
	 */
	@Override
	public List<Note> getNotesById(String token) throws NoteException {

		long id = TokenUtil.verifyToken(token);
		System.out.println(id);
		// return (List<Note>)
		//List<Note> list = 
		return noterepository.findAllById(id);

	}
	
	/**
	 * Get all notes
	 * @throws NoteException
	 */
	@Override
	public List<Note> getAllNotes() throws NoteException {

		List<Note> list = (List<Note>) noterepository.findAll();
		return list;
	}

	/*@Override
	public List<Note> listOfNotes(String token,String value) throws NoteException{
		
		long id = verifyToken(token);
		return noterepository.findAllById(id,value);
	}*/
}
