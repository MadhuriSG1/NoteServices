package com.api.note.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.note.response.Response;


@ControllerAdvice
public class NoteExceptionController {
	
	@ExceptionHandler(Exception.class)
	  public ResponseEntity<Response> exceptionResolver(Exception ex) {
		Response response = new Response();
	        response.setStatusCode(100);
	        response.setStatusMessage(ex.getMessage());
	  return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	    }
	
	  @ExceptionHandler(NoteException.class)
	  public ResponseEntity<Response> exceptionResolver(NoteException ex,HttpServletRequest req) {
		  Response response = new Response();
		  response.setStatusCode(100);
	        response.setStatusMessage(ex.getMessage());
	        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	    }
	  

}
