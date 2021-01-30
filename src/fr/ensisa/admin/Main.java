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
import fr.ensisa.res.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
			String entity = "{error{reason='no challenges'" +
					", message='Not Found'}" +
					", code='404'}";
			return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
		}
		else {
			StringBuilder entity = new StringBuilder();
			for (Challenge c : challengeFactory.getDao().findAll()) {
				entity.append(c.toString());
			}
			return Response.ok(entity.toString()).build();
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
	public Response getChallengesInXML() throws ParserConfigurationException {
		// Define a factory to produce DOM object trees from XML Documents
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Creates a builder
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Creates a document
		Document doc = builder.newDocument();

		// Check if there is no challenge in the DAO
		if (challengeFactory.getDao().count() == 0) {
			// Creates root element
			Element root = doc.createElement("errors");
			doc.appendChild(root);

			// Creates error element
			Element error = doc.createElement("error");

			// Creates reason element
			Element reason = doc.createElement("reason");
			reason.appendChild(doc.createTextNode("no challenges"));
			error.appendChild(reason);

			// Creates message element
			Element message = doc.createElement("message");
			message.appendChild(doc.createTextNode("Not Found"));
			error.appendChild(message);

			// Creates code element
			Element code = doc.createElement("code");
			code.appendChild(doc.createTextNode("404"));
			root.appendChild(error);
			root.appendChild(code);

			return Response.status(Response.Status.NOT_FOUND).entity(Parser.XML(doc)).build();
		}
		else {
			// Creates root element
			Element root = doc.createElement("challenges");
			doc.appendChild(root);

			for (Challenge c : challengeFactory.getDao().findAll()) {
				// Create challenge element
				Element challenge = doc.createElement("challenge");

				// Create id element
				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(String.valueOf(c.getId())));
				challenge.appendChild(id);

				// Create name element
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(c.getName()));
				challenge.appendChild(name);

				// Create author element
				Element author = doc.createElement("author");
				author.appendChild(doc.createTextNode(c.getAuthor()));
				challenge.appendChild(author);

				// Create maxUsers element
				Element maxUsers = doc.createElement("maxUsers");
				maxUsers.appendChild(doc.createTextNode(String.valueOf(c.getMaxUsers())));
				challenge.appendChild(maxUsers);

				// Create gamingMode element
				Element gamingMode = doc.createElement("gamingMode");
				gamingMode.appendChild(doc.createTextNode(c.getMode().getName()));
				challenge.appendChild(gamingMode);

				// Append challenge element to root element
				root.appendChild(challenge);
			}

			return Response.ok(Parser.XML(doc)).build();
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
			for (Challenge c : challengeFactory.getDao().findAll()) {
				// Creates a JsonObject Builder
				array.add(
						Json.createObjectBuilder()
						.add("id", c.getId())
						.add("name", c.getName())
						.add("author", c.getAuthor())
						.add("maxUsers", c.getMaxUsers())
						.add("gamingMode", c.getMode().getName())
						.build()
				);
			}
			return Response.ok(array.build().toString()).build();
		}
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/signup")
	public Response subscriptionInTextPlain() {
		// TODO: Get Request Parameters
		return Response.ok("adding").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/signup")
	public Response subscriptionInXML() {
		// TODO: Get Request Parameters
		return Response.ok("adding").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public Response subscriptionInJSON() {
		// TODO: Get Request Parameters
		return Response.ok("adding").build();
	}

}
