package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import lombok.ToString;

/**
 * @author Kaooot
 */
@Data
@ToString
public class MoveActorDeltaData {

    private long actorRuntimeID;
    private float newPositionX;
    private float newPositionY;
    private float newPositionZ;
    private float rotationX;
    private float rotationY;
    private float rotationYHead;
}