package org.ebgr;

import org.ebgr.DTO.UserDTO;
import org.ebgr.service.UserService;
import org.jboss.resteasy.reactive.RestCookie;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public RestResponse<String> get_user(@RestCookie String token) {

        return ResponseBuilder
                    .ok(String.format("Logged as user (%s).", token))
                    .build();
    }


    @POST
    @Transactional(dontRollbackOn=Exception.class)
    public RestResponse create(UserDTO dto) {
        return userService.saveUser(dto);
    }

    @POST
    @Path("authenticate")
    public RestResponse<UserDTO> authenticate(UserDTO user) {
        return ResponseBuilder
                    .ok(user)
                    .cookie(new NewCookie("token", user.login(), "/", null, 0, null, 3600, false))
                    .build();
    }

    @GET
    @Path("/logout")
    public RestResponse<String> logout(@RestCookie String token) {
        return ResponseBuilder
                    .ok(String.format("logout from user (%s).", token))
                    .cookie(new NewCookie("token", "", "/", null, 0, null, 0, false))
                    .build();
    }

}
