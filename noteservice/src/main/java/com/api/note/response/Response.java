package com.api.note.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Response{
	
	private int statusCode;
	private String statusMessage;
}
