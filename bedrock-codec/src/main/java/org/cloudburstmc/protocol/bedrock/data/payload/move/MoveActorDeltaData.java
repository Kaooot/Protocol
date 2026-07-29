package org.cloudburstmc.protocol.bedrock.data.payload.move;

import lombok.Data;
import lombok.ToString;

/**
 * @author Kaooot
 */
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
     * @since v2177
     */
    private long ticks;
}