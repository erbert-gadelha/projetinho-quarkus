package org.ebgr.DTO;

import io.quarkus.runtime.annotations.RegisterForReflection;


@RegisterForReflection
public record UserDTO(
    String name,
    String login,
    String password
) {
    @Override
    public String toString() {
        //return String.format("{name=%s, login=%s, password=%s}", name, login, password);
        return String.format("""
                {
                    "name": "%s",
                    "login":"%s",
                    "password":"%s"
                }
                """, name, login, password);
    }
    
}
