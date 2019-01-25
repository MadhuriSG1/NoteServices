package com.api.note.util;

import java.io.UnsupportedEncodingException;

import com.api.note.exception.NoteException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

public class TokenUtil {
	
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
	
	/**
	 * This Method accept token ,decode token,and return id
	 * @param token
	 * @return
	 * @throws NoteException
	 */
	public static long verifyToken(String token) throws NoteException {
		long id = 0;
		try {
			Verification verification = JWT.require(Algorithm.HMAC256(TokenUtil.TOKEN_SECRET));
			JWTVerifier jwtverifier = verification.build();
			DecodedJWT decodedjwt = jwtverifier.verify(token);
			Claim claim = decodedjwt.getClaim("ID");
			id = claim.asLong();
			System.out.println(id);
		} catch (Exception e) {
			throw new NoteException("Token is Not valid");
		}

		return id;
	}

}
