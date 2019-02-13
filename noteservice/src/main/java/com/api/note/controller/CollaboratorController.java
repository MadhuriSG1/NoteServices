package com.api.note.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.note.entity.Collaborator;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.response.Response;
import com.api.note.service.CollaboratorService;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"},exposedHeaders= {"Authorization"})
@RequestMapping("/api/collaborator")
public class CollaboratorController {

	@Autowired
	private CollaboratorService collaboratorservices;
	@PostMapping
	public ResponseEntity<Response> addCollaborator(@RequestParam long noteid,@RequestParam long id,
			@RequestHeader("token") String token) throws NoteException
	{
		collaboratorservices.addCollaborator(noteid,id,token);
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Collaborator added successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
		
		
	}
	@GetMapping
	public ResponseEntity<List<Note>> getCollaboratorNotes(@RequestHeader("token") String token) 
			throws NoteException {
	    List<Note> list=collaboratorservices.getCollaboratorNotes(token);
	    return new ResponseEntity<List<Note>>(list,HttpStatus.OK);
		
	}
	
	@DeleteMapping
	public ResponseEntity<Response> deleteCollaborator( @RequestHeader("token") String token,@RequestParam Long noteid) throws NoteException
	{
		collaboratorservices.deleteCollaborator(token,noteid);
		return null;
		
	}
	
	
	
	

}
