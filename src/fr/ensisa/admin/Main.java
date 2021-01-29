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
package fr.ensisa.admin;
/**
 *		@file            	Main.java
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
import fr.ensisa.factory.ChallengeFactory;
import fr.ensisa.factory.UserFactory;
import fr.ensisa.model.Challenge;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

@Path("/")
public class Main {

	private ChallengeFactory challengeFactory = new ChallengeFactory();
	private UserFactory userFactory = new UserFactory();

	/**
	 * @brief		This method is called if TEXT_PLAIN is request
	 * @return		TEXT_PLAIN
	 */
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/")
	public Response getChallengesInPlainText() {
		// Check if there is no challenge in the DAO
		if (challengeFactory.getDao().count() == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("ko").build();
		}
		else {
			return Response.ok("ok").build();
		}
	}

	/**
	 * @brief		This method is called if APPLICATION_XML is request
	 * @return		APPLICATION_XML
	 */
	@GET
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/")
	public Response getChallengesInXML() {
		// Check if there is no challenge in the DAO
		if (challengeFactory.getDao().count() == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("").build();
		}
		else {
			return Response.ok("").build();
		}
	}

	/**
	 * @brief		This method is called if TEXT_HTML is request
	 * @return		TEXT_HTML
	 */
	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	@Path("/")
	public Response getChallengesInHTML() {
		// Check if there is no challenge in the DAO
		if (challengeFactory.getDao().count() == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("").build();
		}
		else {
			
			return Response.ok("").build();
		}
	}

	/**
	 * @brief		This method is called if APPLICATION_JSON is request
	 * @return
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getChallengesInJSON() {
		// Check if there is no challenge in the DAO
		if (challengeFactory.getDao().count() == 0) {
			// Creates a JsonObject Builder
			JsonObject value = Json.createObjectBuilder()
					.add("error",
							Json.createObjectBuilder()
							.add("reason", "no challenges")
							.add("message", "Not Found")
							.build()
					)
					.add("code", "404")
					.build();
			return Response.status(Response.Status.NOT_FOUND).entity(value.toString()).build();
		}
		else {
			// Create a JSON array Builder
			JsonArrayBuilder array = Json.createArrayBuilder();
			for (Challenge challenge : challengeFactory.getDao().findAll()) {
				// Creates a JsonObject Builder
				array.add(
						Json.createObjectBuilder()
						.add("id", challenge.getId())
						.add("name", challenge.getName())
						.add("author", challenge.getAuthor())
						.add("maxUsers", challenge.getMaxUsers())
						.add("gamingMode", challenge.getMode().getName())
						.build()
				);
			}
			return Response.ok(array.build().toString()).build();
		}
	}

}
