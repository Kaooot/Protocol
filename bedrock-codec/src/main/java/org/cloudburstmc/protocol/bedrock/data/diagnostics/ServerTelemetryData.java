package org.cloudburstmc.protocol.bedrock.data.diagnostics;

import lombok.Data;

@Data
public class ServerTelemetryData {

    private String serverId;
    private String scenarioId;
    private String worldId;
    private String ownerId;
}