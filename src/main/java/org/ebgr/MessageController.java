package org.ebgr;

import org.ebgr.DTO.Message;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/message")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageController {

    private static String SENDER = "default-server";
    private static String MESSAGE = "Hello, World!";

    @GET
    public RestResponse<Message> get_message() {
        RestResponse<Message> response = ResponseBuilder
            .ok(new Message(SENDER, MESSAGE, null))
            .build();
        System.out.println(response.getEntity().toString());

        return response;
    }

    @POST
    public RestResponse<Message> post_message(Message message) {
        if(message == null || message.content() == null || message.sender() == null)
            return get_message();

        MESSAGE = message.content();
        SENDER =  message.sender();
        return get_message();
    }
}
