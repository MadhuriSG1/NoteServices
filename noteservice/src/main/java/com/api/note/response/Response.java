package com.api.note.response;

public class Response {
	
	private int statusCode;
	private String statusMessage;
	public int getStatusCode() {
		return statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", statusMessage=" + statusMessage + "]";
	}
	
	

}
