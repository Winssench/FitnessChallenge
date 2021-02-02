/**
 * Copyright © 2021  	Hethsron Jedaël BOUEYA
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
 *		@file            	Authentication.java
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
import fr.ensisa.controllers.UserManager;
import fr.ensisa.res.Parser;
import fr.ensisa.security.JWTokenUtility;
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

@Path("/")
public class Authentication {

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/signin")
    public Response signInXML(@QueryParam("username") String username, @QueryParam("password") String password) throws ParserConfigurationException {
        // Define a factory to produce DOM object trees from XML Documents
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Creates a builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Creates a document
        Document doc = builder.newDocument();

        // Check if query parameters is not empty
        if ((username == null && password == null) || (username.isEmpty() && password.isEmpty())) {
            // Creates root element
            Element root = doc.createElement("errors");
            doc.appendChild(root);

            // Creates error element
            Element error = doc.createElement("error");

            // Creates reason element
            Element reason = doc.createElement("reason");
            reason.appendChild(doc.createTextNode("null token"));
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
            // Check if user already exists in the database
            if (UserManager.login(username, password)) {
                // Creates root element
                Element root = doc.createElement("success");
                doc.appendChild(root);

                // Creates message element
                Element message = doc.createElement("message");
                message.appendChild(doc.createTextNode("user has been created"));
                root.appendChild(message);

                // Creates token element
                Element token = doc.createElement("token");
                token.appendChild(doc.createTextNode(JWTokenUtility.build(username)));
                root.appendChild(token);

                // Creates code element
                Element code = doc.createElement("code");
                code.appendChild(doc.createTextNode("200"));
                root.appendChild(code);

                return Response.ok(Parser.XML(doc)).build();
            }
            else {
                // Creates root element
                Element root = doc.createElement("errors");
                doc.appendChild(root);

                // Creates error element
                Element error = doc.createElement("error");

                // Creates reason element
                Element reason = doc.createElement("reason");
                reason.appendChild(doc.createTextNode("nothing satisfies the criteria given by the user agent"));
                error.appendChild(reason);

                // Creates message element
                Element message = doc.createElement("message");
                message.appendChild(doc.createTextNode("Not Acceptable"));
                error.appendChild(message);

                // Creates code element
                Element code = doc.createElement("code");
                code.appendChild(doc.createTextNode("406"));
                root.appendChild(error);
                root.appendChild(code);

                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(Parser.XML(doc)).build();
            }
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/signin")
    public Response signInJSON(@QueryParam("username") String username, @QueryParam("password") String password) {
        // Check if query parameters is not empty
        if ((username == null && password == null) || (username.isEmpty() && password.isEmpty())) {
            // Creates a JsonObject Builder
            JsonObject value = Json.createObjectBuilder()
                    .add("error",
                            Json.createObjectBuilder()
                                    .add("reason", "null token")
                                    .add("message", "Unauthorized")
                                    .build()
                    )
                    .add("code", "401")
                    .build();

            return Response.status(Response.Status.UNAUTHORIZED).entity(value.toString()).build();
        }
        else {
            // Check if user already exists in the database
            if (UserManager.login(username, password)) {
                // Creates a JsonObject Builder
                JsonObject value = Json.createObjectBuilder()
                        .add("success",
                                Json.createObjectBuilder()
                                        .add("message", "user has been created")
                                        .add("token", JWTokenUtility.build(username))
                                        .add("code", "200")
                                        .build()
                        )
                        .build();

                return Response.ok(value.toString()).build();
            }
            else {
                // Creates a JsonObject Builder
                JsonObject value = Json.createObjectBuilder()
                        .add("error",
                                Json.createObjectBuilder()
                                        .add("reason", "nothing satisfies the criteria given by the user agent")
                                        .add("message", "Not Acceptable")
                                        .build()
                        )
                        .add("code", "406")
                        .build();

                return Response.status(Response.Status.NOT_ACCEPTABLE).entity(value.toString()).build();
            }
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/signup")
    public Response signUpXML(@QueryParam("username") String username, @QueryParam("password") String password) throws ParserConfigurationException {
        // Define a factory to produce DOM object trees from XML Documents
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Creates a builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Creates a document
        Document doc = builder.newDocument();

        // Check if query parameters is not empty
        if ((username == null && password == null) || (username.isEmpty() && password.isEmpty())) {
            // Creates root element
            Element root = doc.createElement("errors");
            doc.appendChild(root);

            // Creates error element
            Element error = doc.createElement("error");

            // Creates reason element
            Element reason = doc.createElement("reason");
            reason.appendChild(doc.createTextNode("null query parameters"));
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
            // Check if user already exists in the database
            if (UserManager.login(username, password)) {
                // Creates root element
                Element root = doc.createElement("errors");
                doc.appendChild(root);

                // Creates error element
                Element error = doc.createElement("error");

                // Creates reason element
                Element reason = doc.createElement("reason");
                reason.appendChild(doc.createTextNode("user already exists"));
                error.appendChild(reason);

                // Creates message element
                Element message = doc.createElement("message");
                message.appendChild(doc.createTextNode("Conflict"));
                error.appendChild(message);

                // Creates code element
                Element code = doc.createElement("code");
                code.appendChild(doc.createTextNode("409"));
                root.appendChild(error);
                root.appendChild(code);

                return Response.status(Response.Status.CONFLICT).entity(Parser.XML(doc)).build();
            }
            else {
                // Adding new User in the database
                if (UserManager.create(username, password)) {
                    // Creates root element
                    Element root = doc.createElement("success");
                    doc.appendChild(root);

                    // Creates message element
                    Element message = doc.createElement("message");
                    message.appendChild(doc.createTextNode("user has been created"));
                    root.appendChild(message);

                    // Creates code element
                    Element code = doc.createElement("code");
                    code.appendChild(doc.createTextNode("200"));
                    root.appendChild(code);

                    return Response.ok(Parser.XML(doc)).build();
                }
                else {
                    // Creates root element
                    Element root = doc.createElement("errors");
                    doc.appendChild(root);

                    // Creates error element
                    Element error = doc.createElement("error");

                    // Creates reason element
                    Element reason = doc.createElement("reason");
                    reason.appendChild(doc.createTextNode("The server has encountered a situation that it does not know how to handle"));
                    error.appendChild(reason);

                    // Creates message element
                    Element message = doc.createElement("message");
                    message.appendChild(doc.createTextNode("Internal Server Error"));
                    error.appendChild(message);

                    // Creates code element
                    Element code = doc.createElement("code");
                    code.appendChild(doc.createTextNode("500"));
                    root.appendChild(error);
                    root.appendChild(code);

                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Parser.XML(doc)).build();
                }
            }
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/signup")
    public Response signUpJSON(@QueryParam("username") String username, @QueryParam("password") String password) {
        // Check if query parameters is not empty
        if ((username == null && password == null) || (username.isEmpty() && password.isEmpty())) {
            // Creates a JsonObject Builder
            JsonObject value = Json.createObjectBuilder()
                    .add("error",
                            Json.createObjectBuilder()
                                    .add("reason", "null query parameters")
                                    .add("message", "Unauthorized")
                                    .build()
                    )
                    .add("code", "401")
                    .build();

            return Response.status(Response.Status.UNAUTHORIZED).entity(value.toString()).build();
        }
        else {
            // Check if user already exists in the database
            if (UserManager.login(username, password)) {
                // Creates a JsonObject Builder
                JsonObject value = Json.createObjectBuilder()
                        .add("error",
                                Json.createObjectBuilder()
                                        .add("reason", "user already exists")
                                        .add("message", "Conflict")
                                        .build()
                        )
                        .add("code", "409")
                        .build();

                return Response.status(Response.Status.CONFLICT).entity(value.toString()).build();
            }
            else {
                // Adding new User in the database
                if (UserManager.create(username, password)) {
                    // Creates a JsonObject Builder
                    JsonObject value = Json.createObjectBuilder()
                            .add("success",
                                    Json.createObjectBuilder()
                                            .add("message", "user has been created")
                                            .add("code", "200")
                                            .build()
                            )
                            .build();

                    return Response.ok(value.toString()).build();
                }
                else {
                    // Creates a JsonObject Builder
                    JsonObject value = Json.createObjectBuilder()
                            .add("error",
                                    Json.createObjectBuilder()
                                            .add("reason", "The server has encountered a situation that it does not know how to handle")
                                            .add("message", "Internal Server Error")
                                            .build()
                            )
                            .add("code", "500")
                            .build();

                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(value.toString()).build();
                }
            }
        }
    }


}
