package com.api.note.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoteException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = 1L;
	private String errorMessage;
	private int errorCode;
	
	public NoteException() {
		
	}
	public NoteException(String errorMessage) {
		super(errorMessage);	
	}
	
	
	public NoteException(String errorMessage, int errorCode) {
		this(errorMessage);
		this.errorCode = errorCode;
	}


	


}
