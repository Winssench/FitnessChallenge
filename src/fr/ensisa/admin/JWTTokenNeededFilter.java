package fr.ensisa.admin;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.crypto.KeyGenerator;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.security.Key;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;


@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter{
	
	
	    @Inject
	    private Logger logger;
	    @Inject
	    private KeyGenerator keyGenerator;
	 
	    @Override
	    public void filter(ContainerRequestContext requestContext) throws IOException {
	 
	        // Get the HTTP Authorization header from the request
	        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
	        
			if (authHeader == null)
				throw new NotAuthorizedException("Bearer");
	        // Extract the token from the HTTP Authorization header
	        String token = authHeader.substring("Bearer".length()).trim();
	 
	        try {
	 
	            // Validate the token
	            Key key = keyGenerator.generateKey();
	         
	            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
	            logger.info("#### valid token : " + token);
	 
	        } catch (Exception e) {
	            logger.severe("#### invalid token : " + token);
	            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
	        }
	    }
}
