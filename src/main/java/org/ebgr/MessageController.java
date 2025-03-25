package org.ebgr;

import org.ebgr.DTO.MessageDTO;
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
    public RestResponse<MessageDTO> get_message() {
        RestResponse<MessageDTO> response = ResponseBuilder
            .ok(new MessageDTO(SENDER, MESSAGE, null))
            .build();
        System.out.println(response.getEntity().toString());

        return response;
    }

    @POST
    public RestResponse<MessageDTO> post_message(MessageDTO message) {
        if(message == null || message.content() == null || message.sender() == null)
            return get_message();

        MESSAGE = message.content();
        SENDER =  message.sender();
        return get_message();
    }
}
