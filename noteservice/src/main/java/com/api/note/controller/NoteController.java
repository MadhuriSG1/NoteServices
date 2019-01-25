package com.api.note.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.note.dto.NoteDto;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.repository.NoteRepository;
import com.api.note.service.NoteService;
/**
 * @author bridgelabz
 * @RestController-Map incoming request to appropriate Class
 * @RequestMapping-Map incoming request to appropriate method 
 * @RequestBody-
 *
 */

@RestController
@RequestMapping("/api/note")
public class NoteController {

	@Autowired
	private NoteService noteservices;
	/*@Autowired
	private NoteRepository noterepository;*/
	
	
	/**
	 * Create a new Note
	 * @param createnotedto
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws NoteException
	 */
	@PostMapping
	public ResponseEntity<Response> createNote(@Valid @RequestBody NoteDto noteDto, BindingResult bindingResult,
			HttpServletRequest request ) throws NoteException
	{
		if (bindingResult.hasErrors()) {
			throw new NoteException("Invalid data ", 100);
		}
		String token= request.getHeader("Authorization");
		Note note=noteservices.createNote(noteDto,token);
		Response response=new Response();
		response.setStatus(200);
		response.setMessage("Note Created successfully");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}
	
	/**
	 * Update Note
	 * @param note
	 * @param request
	 * @return
	 * @throws NoteException
	 */
	//@RequestMapping(value = "/updatenote", method = RequestMethod.PUT)
	@PutMapping
	public ResponseEntity<String> updateNote(@RequestBody Note note, 
			HttpServletRequest request) throws NoteException
	{
		String token= request.getHeader("Authorization");
		noteservices.updateNote(note,token);
		System.out.println("token  :"+token);
		return new ResponseEntity<String>("Note Updated successfully", HttpStatus.OK);
	}
	
	/**
	 * Delete note
	 * @param note
	 * @param request
	 * @return
	 * @throws NoteException
	 */
	@RequestMapping(value = "/deletenote", method = RequestMethod.POST)
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteNote(@RequestBody Note note, 
			HttpServletRequest request) throws NoteException
	{
		String token= request.getHeader("Authorization");
		noteservices.deleteNote(note,token);
		return new ResponseEntity<String>("Note Deleted successfully", HttpStatus.OK);
		
	}
	
	
	/**
	 * Get All Notes
	 * @return
	 * @throws NoteException
	 */
	@GetMapping
	public ResponseEntity<List<Note>> getAllNotes() 
			throws NoteException {
	    List<Note> list=noteservices.getAllNotes();
	    return new ResponseEntity<List<Note>>(list,HttpStatus.OK);
		
	}
	

	/**
	 * Get All Notes for given ID
	 * @param request
	 * @return
	 * @throws NoteException
	 */
	@GetMapping("/{id}")
	public ResponseEntity<List<Note>> getNotesById(HttpServletRequest request) 
			throws NoteException {
		String token= request.getHeader("Authorization");
	    List<Note> list=noteservices.getNotesById(token);
	    return new ResponseEntity(list,HttpStatus.OK);
		
	}
	
	
	/*@GetMapping("/listofnotes")
	public ResponseEntity<List<Note>> listOfnotes(HttpServletRequest request,@PathVariable("value")String value) 
			throws NoteException {
		String token= request.getHeader("Authorization");
	    List<Note> list=noteservices.listOfNotes(token,value);
	    return new ResponseEntity<List<Note>>(list,HttpStatus.OK);
		
	}*/
}
