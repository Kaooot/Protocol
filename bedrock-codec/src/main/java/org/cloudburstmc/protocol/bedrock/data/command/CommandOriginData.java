package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Data;

import java.util.UUID;

@Data
public class CommandOriginData {

    private CommandOriginType type;
    private UUID uuid;
    private String requestId;
    private Long playerId;
}