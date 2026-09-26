package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MoveActorDeltaData {

    private long actorRuntimeID;
    private Float newPositionX;
    private Float newPositionY;
    private Float newPositionZ;
    private Float rotationX;
    private Float rotationY;
    private Float rotationYHead;
    private boolean isOnGround;
    private boolean forceMove;
    private boolean forceMoveLocalEntity;
    private boolean forceCompletion;
    /**
     * @since v2192
     */
    private long ticks;
}
