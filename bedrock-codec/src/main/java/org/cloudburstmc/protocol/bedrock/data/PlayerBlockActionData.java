package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

@Data
public class PlayerBlockActionData {
    PlayerActionType playerActionType;
    Vector3i blockPosition;
    int facing;
}