package com.api.note.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.note.response.Response;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class NoteExceptionController {
	@ExceptionHandler(NoteException.class)
	public ResponseEntity<?> noteExceptionHandle(NoteException e)
	{
		log.error(e.getMessage(), e);
		Response response=new Response();
		response.setStatusCode(e.getErrorCode());
		response.setStatusMessage(e.getErrorMessage());
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> allExceptionHandle(Exception e)
	{
		log.error(e.getMessage(), e);
		Response response=new Response();
		response.setStatusCode(100);
		response.setStatusMessage(e.getMessage());
		return new ResponseEntity<Response>(response,HttpStatus.OK);
}
}
