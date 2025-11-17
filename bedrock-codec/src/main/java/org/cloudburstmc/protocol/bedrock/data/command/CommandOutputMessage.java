package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.NonNull;
import lombok.Value;

@Value
public class CommandOutputMessage {
    private final boolean successful;
    @NonNull
    private final String messageId;
    @NonNull
    private final String[] parameters;
}
