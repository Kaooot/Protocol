package org.cloudburstmc.protocol.bedrock.data.actor;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

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
    @Added("26.50")
    private long ticks;
}
