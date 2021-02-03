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

import fr.ensisa.controllers.ChallengeManager;
import fr.ensisa.model.Challenge;
import javax.ws.rs.core.Response.Status;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteChallenge(@PathParam("id") String id) {

		// Challenge v = FlightManager.getFlight(id);
		Challenge v = ChallengeManager.getChallenge((Long.parseLong(id)));

		if (v != null) {
			ChallengeManager.removeChallange(v);
			return Response.ok().build();

		}

		return Response.status(Status.NOT_ACCEPTABLE).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateChallenge(@PathParam("id") String id) {

		// Challenge v = FlightManager.getFlight(id);
		Challenge v = ChallengeManager.getChallenge((Long.parseLong(id)));

		if (v != null) {
			ChallengeManager.updateChallange(v);
			return Response.ok().build();

		}

		return Response.status(Status.NOT_ACCEPTABLE).build();
	}

}
