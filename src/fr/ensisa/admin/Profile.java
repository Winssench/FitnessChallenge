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
 *		@file            	Profile.java
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

@Path("/profile")
public class Profile {

    @GET
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response checkUserInfosInXML(@QueryParam("token") String token, @QueryParam("id") int id) throws ParserConfigurationException {
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
            // TODO : Check if token is authorized

            if (id == 0) {
                // Creates root element
                Element root = doc.createElement("errors");
                doc.appendChild(root);

                // Creates error element
                Element error = doc.createElement("error");

                // Creates reason element
                Element reason = doc.createElement("reason");
                reason.appendChild(doc.createTextNode("null id"));
                error.appendChild(reason);

                // Creates message element
                Element message = doc.createElement("message");
                message.appendChild(doc.createTextNode("Bad Request"));
                error.appendChild(message);

                // Creates code element
                Element code = doc.createElement("code");
                code.appendChild(doc.createTextNode("400"));
                root.appendChild(error);
                root.appendChild(code);

                return Response.status(Response.Status.BAD_REQUEST).entity(Parser.XML(doc)).build();
            }
            else {
                // Check if user exists in the database
                /*if (!userFactory.getDao().find(id).isPresent()) {
                    // Creates root element
                    Element root = doc.createElement("errors");
                    doc.appendChild(root);

                    // Creates error element
                    Element error = doc.createElement("error");

                    // Creates reason element
                    Element reason = doc.createElement("reason");
                    reason.appendChild(doc.createTextNode("user does not exists"));
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
                    // Get user profile information
                    User user = userFactory.getDao().find(id).get();

                    // Creates root element
                    Element root = doc.createElement("success");
                    doc.appendChild(root);

                    // Creates username element
                    Element username = doc.createElement("username");
                    username.appendChild(doc.createTextNode(user.getUsername()));
                    root.appendChild(username);

                    // Creates role element
                    Element role = doc.createElement("role");
                    role.appendChild(doc.createTextNode(user.getRole().getName()));
                    root.appendChild(role);

                    // Creates code element
                    Element code = doc.createElement("code");
                    code.appendChild(doc.createTextNode("200"));
                    root.appendChild(code);

                    return Response.ok(Parser.XML(doc)).build();
                }*/
                return null;
            }
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkUserInfosInJSON(@QueryParam("token") String token, @QueryParam("id") int id) {
        // Check if query parameters is not empty
        if (token == null || token.isEmpty()) {
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
            // TODO : Check if token is authorized

            if (id == 0) {
                // Creates a JsonObject Builder
                JsonObject value = Json.createObjectBuilder()
                        .add("error",
                                Json.createObjectBuilder()
                                        .add("reason", "null id")
                                        .add("message", "Bad Request")
                                        .build()
                        )
                        .add("code", "400")
                        .build();

                return Response.status(Response.Status.BAD_REQUEST).entity(value.toString()).build();
            }
            else {
                // Check if user exists in the database
                /*if (!userFactory.getDao().find(id).isPresent()) {
                    // Creates a JsonObject Builder
                    JsonObject value = Json.createObjectBuilder()
                            .add("error",
                                    Json.createObjectBuilder()
                                            .add("reason", "user does not exists")
                                            .add("message", "Not Found")
                                            .build()
                            )
                            .add("code", "404")
                            .build();

                    return Response.status(Response.Status.NOT_FOUND).entity(value.toString()).build();
                }
                else {
                    // Get user profile information
                    User user = userFactory.getDao().find(id).get();

                    // Creates a JsonObject Builder
                    JsonObject value = Json.createObjectBuilder()
                            .add("success",
                                    Json.createObjectBuilder()
                                            .add("username", user.getUsername())
                                            .add("role", user.getRole().getName())
                                            .add("code", "200")
                                            .build()
                            )
                            .build();

                    return Response.ok(value.toString()).build();
                }*/
                return null;
            }
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateUserInfosInXML(@QueryParam("token") String token, @QueryParam("id") int id) throws ParserConfigurationException {
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
            // TODO : Check if token is authorized

            if (id == 0) {
                // Creates root element
                Element root = doc.createElement("errors");
                doc.appendChild(root);

                // Creates error element
                Element error = doc.createElement("error");

                // Creates reason element
                Element reason = doc.createElement("reason");
                reason.appendChild(doc.createTextNode("null id"));
                error.appendChild(reason);

                // Creates message element
                Element message = doc.createElement("message");
                message.appendChild(doc.createTextNode("Bad Request"));
                error.appendChild(message);

                // Creates code element
                Element code = doc.createElement("code");
                code.appendChild(doc.createTextNode("400"));
                root.appendChild(error);
                root.appendChild(code);

                return Response.status(Response.Status.BAD_REQUEST).entity(Parser.XML(doc)).build();
            }
            else {
                // Check if user exists in the database
                /*if (!userFactory.getDao().find(id).isPresent()) {
                    // Creates root element
                    Element root = doc.createElement("errors");
                    doc.appendChild(root);

                    // Creates error element
                    Element error = doc.createElement("error");

                    // Creates reason element
                    Element reason = doc.createElement("reason");
                    reason.appendChild(doc.createTextNode("user does not exists"));
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
                    return null;
                }*/
                return null;
            }
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserInfosInJSON(@QueryParam("token") String token, @QueryParam("id") int id) {
        // Check if query parameters is not empty
        if (token == null || token.isEmpty()) {
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
            // TODO : Check if token is authorized

            if (id == 0) {
                // Creates a JsonObject Builder
                JsonObject value = Json.createObjectBuilder()
                        .add("error",
                                Json.createObjectBuilder()
                                        .add("reason", "null id")
                                        .add("message", "Bad Request")
                                        .build()
                        )
                        .add("code", "400")
                        .build();

                return Response.status(Response.Status.BAD_REQUEST).entity(value.toString()).build();
            }
            else {
                // Check if user exists in the database
                /*if (!userFactory.getDao().find(id).isPresent()) {
                    // Creates a JsonObject Builder
                    JsonObject value = Json.createObjectBuilder()
                            .add("error",
                                    Json.createObjectBuilder()
                                            .add("reason", "user does not exists")
                                            .add("message", "Not Found")
                                            .build()
                            )
                            .add("code", "404")
                            .build();

                    return Response.status(Response.Status.NOT_FOUND).entity(value.toString()).build();
                }
                else {
                    return null;
                }*/
                return null;
            }
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteUserAccountInXML(@QueryParam("token") String token, @QueryParam("id") int id) throws ParserConfigurationException {
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
            // TODO : Check if token is authorized

            if (id == 0) {
                // Creates root element
                Element root = doc.createElement("errors");
                doc.appendChild(root);

                // Creates error element
                Element error = doc.createElement("error");

                // Creates reason element
                Element reason = doc.createElement("reason");
                reason.appendChild(doc.createTextNode("null id"));
                error.appendChild(reason);

                // Creates message element
                Element message = doc.createElement("message");
                message.appendChild(doc.createTextNode("Bad Request"));
                error.appendChild(message);

                // Creates code element
                Element code = doc.createElement("code");
                code.appendChild(doc.createTextNode("400"));
                root.appendChild(error);
                root.appendChild(code);

                return Response.status(Response.Status.BAD_REQUEST).entity(Parser.XML(doc)).build();
            }
            else {
                // Check if user exists in the database
                /*if (!userFactory.getDao().find(id).isPresent()) {
                    // Creates root element
                    Element root = doc.createElement("errors");
                    doc.appendChild(root);

                    // Creates error element
                    Element error = doc.createElement("error");

                    // Creates reason element
                    Element reason = doc.createElement("reason");
                    reason.appendChild(doc.createTextNode("user does not exists"));
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
                    // Delete existing user from the database
                    userFactory.getDao().remove(userFactory.getDao().find(id).get());

                    // Creates root element
                    Element root = doc.createElement("success");
                    doc.appendChild(root);

                    // Creates message element
                    Element message = doc.createElement("message");
                    message.appendChild(doc.createTextNode("user account has been deleted"));
                    root.appendChild(message);

                    // Creates code element
                    Element code = doc.createElement("code");
                    code.appendChild(doc.createTextNode("200"));
                    root.appendChild(code);

                    return Response.ok(Parser.XML(doc)).build();
                }*/
                return null;
            }
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserAccountInJSON(@QueryParam("token") String token, @QueryParam("id") int id) {
        // Check if query parameters is not empty
        if (token == null || token.isEmpty()) {
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
            // TODO : Check if token is authorized

            if (id == 0) {
                // Creates a JsonObject Builder
                JsonObject value = Json.createObjectBuilder()
                        .add("error",
                                Json.createObjectBuilder()
                                        .add("reason", "null id")
                                        .add("message", "Bad Request")
                                        .build()
                        )
                        .add("code", "400")
                        .build();

                return Response.status(Response.Status.BAD_REQUEST).entity(value.toString()).build();
            }
            else {
                // Check if user exists in the database
                /*if (!userFactory.getDao().find(id).isPresent()) {
                    // Creates a JsonObject Builder
                    JsonObject value = Json.createObjectBuilder()
                            .add("error",
                                    Json.createObjectBuilder()
                                            .add("reason", "user does not exists")
                                            .add("message", "Not Found")
                                            .build()
                            )
                            .add("code", "404")
                            .build();

                    return Response.status(Response.Status.NOT_FOUND).entity(value.toString()).build();
                }
                else {
                    // Delete existing user from the database
                    userFactory.getDao().remove(userFactory.getDao().find(id).get());

                    // Creates a JsonObject Builder
                    JsonObject value = Json.createObjectBuilder()
                            .add("success",
                                    Json.createObjectBuilder()
                                            .add("message", "user account has been deleted")
                                            .add("code", "200")
                                            .build()
                            )
                            .build();

                    return Response.ok(value.toString()).build();
                }*/
                return null;
            }
        }
    }

}
