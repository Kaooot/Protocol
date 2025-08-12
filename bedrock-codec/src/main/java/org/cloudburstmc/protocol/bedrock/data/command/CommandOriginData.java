package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Value;

import java.util.UUID;

@Value
public class CommandOriginData {
    private final CommandOriginType commandType;
    private final UUID commandUUID;
    private final String requestID;
    private final long playerID;
}
