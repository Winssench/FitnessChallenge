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
 *		@file            	SignIn.java
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
import fr.ensisa.res.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Path("/signin")
public class SignIn {

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response connectionInTextPlain(@QueryParam("token") String token) {
        // Check if query parameters is not empty
        if (token == null || token.isEmpty()) {
            // Define entity
            String entity = "{error{reason='null query parameter'" +
                    ", message='Unauthorized'}" +
                    ", code='401'}";
            return Response.status(Response.Status.UNAUTHORIZED).entity(entity).build();
        }
        else {
            // TODO : Check if token is authorized
            return Response.ok("connected").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response connectionInXML(@QueryParam("token") String token) throws ParserConfigurationException {
        // Define a factory to produce DOM object trees from XML Documents
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Creates a builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Creates a document
        Document doc = builder.newDocument();

        // Check if query parameters is not empty
        if (token == null || token.isEmpty()) {
            // Creates root element
            Element root = doc.createElement("errors");
            doc.appendChild(root);

            // Creates error element
            Element error = doc.createElement("error");

            // Creates reason element
            Element reason = doc.createElement("reason");
            reason.appendChild(doc.createTextNode("null query parameter"));
            error.appendChild(reason);

            // Creates message element
            Element message = doc.createElement("message");
            message.appendChild(doc.createTextNode("Unauthorized"));
            error.appendChild(message);

            // Creates code element
            Element code = doc.createElement("code");
            code.appendChild(doc.createTextNode("401"));
            root.appendChild(error);
            root.appendChild(code);

            return Response.status(Response.Status.UNAUTHORIZED).entity(Parser.XML(doc)).build();
        }
        else {
            // TODO : Check if token is authorized
            return Response.ok("connected").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response connectionInJSON(@QueryParam("token") String token) {
        // Check if query parameters is not empty
        if (token == null || token.isEmpty()) {
            // Creates a JsonObject Builder
            JsonObject value = Json.createObjectBuilder()
                    .add("error",
                            Json.createObjectBuilder()
                                    .add("reason", "null query parameter")
                                    .add("message", "Unauthorized")
                                    .build()
                    )
                    .add("code", "401")
                    .build();

            return Response.status(Response.Status.UNAUTHORIZED).entity(value.toString()).build();
        }
        else {
            // TODO : Check if token is authorized
            return Response.ok("connected").build();
        }
    }

}
