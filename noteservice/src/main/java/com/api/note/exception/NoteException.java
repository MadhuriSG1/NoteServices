package com.api.note.exception;

public class NoteException extends Exception{
	
	private String errorMessage;
	private String requestedURI;
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


	public NoteException(String errorMessage, String requestedURI, int errorCode) {
		this(errorMessage,errorCode);
		this.requestedURI = requestedURI;
	}

	public NoteException(String errorMessage, String requestedURI, int errorCode,Throwable throwable) {
		super(errorMessage,throwable);
		this.errorCode=errorCode;
		this.requestedURI = requestedURI;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public String getRequestedURI() {
		return requestedURI;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public void setRequestedURI(String requestedURI) {
		this.requestedURI = requestedURI;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	@Override
	public String toString() {
		return "NoteException [errorMessage=" + errorMessage + ", requestedURI=" + requestedURI + ", errorCode="
				+ errorCode + "]";
	}


}
