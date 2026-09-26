package org.cloudburstmc.protocol.bedrock.data.text;

import lombok.Data;

@Data
public class AuthorAndMessage {

    private String playerName;
    private CharSequence message;

    public <T extends CharSequence> T getMessage(Class<T> type) {
        return type.cast(this.message);
    }
}