package org.ebgr.DTO;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record Message(
    String sender,
    String content,
    String cookies
) {
    @Override
    public String toString() {
        return String.format("Message[sender=%s, content=%s, cookies=%s]", sender, content, cookies);
    }
}
