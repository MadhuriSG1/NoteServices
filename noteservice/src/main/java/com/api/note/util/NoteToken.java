package com.api.note.util;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class NoteToken {
	
	public static String TOKEN_SECRET="s4T2zOIWHMM1sxq" ;
	public static String generateToken(long id)
	{
		 try {
			  Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
	           String token = JWT.create()
	                    .withClaim("ID",id)
	                    .sign(algorithm);
	                   
	            return token;
	        } catch (UnsupportedEncodingException exception) {
	            exception.printStackTrace();
	            //log WRONG Encoding message
	        } catch (JWTCreationException exception) {
	            exception.printStackTrace();
	            //log Token Signing Failed
	        }
	        return null;
		
		
	}

}
