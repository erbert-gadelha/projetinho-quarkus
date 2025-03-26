package org.ebgr.service;

import java.util.List;

import org.ebgr.DTO.UserDTO;
import org.ebgr.entity.UserEntity;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

    public List<UserEntity> getUsers () {
        return UserEntity.findAll().list();
    } 

    public RestResponse saveUser(UserDTO dto) throws Error {
        if(dto.name() == null || dto.login() == null || dto.password() == null) {
            return ResponseBuilder
                        .ok("campos inválidos.").status(400)
                        .build();
        }
        
        UserEntity user = new UserEntity();
        user.name =  dto.name();
        user.login = dto.login();
        user.password = dto.password();
        UserEntity.persist(user);


        return ResponseBuilder
                    .ok(user)
                    .status(201)
                    .build();
    }
}
