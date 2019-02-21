package com.api.note.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.note.dto.NoteDto;
import com.api.note.dto.TotalNotesDto;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.response.Response;
import com.api.note.service.CollaboratorService;
import com.api.note.service.NoteService;

/**
 * @author bridgelabz
 * @RestController-Map incoming request to appropriate Class
 * @RequestMapping-Map incoming request to appropriate method 
 * @RequestBody-
 *
 */

@RestController
@CrossOrigin(origins= {"http://localhost:4200"},exposedHeaders= {"Authorization"})
@RequestMapping("/api/note")
public class NoteController {

	@Autowired
	private NoteService noteservices;
	
	@Autowired
	private CollaboratorService collaboratorService;
	
	/**
	 * Create a new Note
	 * @param createnotedto
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws NoteException
	 */
	@PostMapping
	public ResponseEntity<Response> createNote(@RequestBody NoteDto noteDto, 
			@RequestHeader("token") String token) throws NoteException
	{
	   // log.info(token);
		noteservices.createNote(noteDto,token);
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Note Created successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	/**
	 * Update Note
	 * @param note
	 * @param request
	 * @return
	 * @throws NoteException
	 */
	@RequestMapping(value = "/updatenote", method = RequestMethod.POST)
	public ResponseEntity<Response> updateNote(@RequestBody Note note, 
			@RequestHeader("token") String token) throws NoteException
	{

		noteservices.updateNote(note,token);
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Note updated successfully");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/**
	 * Delete note
	 * @param note
	 * @param request
	 * @return
	 * @throws NoteException
	 */
	@RequestMapping(value = "/deletenote", method = RequestMethod.POST)
	public ResponseEntity<Response> deleteNote(@RequestBody Note note, @RequestHeader("token") String token
			) throws NoteException
	{
		System.out.println(note);
		noteservices.deleteNote(note,token);
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Note deleted successfully");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}
	
	
	/**
	 * Get All Notes
	 * @return
	 * @throws NoteException
	 */
	
	@GetMapping
	public ResponseEntity<List<TotalNotesDto>> getAllNotes(@RequestHeader("token") String token,@RequestParam String isArchive,@RequestParam String isTrash) 
			throws NoteException
	{
	    List<TotalNotesDto> listTotalNotes=noteservices.getAllNotes(token,isArchive,isTrash);
	    return new ResponseEntity<List<TotalNotesDto>>(listTotalNotes,HttpStatus.OK);
		
	}
	
	/*@GetMapping
	public ResponseEntity<List<Notes>>  ResponseEntity<List<SendingNotes>> void listAllNotes(@RequestHeader("token")String token,@RequestParam String archive,@RequestParam String trash)throws NoteException //@PathVariable("value") String value,
	{
		System.out.println(archive+"   "+trash);
		List<SendingNotes> notesall=noteServices.listAllNotes(token, archive, trash);
		return new ResponseEntity<List<SendingNotes>>(notesall,HttpStatus.OK);
		
} 
	*/

}
