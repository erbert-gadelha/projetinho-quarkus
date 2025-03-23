package org.ebgr.DTO;

public record User(
    String login,
    String password
) {
    @Override
    public String toString() {
        return String.format("Message[login=%s, password=%s]", login, password);
    }
    
}
