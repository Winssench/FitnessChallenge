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
 *		@file            	Close.java
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
import fr.ensisa.model.Challenge;
import fr.ensisa.res.GamingMode;
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

@Path("/challenge")
public class Close {

    private ChallengeFactory challengeFactory = new ChallengeFactory();

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createChallengeInPlainText(@QueryParam("token") String token, @QueryParam("name") String name, @QueryParam("author") String author, @QueryParam("maxUsers") int maxUsers, @DefaultValue("Solo") @QueryParam("mode") String mode) {
        // Check if query parameters is not empty
        if (token == null || token.isEmpty()) {
            // Define entity
            String entity = "{error{reason='null token'" +
                    ", message='Unauthorized'}" +
                    ", code='401'}";

            return Response.status(Response.Status.UNAUTHORIZED).entity(entity).build();
        }
        else {
            if ((name == null && author == null && mode == null) || (name.isEmpty() && author.isEmpty() && mode.isEmpty())) {
                // Define entity
                String entity = "{error{reason='null query parameters'" +
                        ", message='Bad Request'}" +
                        ", code='400'}";

                return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
            }
            else {
                // Add new challenge in the database
                challengeFactory.getDao().persist(new Challenge(name, author, maxUsers, GamingMode.find(mode).get()));

                // Define entity
                String entity = "{success{message='challenge has been created'" +
                        ", code='200'}}";

                return Response.ok(entity).build();
            }
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response createChallengeInXML(@QueryParam("token") String token, @QueryParam("name") String name, @QueryParam("author") String author, @QueryParam("maxUsers") int maxUsers, @DefaultValue("Solo") @QueryParam("mode") String mode) throws ParserConfigurationException {
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
            if ((name == null && author == null && mode == null) || (name.isEmpty() && author.isEmpty() && mode.isEmpty())) {
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
                // Add new challenge in the database
                challengeFactory.getDao().persist(new Challenge(name, author, maxUsers, GamingMode.find(mode).get()));

                // Creates root element
                Element root = doc.createElement("success");
                doc.appendChild(root);

                // Creates message element
                Element message = doc.createElement("message");
                message.appendChild(doc.createTextNode("challenge has been created"));
                root.appendChild(message);

                // Creates code element
                Element code = doc.createElement("code");
                code.appendChild(doc.createTextNode("200"));
                root.appendChild(code);

                return Response.ok(Parser.XML(doc)).build();
            }
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createChallengeInJSON(@QueryParam("token") String token, @QueryParam("name") String name, @QueryParam("author") String author, @QueryParam("maxUsers") int maxUsers, @DefaultValue("Solo") @QueryParam("mode") String mode) {
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
            if ((name == null && author == null && mode == null) || (name.isEmpty() && author.isEmpty() && mode.isEmpty())) {
                // Creates a JsonObject Builder
                JsonObject value = Json.createObjectBuilder()
                        .add("error",
                                Json.createObjectBuilder()
                                        .add("reason", "null query parameters")
                                        .add("message", "Bad Request")
                                        .build()
                        )
                        .add("code", "400")
                        .build();

                return Response.status(Response.Status.BAD_REQUEST).entity(value.toString()).build();
            }
            else {
                // Add new challenge in the database
                challengeFactory.getDao().persist(new Challenge(name, author, maxUsers, GamingMode.find(mode).get()));

                // Creates a JsonObject Builder
                JsonObject value = Json.createObjectBuilder()
                        .add("success",
                                Json.createObjectBuilder()
                                        .add("message", "challenge has been created")
                                        .add("code", "200")
                                        .build()
                        )
                        .build();

                return Response.ok(value.toString()).build();
            }
        }
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateChallengeInPlainText(@QueryParam("token") String token) {
        // Check if query parameters is not empty
        if (token == null || token.isEmpty()) {
            // Define entity
            String entity = "{error{reason='null token'" +
                    ", message='Unauthorized'}" +
                    ", code='401'}";

            return Response.status(Response.Status.UNAUTHORIZED).entity(entity).build();
        }
        else {
            // TODO : Check if token is authorized
            return Response.ok("updated").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateChallengeInXML(@QueryParam("token") String token) throws ParserConfigurationException {
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
            return Response.ok("updated").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateChallengeInJSON(@QueryParam("token") String token) {
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
            return Response.ok("updated").build();
        }
    }

    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteChallengeInTextPlain(@QueryParam("token") String token, @QueryParam("id") int id) {
        // Check if query parameters is not empty
        if (token == null || token.isEmpty()) {
            // Define entity
            String entity = "{error{reason='null token'" +
                    ", message='Unauthorized'}" +
                    ", code='401'}";

            return Response.status(Response.Status.UNAUTHORIZED).entity(entity).build();
        }
        else {
            if (id == 0) {
                // Define entity
                String entity = "{error{reason='null id'" +
                        ", message='Bad Request'}" +
                        ", code='400'}";

                return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
            }
            else {
                // Check if challenge exists in the database
                if (challengeFactory.getDao().find(id).isPresent()) {
                    // Define entity
                    String entity = "{error{reason='challenge does not exists'" +
                            ", message='Not Found'}" +
                            ", code='404'}";

                    return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
                }
                else {
                    // Delete existing challenge from the database
                    challengeFactory.getDao().remove(challengeFactory.getDao().find(id).get());

                    // Define entity
                    String entity = "{success{message='challenge has been deleted'" +
                            ", code='200'}}";

                    return Response.ok(entity).build();
                }
            }
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteChallengeInXML(@QueryParam("token") String token, @QueryParam("id") int id) throws ParserConfigurationException {
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
                // Check if challenge exists in the database
                if (challengeFactory.getDao().find(id).isPresent()) {
                    // Creates root element
                    Element root = doc.createElement("errors");
                    doc.appendChild(root);

                    // Creates error element
                    Element error = doc.createElement("error");

                    // Creates reason element
                    Element reason = doc.createElement("reason");
                    reason.appendChild(doc.createTextNode("challenge does not exists"));
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
                    // Delete existing challenge from the database
                    challengeFactory.getDao().remove(challengeFactory.getDao().find(id).get());

                    // Creates root element
                    Element root = doc.createElement("success");
                    doc.appendChild(root);

                    // Creates message element
                    Element message = doc.createElement("message");
                    message.appendChild(doc.createTextNode("challenge has been deleted"));
                    root.appendChild(message);

                    // Creates code element
                    Element code = doc.createElement("code");
                    code.appendChild(doc.createTextNode("200"));
                    root.appendChild(code);

                    return Response.ok(Parser.XML(doc)).build();
                }
            }
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteChallengeInJSON(@QueryParam("token") String token, @QueryParam("id") int id) {
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
                // Check if challenge exists in the database
                if (challengeFactory.getDao().find(id).isPresent()) {
                    // Creates a JsonObject Builder
                    JsonObject value = Json.createObjectBuilder()
                            .add("error",
                                    Json.createObjectBuilder()
                                            .add("reason", "challenge does not exists")
                                            .add("message", "Not Found")
                                            .build()
                            )
                            .add("code", "404")
                            .build();

                    return Response.status(Response.Status.NOT_FOUND).entity(value.toString()).build();
                }
                else {
                    // Delete existing challenge from the database
                    challengeFactory.getDao().remove(challengeFactory.getDao().find(id).get());

                    // Creates a JsonObject Builder
                    JsonObject value = Json.createObjectBuilder()
                            .add("success",
                                    Json.createObjectBuilder()
                                            .add("message", "challenge has been deleted")
                                            .add("code", "200")
                                            .build()
                            )
                            .build();

                    return Response.ok(value.toString()).build();
                }
            }
        }
    }

}
