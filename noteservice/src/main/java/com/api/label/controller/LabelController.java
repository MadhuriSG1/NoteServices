package com.api.label.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.note.dto.LabelDto;
import com.api.note.entity.Label;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.response.Response;
import com.api.note.service.LabelService;

import lombok.ToString;


@RestController
//@CrossOrigin(origins= {"http://localhost:4200"},exposedHeaders= {"Authorization"})
@RequestMapping("/api/label")
public class LabelController {
	@Autowired
	private LabelService labelservices;
	
	
	@PostMapping
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto labeldto, 
			@RequestHeader("token") String token) throws NoteException
	{
		labelservices.createLabel(labeldto, token);
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Label Created successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<Response> addLabelsToNote(@RequestParam long noteid, @RequestHeader("token") String token
			) throws NoteException
	{
		labelservices.getAllLabels(noteid, token);
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage("Note deleted successfully");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}
	

}
