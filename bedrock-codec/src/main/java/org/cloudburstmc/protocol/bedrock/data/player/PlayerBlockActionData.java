package org.cloudburstmc.protocol.bedrock.data.player;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerActionType;

@Data
public class PlayerBlockActionData {

    private PlayerActionType playerActionType;
    private Vector3i position;
    private int facing;
}