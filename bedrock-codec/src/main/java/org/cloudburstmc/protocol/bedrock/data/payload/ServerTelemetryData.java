package org.cloudburstmc.protocol.bedrock.data.payload;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class ServerTelemetryData {

    private String serverId = "";
    private String scenarioId = "";
    private String worldId = "";
    private String ownerId = "";
}