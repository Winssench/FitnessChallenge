/**
 * Copyright © 2020  	Hethsron Jedaël BOUEYA
 * 						Omar CHICHAOUI
 * 					    Pranamika SOLANKI
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package fr.ensisa.routes;

/**
 *		@file            	Open.java
 *      @details
 *
 *      @author          	Hethsron Jedaël BOUEYA (hethsron-jedael.boueya@uha.fr)
 *      					Omar CHICHAOUI (omar.chichaoui@uha.fr)
 *      				    Pranamika SOLANKI (pranamika.solanki@uha.fr)
 *
 *      @version         	0.0.1
 *      @date            	January, 25th 2021
 *
 *      @Copyright       	GPLv3+ : GNU GPL version 3 or later
 *                       	Licencied Material - Property of Us®
 *                       	© 2020 ENSISA (UHA) - All rights reserved.
 */
import org.w3c.dom.Document;

import fr.ensisa.controllers.ChallengeManager;
import fr.ensisa.controllers.UserManager;
import fr.ensisa.dao.DAOUser;
import fr.ensisa.model.Challenge;
import fr.ensisa.model.CrossingPoint;
import fr.ensisa.model.Enigma;
import fr.ensisa.model.Obstacle;
import fr.ensisa.model.Segment;
import fr.ensisa.model.User;
import fr.ensisa.res.GamingMode;
import fr.ensisa.res.Role;
import fr.ensisa.security.SigninNeeded;

import javax.ws.rs.core.Response.Status;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/")
public class Open {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChallengesInJSON() {
		// Check if there is no challenge in the DAO

		List<Challenge> lc = ChallengeManager.getChallengs();
		if (lc != null) {
			GenericEntity<List<Challenge>> entity = new GenericEntity<List<Challenge>>(lc) {
			};
			return Response.ok().entity(entity).build();
		}
		return Response.status(Status.NO_CONTENT).build();

	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChallenge(@PathParam("id") String id) {

		// Challenge v = FlightManager.getFlight(id);
		Challenge v = ChallengeManager.getChallenge((Long.parseLong(id)));

		if (v != null)
			return Response.ok().entity(v).build();

		return Response.status(Status.NO_CONTENT).build();
	}

	@DELETE
	@SigninNeeded
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteChallenge(@PathParam("id") String id, @Context SecurityContext security) {

		// Challenge v = FlightManager.getFlight(id);
		Challenge v = ChallengeManager.getChallenge((Long.parseLong(id)));
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		boolean isadmin = security.isUserInRole("ADMIN");

		if (v != null && isadmin) {
			ChallengeManager.removeChallange(v);
			return Response.ok().build();

		}

		return Response.status(Status.NOT_ACCEPTABLE).build();
	}

	@PUT
	@SigninNeeded
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateChallenge(@PathParam("id") String id, @Context SecurityContext security) {

		// Challenge v = FlightManager.getFlight(id);
		Challenge v = ChallengeManager.getChallenge((Long.parseLong(id)));
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		boolean isadmin = security.isUserInRole("ADMIN");

		if (v != null && isadmin) {
			ChallengeManager.updateChallange(v);
			return Response.ok().build();

		}

		return Response.status(Status.NOT_ACCEPTABLE).build();
	}

}
