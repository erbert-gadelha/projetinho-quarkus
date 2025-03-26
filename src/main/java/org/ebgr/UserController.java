package org.ebgr;

import java.util.ArrayList;
import java.util.List;

import org.ebgr.DTO.UserDTO;
import org.ebgr.service.UserService;
import org.jboss.resteasy.reactive.RestCookie;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
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
    SecurityIdentity securityIdentity;

    @Inject
    UserService userService;

    @GET
    public RestResponse<List<UserDTO>> get_user() {

        List<UserDTO> users = new ArrayList<>();
        userService.getUsers().stream().forEach(user -> {
            users.add(
                new UserDTO(
                    user.name,
                    user.login,
                    user.password
                ));
        });

        return ResponseBuilder
                    .ok(users)
                    .build();
    }

    @GET
    @Path("me")
    public RestResponse<String> get_me(SecurityIdentity identity) {        
        return ResponseBuilder
                    .ok(identity.getPrincipal().getName())
                    .build();
    }


    @POST
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
