package com.api.note.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.response.Response;
import com.api.note.service.CollaboratorService;

/**
 * @author admin1
 * @RestController-Map incoming request to appropriate Class
 * @RequestMapping-Map incoming request to appropriate method
 *
 */
@RestController
@CrossOrigin(origins = { "http://localhost:4200" }, exposedHeaders = { "Authorization" })
@RequestMapping("/api/collaborator")
public class CollaboratorController {

	@Autowired
	private CollaboratorService collaboratorservices;

	@PostMapping
	public ResponseEntity<Response> addCollaborator(@RequestParam long sharedUserID, @RequestParam long sharedNoteId,
			@RequestHeader("token") String token) throws NoteException {

		long resp = collaboratorservices.addCollaborator(sharedUserID, sharedNoteId, token);
		Response response = new Response();
		if (resp == -1L) {
			response.setStatusCode(100);
			response.setStatusMessage("Person already added");
		} else {
			response.setStatusCode(200);
			response.setStatusMessage("Collaborator added successfully");
		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	/**
	 * Purpose:Get collaborator note by passing token
	 * 
	 * @param token
	 * @return
	 * @throws NoteException
	 */
	@GetMapping
	public ResponseEntity<List<Note>> getCollaboratorNotes(@RequestHeader("token") String token) throws NoteException {
		List<Note> list = collaboratorservices.getCollaboratorNotes(token);
		return new ResponseEntity<List<Note>>(list, HttpStatus.OK);

	}

	/**
	 * Purpose:Delete collaborator note by passing sharedNoteId and sharedUserID
	 * 
	 * @param token
	 * @param sharedUserID
	 * @param sharedNoteId
	 * @return
	 * @throws NoteException
	 */
	@DeleteMapping
	public ResponseEntity<Response> deleteCollaborator(@RequestHeader("token") String token,
			@RequestParam long sharedUserID, @RequestParam long sharedNoteId) throws NoteException {
		collaboratorservices.deleteCollaborator(token, sharedUserID, sharedNoteId);
		Response response = new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Collaborator user deleted successfully");
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

}
