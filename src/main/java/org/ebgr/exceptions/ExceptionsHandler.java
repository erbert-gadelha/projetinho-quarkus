package org.ebgr.exceptions;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionsHandler {
    @ServerExceptionMapper
    public RestResponse<String> mapException(jakarta.persistence.PersistenceException x) {
        return ResponseBuilder
            .ok("login de usuário já existente.")
            .status(400)
            .build();
    }
}
