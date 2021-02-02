package fr.ensisa.routes;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import fr.ensisa.controllers.UserManager;
import fr.ensisa.model.User;
import fr.ensisa.security.JWTokenUtility;

@Path("/")
public class Authentification {
	
	@POST
	@Path("/signin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signin(@QueryParam("login") String login, @QueryParam("password") String password) {
		User u = UserManager.login(login, password);

		if (u != null)
			return Response.ok().entity(JWTokenUtility.buildJWT(Long.toString(u.getId()))).build();

		return Response.status(Status.NOT_ACCEPTABLE).build();
	}
	
	/**
	 * Méthode permettant de récupérer l'ensemble des roles d'un utilisateur
	 * 
	 * @param user l'utilisateur
	 * @return une liste de tous les roles associés à l'utilisateur user
	 */
	public static List<String> findUserRoles(String user) {
		return null;
	}

}
