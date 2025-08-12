package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

@Data
public class PlayerBlockActionData {
    PlayerActionType action;
    /**
     * Only used if {@link #action} is set to {@link PlayerActionType#START_DESTROY_BLOCK}, {@link PlayerActionType#ABORT_DESTROY_BLOCK},
     * {@link PlayerActionType#CRACK_BLOCK}, {@link PlayerActionType#PREDICT_DESTROY_BLOCK} or {@link PlayerActionType#CONTINUE_DESTROY_BLOCK}
     */
    Vector3i blockPosition;
    /**
     * Only used if {@link #action} is set to {@link PlayerActionType#START_DESTROY_BLOCK}, {@link PlayerActionType#ABORT_DESTROY_BLOCK},
     * {@link PlayerActionType#CRACK_BLOCK}, {@link PlayerActionType#PREDICT_DESTROY_BLOCK} or {@link PlayerActionType#CONTINUE_DESTROY_BLOCK}
     */
    int face;
}
