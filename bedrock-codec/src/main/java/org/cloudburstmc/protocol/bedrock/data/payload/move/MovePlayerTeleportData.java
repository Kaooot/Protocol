package org.cloudburstmc.protocol.bedrock.data.payload.move;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class MovePlayerTeleportData {

    private TeleportationCause teleportationCause;
    private int sourceActorType;
}