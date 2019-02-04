package com.api.label.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.note.entity.Label;
import com.api.note.exception.NoteException;
import com.api.note.response.Response;
import com.api.note.service.LabelService;




@RestController
@CrossOrigin(origins= {"http://localhost:4200"},exposedHeaders= {"Authorization"})
@RequestMapping("/api/label")
public class LabelController {
	@Autowired
	private LabelService labelservices;
	
	@PostMapping
	public ResponseEntity<Response> createLabel(@RequestBody Label label,@RequestHeader String token) throws NoteException
	{
		
		labelservices.createLabel(label, token);
		
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Label Created successfully");
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PostMapping("/addlabeltonote")
	public ResponseEntity<Response> labelAddToNote(@RequestParam long noteid,@RequestParam long labelId) throws NoteException
	{
		labelservices.labelAddToNote(noteid, labelId);
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Label Added To Note");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    }
	
	
	@GetMapping
	public ResponseEntity<List<Label>> getAllLabels(@RequestHeader("token") String token
			) throws NoteException
	{
	    List<Label> list=labelservices.getAllLabels(token);
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Gets all labels successfully");
		return new ResponseEntity<List<Label>>(list,HttpStatus.OK);
		
	}
	
	@PostMapping("/removelabelfromnote")
	public ResponseEntity<Response> removeLabelFromNote(@RequestParam long noteid,@RequestParam long labelId)
	{
		labelservices.removeLabelFromNote(noteid,labelId);
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Label removed from  Note");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
     }
	
	@PutMapping
	public ResponseEntity<Response> updateLabel(@RequestBody Label label,@RequestHeader String token) throws NoteException
	{
		
		labelservices.updateLabel(label, token);
		
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Label Updated successfully");
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	//@PostMapping("/delete")
	@DeleteMapping
	public ResponseEntity<Response> deleteLabel(@RequestBody Label label,@RequestHeader String token) throws NoteException
	{
		labelservices.deleteLabel(label,token);
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Label Deleted");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
    }
	@GetMapping("/sortlabelbytitle")
	public List<?> sortByTitle()
	{
		return labelservices.sortByTitle();
		
	}
	
	
}
