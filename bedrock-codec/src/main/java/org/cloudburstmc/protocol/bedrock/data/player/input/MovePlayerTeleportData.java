package org.cloudburstmc.protocol.bedrock.data.player.input;

import lombok.Data;

@Data
public class MovePlayerTeleportData {

    private int teleportationCause;
    private int sourceActorType;
}